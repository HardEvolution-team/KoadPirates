package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Cucumber plant (ages 0..10). The parent (planted from seeds) spreads cucumber vines onto neighbouring farmland once
 * older than 6; vines die without a parent next to them. Mature plants (age 10) are harvested with shears.
 */
public class BlockCucumberCrop extends BlockBush implements IGrowable, IArchaicRenderBlock {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 10);
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.25, 1);
    private final boolean parent;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockCucumberCrop(boolean parent) {
        this.parent = parent;
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
    }

    @Override protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, AGE); }
    @Override public IBlockState getStateFromMeta(int meta) { return this.getDefaultState().withProperty(AGE, Math.min(meta, 10)); }
    @Override public int getMetaFromState(IBlockState s) { return s.getValue(AGE); }
    @Override public int getArchaicRenderType(int key) { return RENDER_CROPS; }
    @Override public AxisAlignedBB getBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return AABB; }
    @Override protected boolean canSustainBush(IBlockState state) { return state.getBlock() == Blocks.FARMLAND; }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[11];
        for (int i = 0; i < 11; i++) this.icons[i] = reg.registerIcon("koadpirates:blocks/cucumbers/crops_" + i);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.icons[MathHelper.clamp(meta, 0, 10)];
    }

    private void fertilize(World w, BlockPos p, IBlockState s) {
        int l = Math.min(10, s.getValue(AGE) + MathHelper.getInt(w.rand, 2, 5));
        w.setBlockState(p, s.withProperty(AGE, l), 2);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        ItemStack held = player.getHeldItem(hand);
        if (held.isEmpty()) return false;
        if (held.getItem() == Items.GOLDEN_HOE) {
            if (!world.isRemote) fertilize(world, pos, state);
            return true;
        }
        if (held.getItem() == Items.SHEARS) {
            if (state.getValue(AGE) <= 9 || world.isRemote || !world.getGameRules().getBoolean("doTileDrops")) return true;
            float f = 0.7F;
            EntityItem ei = new EntityItem(world, pos.getX() + world.rand.nextFloat() * f + (1 - f) * 0.5, pos.getY() + world.rand.nextFloat() * f + (1 - f) * 0.5,
                    pos.getZ() + world.rand.nextFloat() * f + (1 - f) * 0.5, new ItemStack(PiratesContent.cucumberFruit));
            ei.setPickupDelay(10);
            world.spawnEntity(ei);
            if (world.rand.nextInt(this.parent ? 3 : 4) < 2) world.setBlockState(pos, state.withProperty(AGE, 7), 2);
            held.damageItem(1, player);
            return true;
        }
        return false;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        if (world.getBlockState(pos).getBlock() != this) return;
        int l = state.getValue(AGE);
        if (world.getLightFromNeighbors(pos.up()) >= 9 && l < 10) {
            int has = l > 7 ? (this.parent ? 25 : 20) : 15;
            if (rand.nextInt(has) == 0) world.setBlockState(pos, state.withProperty(AGE, ++l), 2);
        }
        if (this.parent) {
            if (l > 6) {
                for (EnumFacing f : EnumFacing.HORIZONTALS) {
                    trySpread(world, pos.offset(f), rand);
                    trySpread(world, pos.offset(f).offset(f.rotateY()), rand);
                }
            }
        } else {
            boolean hasParent = false;
            for (EnumFacing f : EnumFacing.HORIZONTALS) {
                if (world.getBlockState(pos.offset(f)).getBlock() == PiratesContent.cucumberParent
                        || world.getBlockState(pos.offset(f).offset(f.rotateY())).getBlock() == PiratesContent.cucumberParent) {
                    hasParent = true;
                    break;
                }
            }
            if (!hasParent) world.setBlockToAir(pos);
        }
    }

    private static void trySpread(World w, BlockPos p, Random rand) {
        if (rand.nextInt(20) == 0 && w.getBlockState(p.down()).getBlock() == Blocks.FARMLAND && w.isAirBlock(p)) {
            w.setBlockState(p, PiratesContent.cucumber.getDefaultState());
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = state.getValue(AGE);
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        if (age >= 7) {
            for (int n = 0; n < 3 + fortune; n++) {
                if (rand.nextInt(15) <= age) drops.add(new ItemStack(PiratesContent.cucumberSeeds));
            }
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(PiratesContent.cucumberSeeds);
    }

    @Override
    public boolean canGrow(World w, BlockPos p, IBlockState s, boolean client) { return s.getValue(AGE) < 10; }
    @Override
    public boolean canUseBonemeal(World w, Random r, BlockPos p, IBlockState s) { return true; }
    @Override
    public void grow(World w, Random r, BlockPos p, IBlockState s) { fertilize(w, p, s); }

    @SuppressWarnings("unused")
    private static boolean isCucumber(Block b) {
        return b == PiratesContent.cucumber || b == PiratesContent.cucumberParent;
    }
}
