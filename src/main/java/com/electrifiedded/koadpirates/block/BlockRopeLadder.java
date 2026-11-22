package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.ArchaicRenderPart;
import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Vine-like rope ladder (1.7 render type 20). Meta bits like vines: 1 = south, 2 = west, 4 = north, 8 = east support.
 * Right-click with a rope ladder to extend it downwards, with an empty hand to pull the lowest piece back up.
 */
public class BlockRopeLadder extends Block implements IArchaicRenderBlock {
    public static final PropertyInteger SIDES = PropertyInteger.create("sides", 0, 15);
    private static final double T = 0.0625;
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockRopeLadder() {
        super(Material.VINE);
        this.setSoundType(SoundType.CLOTH);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SIDES, 0));
    }

    @Override protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, SIDES); }
    @Override public IBlockState getStateFromMeta(int meta) { return this.getDefaultState().withProperty(SIDES, meta & 15); }
    @Override public int getMetaFromState(IBlockState s) { return s.getValue(SIDES); }
    @Override public int getArchaicItemRenderKey(int meta) { return 0; }
    @Override public AxisAlignedBB getCollisionBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return NULL_AABB; }
    @Override public boolean isOpaqueCube(IBlockState s) { return false; }
    @Override public boolean isFullCube(IBlockState s) { return false; }
    @Override public BlockFaceShape getBlockFaceShape(IBlockAccess w, IBlockState s, BlockPos p, EnumFacing f) { return BlockFaceShape.UNDEFINED; }
    @Override @SideOnly(Side.CLIENT) public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT; }
    @Override public boolean isLadder(IBlockState s, IBlockAccess w, BlockPos p, EntityLivingBase e) { return true; }
    @Override public Item getItemDropped(IBlockState s, Random r, int f) { return Item.getItemFromBlock(this); }
    @Override public int damageDropped(IBlockState s) { return 0; }

    private static AxisAlignedBB boxFor(int bits) {
        if ((bits & 2) != 0) return new AxisAlignedBB(0, 0, 0, T, 1, 1);
        if ((bits & 8) != 0) return new AxisAlignedBB(1 - T, 0, 0, 1, 1, 1);
        if ((bits & 4) != 0) return new AxisAlignedBB(0, 0, 0, 1, 1, T);
        if ((bits & 1) != 0) return new AxisAlignedBB(0, 0, 1 - T, 1, 1, 1);
        return new AxisAlignedBB(0, 1 - T, 0, 1, 1, 1);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) {
        return boxFor(s.getValue(SIDES));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon("koadpirates:ropeladder");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return this.icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<ArchaicRenderPart> getArchaicRenderParts(int key) {
        List<ArchaicRenderPart> parts = new ArrayList<>();
        double d = 0.05;
        if ((key & 1) != 0) parts.add(panel(new AxisAlignedBB(0, 0, 1 - d, 1, 1, 1 - d + 0.001)));
        if ((key & 2) != 0) parts.add(panel(new AxisAlignedBB(d - 0.001, 0, 0, d, 1, 1)));
        if ((key & 4) != 0) parts.add(panel(new AxisAlignedBB(0, 0, d - 0.001, 1, 1, d)));
        if ((key & 8) != 0) parts.add(panel(new AxisAlignedBB(1 - d, 0, 0, 1 - d + 0.001, 1, 1)));
        if (parts.isEmpty()) parts.add(panel(new AxisAlignedBB(0, 0, 0.5, 1, 1, 0.501)));
        return parts;
    }

    @SideOnly(Side.CLIENT)
    private ArchaicRenderPart panel(AxisAlignedBB bb) {
        ArchaicRenderPart p = ArchaicRenderPart.box(bb, this.icon);
        p.doubleSided = true;
        p.cull = false;
        return p;
    }

    private static int bitFor(EnumFacing support) {
        switch (support) {
            case SOUTH: return 1;
            case WEST: return 2;
            case NORTH: return 4;
            case EAST: return 8;
            default: return 0;
        }
    }

    private static boolean canBePlacedOn(IBlockState s) {
        return s.isFullCube();
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
        if (side == EnumFacing.DOWN) return canBePlacedOn(world.getBlockState(pos.up()));
        if (side == EnumFacing.UP) return false;
        return canBePlacedOn(world.getBlockState(pos.offset(side.getOpposite())));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hx, float hy, float hz, int meta, EntityLivingBase placer) {
        int b = facing.getAxis().isHorizontal() ? bitFor(facing.getOpposite()) : 0;
        return this.getDefaultState().withProperty(SIDES, b);
    }

    private boolean canStay(World world, BlockPos pos, IBlockState state) {
        int l = state.getValue(SIDES);
        int keep = l;
        for (EnumFacing f : EnumFacing.HORIZONTALS) {
            int k = bitFor(f);
            if ((l & k) == 0) continue;
            IBlockState above = world.getBlockState(pos.up());
            boolean supported = canBePlacedOn(world.getBlockState(pos.offset(f)))
                    || (above.getBlock() == this && (above.getValue(SIDES) & k) != 0);
            if (!supported) keep &= ~k;
        }
        IBlockState above = world.getBlockState(pos.up());
        if (keep == 0 && !canBePlacedOn(above) && above.getBlock() != this) return false;
        if (keep != l) world.setBlockState(pos, state.withProperty(SIDES, keep), 2);
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
        if (!world.isRemote && !canStay(world, pos, state)) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        if (hand != EnumHand.MAIN_HAND) return false;
        ItemStack held = player.getHeldItem(hand);
        if (held.isEmpty()) {
            BlockPos p = pos;
            while (world.getBlockState(p.down()).getBlock() == this) p = p.down();
            if (!world.isRemote) {
                world.setBlockToAir(p);
                if (!player.inventory.addItemStackToInventory(new ItemStack(this))) player.dropItem(new ItemStack(this), false);
            }
            return true;
        }
        if (held.getItem() == Item.getItemFromBlock(this)) {
            BlockPos p = pos.down();
            while (p.getY() > 0 && world.getBlockState(p).getBlock() == this) p = p.down();
            if (p.getY() > 0 && world.isAirBlock(p)) {
                if (!world.isRemote) {
                    world.setBlockState(p, state, 2);
                    if (!player.capabilities.isCreativeMode) held.shrink(1);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity e) {
        if (e instanceof EntityLivingBase) {
            e.motionY = e.isSneaking() ? 0.25 : Math.max(e.motionY, 0.0);
            e.fallDistance = 0.0F;
        }
    }
}
