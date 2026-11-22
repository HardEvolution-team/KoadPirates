package com.electrifiedded.koadpirates.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.electrifiedded.koadpirates.PiratesContent;
import com.electrifiedded.koadpirates.tile.*;

public class WorldGenSunkBoat {
   public boolean generate(World world, Random random, int i, int j, int k) {
      if (W.get(world, i, j + 1, k) == Blocks.WATER
         && W.get(world, i, j, k + 4) == Blocks.WATER
         && W.get(world, i, j, k) == Blocks.WATER
         && W.get(world, i, j, k + 1) == Blocks.WATER
         && W.get(world, i, j, k + 2) == Blocks.WATER
         && W.get(world, i, j + 2, k) == Blocks.WATER
         && W.get(world, i, j + 3, k) == Blocks.WATER
         && W.get(world, i, j + 4, k) == Blocks.WATER
         && W.get(world, i, j + 5, k) == Blocks.WATER
         && W.get(world, i, j - 2, k) != Blocks.WATER
         && W.get(world, i, j - 1, k) != Blocks.WATER) {
         Block wood = Blocks.PLANKS;
         Block stairs = Blocks.PLANKS;
         Block half = Blocks.PLANKS;
         Block ladder = Blocks.LADDER;
         Block woody = Blocks.LOG;
         Block wool = Blocks.WOOL;
         W.set(world, i, j, k, wood);
         W.set(world, i, j, k + 1, wood);
         W.set(world, i, j, k + 2, wood);
         W.set(world, i, j, k + 3, wood);
         W.set(world, i, j, k + 4, wood);
         W.set(world, i, j, k + 5, wood);
         W.set(world, i, j, k + 6, wood);
         W.set(world, i, j, k + 7, wood);
         W.set(world, i, j, k - 1, wood);
         W.set(world, i, j, k - 2, wood);
         W.set(world, i + 6, j, k, wood);
         W.set(world, i + 6, j, k + 1, wood);
         W.set(world, i + 6, j, k + 2, wood);
         W.set(world, i + 6, j, k + 3, wood);
         W.set(world, i + 6, j, k + 4, wood);
         W.set(world, i + 6, j, k + 5, wood);
         W.set(world, i + 6, j, k + 6, wood);
         W.set(world, i + 6, j, k + 7, wood);
         W.set(world, i + 6, j, k - 1, wood);
         W.set(world, i + 6, j, k - 2, wood);
         W.set(world, i + 1, j - 1, k, wood);
         W.set(world, i + 1, j - 1, k + 1, wood);
         W.set(world, i + 1, j - 1, k + 2, wood);
         W.set(world, i + 1, j - 1, k + 3, wood);
         W.set(world, i + 1, j - 1, k + 4, wood);
         W.set(world, i + 1, j - 1, k + 5, wood);
         W.set(world, i + 1, j - 1, k + 6, wood);
         W.set(world, i + 1, j - 1, k + 7, wood);
         W.set(world, i + 1, j - 1, k - 1, wood);
         W.set(world, i + 1, j - 1, k - 2, wood);
         W.set(world, i + 2, j - 1, k, wood);
         W.set(world, i + 2, j - 1, k + 1, wood);
         W.set(world, i + 2, j - 1, k + 2, wood);
         W.set(world, i + 2, j - 1, k + 3, wood);
         W.set(world, i + 2, j - 1, k + 4, wood);
         W.set(world, i + 2, j - 1, k + 5, wood);
         W.set(world, i + 2, j - 1, k + 6, wood);
         W.set(world, i + 2, j - 1, k + 7, wood);
         W.set(world, i + 2, j - 1, k - 1, wood);
         W.set(world, i + 2, j - 1, k - 2, wood);
         W.set(world, i + 3, j - 1, k, wood);
         W.set(world, i + 3, j - 1, k + 1, wood);
         W.set(world, i + 3, j - 1, k + 2, wood);
         W.set(world, i + 3, j - 1, k + 3, wood);
         W.set(world, i + 3, j - 1, k + 4, wood);
         W.set(world, i + 3, j - 1, k + 5, wood);
         W.set(world, i + 3, j - 1, k + 6, wood);
         W.set(world, i + 3, j - 1, k + 7, wood);
         W.set(world, i + 3, j - 1, k - 1, wood);
         W.set(world, i + 3, j - 1, k - 2, wood);
         W.set(world, i + 4, j - 1, k, wood);
         W.set(world, i + 4, j - 1, k + 1, wood);
         W.set(world, i + 4, j - 1, k + 2, wood);
         W.set(world, i + 4, j - 1, k + 3, wood);
         W.set(world, i + 4, j - 1, k + 4, wood);
         W.set(world, i + 4, j - 1, k + 5, wood);
         W.set(world, i + 4, j - 1, k + 6, wood);
         W.set(world, i + 4, j - 1, k + 7, wood);
         W.set(world, i + 4, j - 1, k - 1, wood);
         W.set(world, i + 4, j - 1, k - 2, wood);
         W.set(world, i + 5, j - 1, k, wood);
         W.set(world, i + 5, j - 1, k + 1, wood);
         W.set(world, i + 5, j - 1, k + 2, wood);
         W.set(world, i + 5, j - 1, k + 3, wood);
         W.set(world, i + 5, j - 1, k + 4, wood);
         W.set(world, i + 5, j - 1, k + 5, wood);
         W.set(world, i + 5, j - 1, k + 6, wood);
         W.set(world, i + 5, j - 1, k + 7, wood);
         W.set(world, i + 5, j - 1, k - 1, wood);
         W.set(world, i + 5, j - 1, k - 2, wood);
         W.set(world, i + 1, j, k - 3, wood);
         W.set(world, i + 5, j, k - 3, wood);
         W.set(world, i + 4, j - 1, k - 3, wood);
         W.set(world, i + 2, j - 1, k - 3, wood);
         W.set(world, i + 3, j - 1, k - 3, wood);
         W.set(world, i + 1, j, k - 4, wood);
         W.set(world, i + 5, j, k - 4, wood);
         W.set(world, i + 4, j - 1, k - 4, wood);
         W.set(world, i + 2, j - 1, k - 4, wood);
         W.set(world, i + 3, j - 1, k - 4, wood);
         W.set(world, i, j, k + 8, wood);
         W.set(world, i + 1, j, k + 8, wood);
         W.set(world, i + 2, j, k + 8, wood);
         W.set(world, i + 3, j, k + 8, wood);
         W.set(world, i + 4, j, k + 8, wood);
         W.set(world, i + 5, j, k + 8, wood);
         W.set(world, i + 6, j, k + 8, wood);
         W.set(world, i + 4, j, k - 5, wood);
         W.set(world, i + 2, j, k - 5, wood);
         W.set(world, i + 3, j - 1, k - 5, wood);
         W.set(world, i + 4, j, k - 6, wood);
         W.set(world, i + 2, j, k - 6, wood);
         W.set(world, i + 3, j - 1, k - 6, wood);
         W.set(world, i + 3, j, k - 7, wood);
         W.set(world, i + 3, j, k - 8, wood);
         W.set(world, i, j + 1, k + 8, wood);
         W.set(world, i + 6, j + 1, k + 8, wood);
         W.set(world, i, j + 1, k + 5, wood);
         W.set(world, i + 2, j + 1, k + 5, wood);
         W.set(world, i + 4, j + 1, k + 5, wood);
         W.set(world, i + 6, j + 1, k + 5, wood);
         W.set(world, i + 1, j, k + 5, wood);
         W.set(world, i + 2, j, k + 5, wood);
         W.set(world, i + 4, j, k + 5, wood);
         W.set(world, i + 5, j, k + 5, wood);
         W.set(world, i + 1, j + 2, k + 7, half, 0, 0);
         W.set(world, i + 5, j + 2, k + 7, half, 0, 0);
         W.set(world, i + 1, j + 2, k + 6, half, 0, 0);
         W.set(world, i + 5, j + 2, k + 6, half, 0, 0);
         W.set(world, i + 2, j + 2, k + 7, half, 0, 0);
         W.set(world, i + 4, j + 2, k + 7, half, 0, 0);
         W.set(world, i + 2, j + 2, k + 6, half, 0, 0);
         W.set(world, i + 4, j + 2, k + 6, half, 0, 0);
         W.set(world, i + 3, j + 2, k + 7, half, 0, 0);
         W.set(world, i + 3, j + 2, k + 6, half, 0, 0);
         W.set(world, i, j + 2, k + 5, wood);
         W.set(world, i + 1, j + 2, k + 5, wood);
         W.set(world, i + 2, j + 2, k + 5, wood);
         W.set(world, i + 3, j + 2, k + 5, wood);
         W.set(world, i + 4, j + 2, k + 5, wood);
         W.set(world, i + 5, j + 2, k + 5, wood);
         W.set(world, i + 6, j + 2, k + 5, wood);
         W.set(world, i + 6, j + 2, k + 8, wood);
         W.set(world, i, j + 2, k + 8, wood);
         W.set(world, i, j + 3, k + 6, woody, 8, 8);
         W.set(world, i, j + 3, k + 7, woody, 8, 8);
         W.set(world, i + 6, j + 3, k + 6, woody, 8, 8);
         W.set(world, i + 6, j + 3, k + 7, woody, 8, 8);
         W.set(world, i + 1, j + 3, k + 8, woody, 4, 4);
         W.set(world, i + 2, j + 3, k + 8, woody, 4, 4);
         W.set(world, i + 3, j + 3, k + 8, woody, 4, 4);
         W.set(world, i + 4, j + 3, k + 8, woody, 4, 4);
         W.set(world, i + 5, j + 3, k + 8, woody, 4, 4);
         W.set(world, i + 6, j + 3, k + 8, woody, 0, 0);
         W.set(world, i, j + 3, k + 8, woody, 0, 0);
         W.set(world, i + 6, j + 3, k + 5, woody, 0, 0);
         W.set(world, i, j + 3, k + 5, woody, 0, 0);
         W.set(world, i, j + 2, k + 6, wood);
         W.set(world, i, j + 2, k + 7, wood);
         W.set(world, i + 6, j + 2, k + 6, wood);
         W.set(world, i + 6, j + 2, k + 7, wood);
         W.set(world, i + 1, j + 2, k + 8, wood);
         W.set(world, i + 2, j + 2, k + 8, wood);
         W.set(world, i + 3, j + 2, k + 8, wood);
         W.set(world, i + 4, j + 2, k + 8, wood);
         W.set(world, i + 5, j + 2, k + 8, wood);
         W.set(world, i + 3, j, k + 2, woody, 0, 0);
         W.set(world, i + 3, j + 1, k + 2, woody, 0, 0);
         W.set(world, i + 3, j + 2, k + 2, woody, 0, 0);
         W.set(world, i + 3, j + 3, k + 2, woody, 0, 0);
         W.set(world, i + 3, j + 4, k + 2, woody, 0, 0);
         W.set(world, i + 3, j + 5, k + 2, woody, 0, 0);
         W.set(world, i + 3, j + 6, k + 2, woody, 0, 0);
         W.set(world, i, j + 2, k + 1, wood);
         W.set(world, i + 1, j + 2, k + 1, wood);
         W.set(world, i + 2, j + 2, k + 1, wood);
         W.set(world, i + 3, j + 2, k + 1, wood);
         W.set(world, i + 4, j + 2, k + 1, wood);
         W.set(world, i + 5, j + 2, k + 1, wood);
         W.set(world, i + 6, j + 2, k + 1, wood);
         W.set(world, i, j + 6, k + 1, wood);
         W.set(world, i + 1, j + 6, k + 1, wood);
         W.set(world, i + 2, j + 6, k + 1, wood);
         W.set(world, i + 3, j + 6, k + 1, wood);
         W.set(world, i + 4, j + 6, k + 1, wood);
         W.set(world, i + 5, j + 6, k + 1, wood);
         W.set(world, i + 6, j + 6, k + 1, wood);
         W.set(world, i + 3, j, k + 1, PiratesContent.cursedChest);
         TileCursedChest tileentitychest = (TileCursedChest)W.te(world, i + 3, j, k + 1);
         if (tileentitychest == null) return true;

         for (int l3 = 0; l3 < 10; l3++) {
            ItemStack itemstack = this.pickCheckLootItem(random);
            if (itemstack != null) {
               tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private ItemStack pickCheckLootItem(Random random) {
      int i = random.nextInt(13);
      if (i == 0) {
         return new ItemStack(Items.GOLD_INGOT);
      } else if (i == 1) {
         return new ItemStack(Items.IRON_INGOT, random.nextInt(4) + 1);
      } else if (i == 2) {
         return new ItemStack(Items.EMERALD, random.nextInt(3) + 1);
      } else if (i == 3) {
         return new ItemStack(PiratesContent.Cutlass, 1);
      } else if (i == 4) {
         return new ItemStack(Items.IRON_SWORD, 1);
      } else if (i == 5) {
         return new ItemStack(PiratesContent.IronPlate, random.nextInt(4) + 1);
      } else if (i == 6) {
         return new ItemStack(Items.BUCKET);
      } else if (i == 7 && random.nextInt(3) == 0) {
         return new ItemStack(Items.DIAMOND);
      } else if (i == 8 && random.nextInt(2) == 0) {
         return new ItemStack(Items.GOLD_NUGGET, random.nextInt(4) + 1);
      } else if (i == 9 && random.nextInt(10) == 0) {
         return new ItemStack(Items.GOLD_INGOT, random.nextInt(3) * 9);
      } else if (i == 10) {
         return new ItemStack(PiratesContent.barrel, random.nextInt(2) + 1);
      } else if (i == 11) {
         return new ItemStack(PiratesContent.blade, random.nextInt(2) + 1);
      } else {
         return i == 12 ? new ItemStack(PiratesContent.Hook, random.nextInt(2) + 1) : null;
      }
   }
}
