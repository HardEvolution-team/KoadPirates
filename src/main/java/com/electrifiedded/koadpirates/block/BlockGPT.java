package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/** Gun powder trail: a flat fuse; fire next to it makes it burn into fire, which lights the next piece. */
public class BlockGPT extends Block implements IArchaicRenderBlock {
    public static final PropertyBool BURNING = PropertyBool.create("burning");
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.0625, 1);
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockGPT() {
        super(Material.CIRCUITS);
        this.setSoundType(SoundType.SAND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BURNING, false));
    }

    @Override protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, BURNING); }
    @Override public IBlockState getStateFromMeta(int meta) { return this.getDefaultState().withProperty(BURNING, (meta & 1) != 0); }
    @Override public int getMetaFromState(IBlockState s) { return s.getValue(BURNING) ? 1 : 0; }
    @Override public int getArchaicRenderKey(IBlockState s) { return 0; }

    @Override public AxisAlignedBB getBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return AABB; }
    @Override public AxisAlignedBB getCollisionBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return NULL_AABB; }
    @Override public boolean isOpaqueCube(IBlockState s) { return false; }
    @Override public boolean isFullCube(IBlockState s) { return false; }
    @Override public BlockFaceShape getBlockFaceShape(IBlockAccess w, IBlockState s, BlockPos p, EnumFacing f) { return BlockFaceShape.UNDEFINED; }
    @Override public int tickRate(World w) { return 4; }
    @Override @SideOnly(Side.CLIENT) public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT; }
    @Override public AxisAlignedBB getArchaicRenderBounds(int key) { return AABB; }
    @Override public boolean hasArchaicTint() { return true; }
    @Override public int colorMultiplier(IBlockAccess w, BlockPos p) { return 11119017; }
    @Override public int getRenderColor(int meta) { return 11119017; }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon("koadpirates:GPT");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 || side == 0 ? this.icon : null;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        IBlockState below = world.getBlockState(pos.down());
        return below.isTopSolid() || below.getBlockFaceShape(world, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID || below.getBlock() == Blocks.GLOWSTONE;
    }

    private static boolean fireNear(World w, BlockPos p) {
        for (int dy = -1; dy <= 1; dy++)
            for (EnumFacing f : EnumFacing.HORIZONTALS)
                if (w.getBlockState(p.up(dy).offset(f)).getBlock() == Blocks.FIRE) return true;
        return w.getBlockState(p.up()).getBlock() == Blocks.FIRE || w.getBlockState(p.down()).getBlock() == Blocks.FIRE;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
        if (world.isRemote) return;
        if (!canPlaceBlockAt(world, pos)) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
            return;
        }
        if (fireNear(world, pos)) {
            world.setBlockState(pos, state.withProperty(BURNING, true), 4);
            world.scheduleUpdate(pos, this, this.tickRate(world));
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (fireNear(world, pos)) state = state.withProperty(BURNING, true);
        if (state.getValue(BURNING)) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return PiratesContent.IGPT;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, net.minecraft.util.math.RayTraceResult target, World world, BlockPos pos, net.minecraft.entity.player.EntityPlayer player) {
        return new ItemStack(PiratesContent.IGPT);
    }
}
