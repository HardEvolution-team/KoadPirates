package com.electrifiedded.koadpirates.block;

import com.electrifiedded.koadpirates.entity.EntityPirateBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/** Ship hull: pirates climb it (slightly inset collision box), drops planks. */
public class BlockHull extends BlockSimple {
    private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0125, 0.0, 0.0125, 1 - 0.0125, 1 - 0.0125, 1 - 0.0125);

    public BlockHull() {
        super(Material.WOOD, SoundType.WOOD, "koadpirates:Wood", () -> Item.getItemFromBlock(Blocks.PLANKS), 1, 1);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return BOX;
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (entity instanceof EntityPirateBase) entity.motionY += 0.04;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.PLANKS);
    }
}
