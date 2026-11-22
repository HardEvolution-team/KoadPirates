package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Underwater plants of the original: sea weed (grows up to 5 high), anemone (hurts) and coral ("kelp", metas 8..15:
 * even = growing, odd = ripe; right-click a ripe coral to harvest a coloured coral item).
 * They need water on all sides/above and dirt, sand, clay or gravel below; broken plants turn back into water.
 */
public class BlockSeaPlant extends Block implements IArchaicRenderBlock {
    public static final int SEAWEED = 0, ANEMONE = 1, KELP = 2;
    public static final PropertyInteger META = PropertyInteger.create("meta", 0, 15);
    /** own material: water must not see these as water (1.12 liquids read LEVEL from any WATER block) nor flow into them */
    public static final Material SEA_PLANT = new Material(net.minecraft.block.material.MapColor.WATER) {
        @Override public boolean isSolid() { return false; }
        @Override public boolean isOpaque() { return false; }
    };
    private final int kind;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockSeaPlant(int kind) {
        super(SEA_PLANT);
        this.kind = kind;
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        this.setDefaultState(this.blockState.getBaseState().withProperty(META, kind == KELP ? 8 : 0));
    }

    @Override protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, META); }
    @Override public IBlockState getStateFromMeta(int meta) { return this.getDefaultState().withProperty(META, meta & 15); }
    @Override public int getMetaFromState(IBlockState s) { return s.getValue(META); }
    @Override public int getArchaicRenderKey(IBlockState s) { return this.kind == KELP ? s.getValue(META) : 0; }
    @Override public int getArchaicItemRenderKey(int meta) { return this.kind == KELP ? 9 : 0; }
    @Override public int getArchaicRenderType(int key) { return RENDER_CROSS; }

    @Override public AxisAlignedBB getCollisionBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return NULL_AABB; }
    @Override public boolean isOpaqueCube(IBlockState s) { return false; }
    @Override public boolean isFullCube(IBlockState s) { return false; }
    @Override public BlockFaceShape getBlockFaceShape(IBlockAccess w, IBlockState s, BlockPos p, EnumFacing f) { return BlockFaceShape.UNDEFINED; }
    @Override @SideOnly(Side.CLIENT) public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT; }
    @Override public boolean isReplaceable(IBlockAccess w, BlockPos p) { return false; }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        if (this.kind == KELP) {
            this.icons = new IIcon[16];
            for (int i = 0; i < 16; i++) this.icons[i] = reg.registerIcon("koadpirates:blocks/kelp/kelp_" + i);
        } else {
            this.icons = new IIcon[]{reg.registerIcon(this.kind == SEAWEED ? "koadpirates:seaWeed" : "koadpirates:Amen")};
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.icons[Math.min(meta, this.icons.length - 1)];
    }

    // ---- placement rules
    public static boolean isWater(World w, BlockPos p) {
        net.minecraft.block.state.IBlockState s = w.getBlockState(p);
        return s.getMaterial() == Material.WATER || s.getBlock() instanceof BlockSeaPlant;
    }

    public boolean isInWater(World w, BlockPos p) {
        for (EnumFacing f : EnumFacing.HORIZONTALS) if (!isWater(w, p.offset(f))) return false;
        BlockPos up = p.up();
        if (!isWater(w, up)) return false;
        for (EnumFacing f : EnumFacing.HORIZONTALS) if (!isWater(w, up.offset(f))) return false;
        return true;
    }

    public boolean isOnBlock(World w, BlockPos p) {
        Block b = w.getBlockState(p.down()).getBlock();
        return b == Blocks.DIRT || b == Blocks.SAND || b == Blocks.CLAY || b == Blocks.GRAVEL || (this.kind == SEAWEED && b == this);
    }

    /** 1.7 canBlockStay(world, x, y, z) for world generation */
    public boolean canBlockStay(World w, BlockPos p) {
        Block here = w.getBlockState(p).getBlock();
        return isWater(w, p) && here != PiratesContent.kelp && here != PiratesContent.seaWeed && here != PiratesContent.anem
                && isInWater(w, p) && isOnBlock(w, p);
    }

    @Override
    public boolean canPlaceBlockAt(World w, BlockPos p) {
        return w.getBlockState(p).getMaterial() == Material.WATER && canBlockStay(w, p);
    }

    /** @return true when the plant broke (it becomes water again) */
    private boolean checkForBreak(World w, BlockPos p) {
        if (isInWater(w, p) && isOnBlock(w, p)) return false;
        if (w.getBlockState(p).getBlock() == this) w.setBlockState(p, Blocks.WATER.getDefaultState());
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
        if (!world.isRemote) checkForBreak(world, pos);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        this.onBlockHarvested(world, pos, state, player);
        return world.setBlockState(pos, Blocks.WATER.getDefaultState(), world.isRemote ? 11 : 3);
    }

    @Override
    public void updateTick(World w, BlockPos p, IBlockState state, Random rand) {
        if (w.isRemote || checkForBreak(w, p)) return;
        if (this.kind == SEAWEED) {
            if (w.getBlockState(p.up()).getBlock() == Blocks.WATER && w.getBlockState(p.up(2)).getBlock() == Blocks.WATER) {
                int l = 1;
                while (w.getBlockState(p.down(l)).getBlock() == this) l++;
                int odds = l == 1 ? 10 : l == 2 ? 20 : l == 3 ? 25 : l == 4 ? 50 : 100;
                if (l < 6 && rand.nextInt(odds) == 0) w.setBlockState(p.up(), this.getDefaultState());
            }
        } else if (this.kind == KELP && rand.nextInt(50) == 0) {
            int m = state.getValue(META);
            if (m == 8 || m == 10 || m == 12 || m == 14) w.setBlockState(p, state.withProperty(META, m + 1), 2);
        }
    }

    @Override
    public void onEntityCollision(World w, BlockPos p, IBlockState s, Entity e) {
        if (this.kind == ANEMONE) e.attackEntityFrom(DamageSource.CACTUS, 1.0F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        if (this.kind != KELP) return false;
        int l = state.getValue(META);
        if (l < 9 || (l & 1) == 0) return false;
        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops")) {
            ItemStack drop = new ItemStack(PiratesContent.kelpItem, 1, (l - 9) / 2);
            float f = 0.7F;
            EntityItem ei = new EntityItem(world, pos.getX() + world.rand.nextFloat() * f + (1 - f) * 0.5, pos.getY() + world.rand.nextFloat() * f + (1 - f) * 0.5,
                    pos.getZ() + world.rand.nextFloat() * f + (1 - f) * 0.5, drop);
            ei.setPickupDelay(10);
            world.spawnEntity(ei);
            world.setBlockState(pos, state.withProperty(META, l - 1), 2);
        }
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this.kind == KELP ? net.minecraft.init.Items.AIR : Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        if (this.kind == KELP) return new ItemStack(PiratesContent.kelpItem, 1, Math.max(0, (state.getValue(META) - 8) / 2));
        return new ItemStack(this);
    }
}
