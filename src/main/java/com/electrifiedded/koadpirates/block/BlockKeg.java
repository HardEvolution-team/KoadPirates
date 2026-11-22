package com.electrifiedded.koadpirates.block;

import com.electrifiedded.koadpirates.entity.EntityKegPrimed;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/** Powder keg: lit by flint and steel, nearby fire or explosions; blows up almost instantly (power 4). */
public class BlockKeg extends BlockSimple {
    public BlockKeg() {
        super(Material.WOOD, SoundType.WOOD, "koadpirates:KegSide", null, 1, 1);
    }

    @Override
    public Item getItemDropped(IBlockState state, java.util.Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    private static boolean fireNear(World w, BlockPos p) {
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                for (int dz = -1; dz <= 1; dz++) {
                    if (Math.abs(dx) + Math.abs(dz) > 1) continue; // 1.7 checked the plus shape on each layer
                    if (w.getBlockState(p.add(dx, dy, dz)).getBlock() == Blocks.FIRE) return true;
                }
        return false;
    }

    private static void prime(World w, BlockPos p) {
        if (w.isRemote) return;
        w.spawnEntity(new EntityKegPrimed(w, p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5));
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
        if (!world.isRemote && fireNear(world, pos)) {
            world.setBlockToAir(pos);
            prime(world, pos);
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote && fireNear(world, pos)) {
            world.setBlockToAir(pos);
            prime(world, pos);
        }
    }

    @Override
    public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) {
        prime(world, pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        ItemStack held = player.getHeldItem(hand);
        if (!held.isEmpty() && (held.getItem() == Items.FLINT_AND_STEEL || held.getItem() == Items.FIRE_CHARGE)) {
            if (!world.isRemote) {
                world.setBlockToAir(pos);
                prime(world, pos);
                if (held.getItem() == Items.FLINT_AND_STEEL) held.damageItem(1, player);
                else if (!player.capabilities.isCreativeMode) held.shrink(1);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }
}
