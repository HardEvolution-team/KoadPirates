package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * Fish trap: placed fully under water it slowly catches fish (max 4). Sea weed on all 4 diagonals -> salmon,
 * anemones -> clownfish, corals -> pufferfish (faster with all four ripe colours), otherwise cod.
 */
public class TileFishTrap extends TileInventory implements ITickable {
    public TileFishTrap() {
        super(1, "tile.koadpirates.fishTrap.name");
    }

    private boolean water(BlockPos p) {
        return com.electrifiedded.koadpirates.block.BlockSeaPlant.isWater(this.world, p);
    }

    public boolean isInWater() {
        for (int dx = -1; dx <= 1; dx++)
            for (int dz = -1; dz <= 1; dz++) {
                if ((dx != 0 || dz != 0) && !water(this.pos.add(dx, 0, dz))) return false;
                if (!water(this.pos.add(dx, 1, dz))) return false;
            }
        return true;
    }

    private boolean diagonals(Block b) {
        return this.world.getBlockState(this.pos.add(1, 0, 1)).getBlock() == b && this.world.getBlockState(this.pos.add(-1, 0, -1)).getBlock() == b
                && this.world.getBlockState(this.pos.add(1, 0, -1)).getBlock() == b && this.world.getBlockState(this.pos.add(-1, 0, 1)).getBlock() == b;
    }

    private void add(ItemFishFood.FishType type) {
        ItemStack fish = new ItemStack(Items.FISH, 1, type.getMetadata());
        ItemStack cur = this.stacks.get(0);
        if (cur.isEmpty()) this.stacks.set(0, fish);
        else if (cur.isItemEqual(fish) && cur.getCount() < 4) cur.grow(1);
        else return;
        markDirty();
    }

    @Override
    public void update() {
        if (!isInWater()) return;
        if (this.world.isRemote) {
            double x = this.pos.getX() + this.world.rand.nextFloat(), y = this.pos.getY() + this.world.rand.nextFloat(), z = this.pos.getZ() + this.world.rand.nextFloat();
            for (int i = 0; i < 3; i++) this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, x, y, z, 0, 0, 0);
            return;
        }
        java.util.Random r = this.world.rand;
        if (diagonals(PiratesContent.seaWeed)) {
            if (r.nextInt(17000) == 1) add(ItemFishFood.FishType.SALMON);
        } else if (diagonals(PiratesContent.anem)) {
            if (r.nextInt(500000) == 1) add(ItemFishFood.FishType.CLOWNFISH);
        } else if (diagonals(PiratesContent.kelp)) {
            boolean[] ripe = new boolean[4];
            int[][] d = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
            for (int[] o : d) {
                int m = PiratesContent.kelp.getMetaFromState(this.world.getBlockState(this.pos.add(o[0], 0, o[1])));
                if (m >= 9 && (m & 1) == 1) ripe[(m - 9) / 2] = true;
            }
            int rate = ripe[0] && ripe[1] && ripe[2] && ripe[3] ? 4 : 8;
            if (r.nextInt(10000 * rate) == 1) add(ItemFishFood.FishType.PUFFERFISH);
        } else if (r.nextInt(10000) == 1) {
            add(ItemFishFood.FishType.COD);
        }
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack s) {
        return false;
    }
}
