package com.electrifiedded.koadpirates.block;

import com.electrifiedded.koadpirates.tile.TilePirateSpawner;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/** Flat wooden plate on pirate ships that keeps spawning pirates while a player is near. */
public class BlockPirateSpawner extends BlockSimple implements ITileEntityProvider {
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.125, 1);

    public BlockPirateSpawner() {
        super(Material.ROCK, SoundType.WOOD, "koadpirates:Wood", null, 0, 0);
        this.setLightOpacity(0);
    }

    @Override public TileEntity createNewTileEntity(World world, int meta) { return new TilePirateSpawner(); }
    @Override public Item getItemDropped(IBlockState s, Random r, int f) { return net.minecraft.init.Items.AIR; }
    @Override public int quantityDropped(Random r) { return 0; }
    @Override public int getExpDrop(IBlockState s, IBlockAccess w, BlockPos p, int fortune) { return 15 + RANDOM.nextInt(15) + RANDOM.nextInt(15); }
    @Override public AxisAlignedBB getBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return AABB; }
    @Override public AxisAlignedBB getArchaicRenderBounds(int key) { return AABB; }
    @Override public boolean isOpaqueCube(IBlockState s) { return false; }
    @Override public boolean isFullCube(IBlockState s) { return false; }
    @Override public BlockFaceShape getBlockFaceShape(IBlockAccess w, IBlockState s, BlockPos p, EnumFacing f) { return f == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED; }
}
