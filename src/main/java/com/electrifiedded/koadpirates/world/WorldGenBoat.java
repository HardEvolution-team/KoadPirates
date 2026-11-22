package com.electrifiedded.koadpirates.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import com.electrifiedded.koadpirates.PiratesContent;
import com.electrifiedded.koadpirates.tile.*;

public class WorldGenBoat {
   public boolean generate(World world, Random random, int i, int j, int k) {
      if (random.nextInt(2) == 0) {
         return false;
      } else {
         if (W.get(world, i, j - 1, k + 7) == Blocks.WATER
            && W.get(world, i, j, k) == Blocks.AIR
            && W.get(world, i + 6, j - 1, k + 7) == Blocks.WATER
            && W.get(world, i, j - 1, k - 2) == Blocks.WATER
            && W.get(world, i + 6, j - 1, k - 2) == Blocks.WATER
            && W.get(world, i + 3, j - 1, k - 2) == Blocks.WATER
            && W.get(world, i + 3, j - 1, k + 7) == Blocks.WATER) {
            Block wood = Blocks.PLANKS;
            Block stairs = Blocks.OAK_STAIRS;
            Block half = Blocks.WOODEN_SLAB;
            Block ladder = Blocks.LADDER;
            Block woody = Blocks.LOG;
            Block wool = Blocks.WOOL;
            Block rail = PiratesContent.Hull;
            int Rot = random.nextInt(4);
            if (Rot == 0) {
               W.set(world, i, j, k, rail);
               W.set(world, i, j, k + 1, rail);
               W.set(world, i, j, k + 2, rail);
               W.set(world, i, j, k + 3, rail);
               W.set(world, i, j, k + 4, wood);
               W.set(world, i, j, k + 5, wood);
               W.set(world, i, j, k + 6, wood);
               W.set(world, i, j, k + 7, wood);
               W.set(world, i, j, k - 1, rail);
               W.set(world, i, j, k - 2, rail);
               W.set(world, i + 6, j, k, rail);
               W.set(world, i + 6, j, k + 1, rail);
               W.set(world, i + 6, j, k + 2, rail);
               W.set(world, i + 6, j, k + 3, rail);
               W.set(world, i + 6, j, k + 4, wood);
               W.set(world, i + 6, j, k + 5, wood);
               W.set(world, i + 6, j, k + 6, wood);
               W.set(world, i + 6, j, k + 7, wood);
               W.set(world, i + 6, j, k - 1, rail);
               W.set(world, i + 6, j, k - 2, rail);
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
               W.set(world, i + 1, j, k - 3, rail);
               W.set(world, i + 5, j, k - 3, rail);
               W.set(world, i + 4, j - 1, k - 3, wood);
               W.set(world, i + 2, j - 1, k - 3, wood);
               W.set(world, i + 3, j - 1, k - 3, wood);
               W.set(world, i + 1, j, k - 4, rail);
               W.set(world, i + 5, j, k - 4, rail);
               W.set(world, i + 4, j - 1, k - 4, wood);
               W.set(world, i + 2, j - 1, k - 4, wood);
               W.set(world, i + 3, j - 1, k - 4, wood);
               W.set(world, i + 4, j, k - 4, half);
               W.set(world, i + 2, j, k - 4, half);
               W.set(world, i, j, k + 8, wood);
               W.set(world, i + 1, j, k + 8, wood);
               W.set(world, i + 2, j, k + 8, wood);
               W.set(world, i + 3, j, k + 8, wood);
               W.set(world, i + 4, j, k + 8, wood);
               W.set(world, i + 5, j, k + 8, wood);
               W.set(world, i + 6, j, k + 8, wood);
               W.set(world, i + 4, j, k - 5, rail);
               W.set(world, i + 2, j, k - 5, rail);
               W.set(world, i + 3, j - 1, k - 5, wood);
               W.set(world, i + 4, j, k - 6, rail);
               W.set(world, i + 2, j, k - 6, rail);
               W.set(world, i + 3, j - 1, k - 6, wood);
               W.set(world, i + 3, j, k - 7, wood);
               W.set(world, i + 3, j, k - 6, stairs, 3, 2);
               W.set(world, i + 3, j, k - 8, stairs, 6, 2);
               W.set(world, i + 3, j + 1, k - 7, half, 0, 2);
               W.set(world, i + 3, j + 1, k - 8, half, 0, 2);
               W.set(world, i, j + 1, k + 8, wood);
               W.set(world, i + 1, j + 1, k + 8, Blocks.GLASS_PANE);
               W.set(world, i + 2, j + 1, k + 8, Blocks.GLASS_PANE);
               W.set(world, i + 3, j + 1, k + 8, Blocks.GLASS_PANE);
               W.set(world, i + 4, j + 1, k + 8, Blocks.GLASS_PANE);
               W.set(world, i + 5, j + 1, k + 8, Blocks.GLASS_PANE);
               W.set(world, i + 6, j + 1, k + 8, wood);
               W.set(world, i, j + 1, k + 5, wood);
               W.set(world, i + 1, j + 1, k + 5, Blocks.GLASS_PANE);
               W.set(world, i + 2, j + 1, k + 5, wood);
               W.set(world, i + 4, j + 1, k + 5, wood);
               W.set(world, i + 5, j + 1, k + 5, Blocks.GLASS_PANE);
               W.set(world, i + 6, j + 1, k + 5, wood);
               W.set(world, i + 1, j, k + 5, wood);
               W.set(world, i + 2, j, k + 5, wood);
               W.set(world, i + 4, j, k + 5, wood);
               W.set(world, i + 5, j, k + 5, wood);
               W.set(world, i, j + 1, k + 7, Blocks.GLASS_PANE);
               W.set(world, i + 6, j + 1, k + 7, Blocks.GLASS_PANE);
               W.set(world, i, j + 1, k + 6, Blocks.GLASS_PANE);
               W.set(world, i + 6, j + 1, k + 6, Blocks.GLASS_PANE);
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
               W.set(world, i, j + 2, k + 6, stairs, 1, 0);
               W.set(world, i, j + 2, k + 7, stairs, 1, 0);
               W.set(world, i + 6, j + 2, k + 6, stairs, 0, 0);
               W.set(world, i + 6, j + 2, k + 7, stairs, 0, 0);
               W.set(world, i + 1, j + 2, k + 8, stairs, 2, 0);
               W.set(world, i + 2, j + 2, k + 8, stairs, 2, 0);
               W.set(world, i + 3, j + 2, k + 8, stairs, 2, 0);
               W.set(world, i + 4, j + 2, k + 8, stairs, 2, 0);
               W.set(world, i + 5, j + 2, k + 8, stairs, 2, 0);
               W.set(world, i, j + 1, k + 4, stairs, 2, 0);
               W.set(world, i + 6, j + 1, k + 4, stairs, 2, 0);
               W.set(world, i + 4, j + 1, k + 4, ladder, 2, 0);
               W.set(world, i + 2, j + 1, k + 4, ladder, 2, 0);
               W.set(world, i + 4, j + 2, k + 4, ladder, 2, 0);
               W.set(world, i + 2, j + 2, k + 4, ladder, 2, 0);
               W.set(world, i + 4, j, k + 4, ladder, 2, 0);
               W.set(world, i + 2, j, k + 4, ladder, 2, 0);
               W.set(world, i, j + 3, k + 5, half, 0, 0);
               W.set(world, i, j + 3, k + 8, half, 0, 0);
               W.set(world, i + 6, j + 3, k + 5, half, 0, 0);
               W.set(world, i + 6, j + 3, k + 8, half, 0, 0);
               W.set(world, i + 3, j, k + 2, woody, 0, 0);
               W.set(world, i + 3, j + 1, k + 2, woody, 0, 0);
               W.set(world, i + 3, j + 2, k + 2, woody, 0, 0);
               W.set(world, i + 3, j + 3, k + 2, woody, 0, 0);
               W.set(world, i + 3, j + 4, k + 2, woody, 0, 0);
               W.set(world, i + 3, j + 5, k + 2, woody, 0, 0);
               W.set(world, i + 3, j + 6, k + 2, woody, 0, 0);
               W.set(world, i + 3, j, k + 3, ladder, 3, 0);
               W.set(world, i + 3, j + 1, k + 3, ladder, 3, 0);
               W.set(world, i + 3, j + 2, k + 3, ladder, 3, 0);
               W.set(world, i + 3, j + 3, k + 3, ladder, 3, 0);
               W.set(world, i + 3, j + 4, k + 3, ladder, 3, 0);
               W.set(world, i + 3, j + 5, k + 3, ladder, 3, 0);
               W.set(world, i + 3, j + 6, k + 3, ladder, 3, 0);
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
               W.set(world, i, j + 5, k, wool, 0, 0);
               W.set(world, i + 1, j + 5, k, wool, 0, 0);
               W.set(world, i + 2, j + 5, k, wool, 0, 0);
               W.set(world, i + 3, j + 5, k, wool, 0, 0);
               W.set(world, i + 4, j + 5, k, wool, 0, 0);
               W.set(world, i + 5, j + 5, k, wool, 0, 0);
               W.set(world, i + 6, j + 5, k, wool, 0, 0);
               W.set(world, i, j + 4, k, wool, 0, 0);
               W.set(world, i + 1, j + 4, k, wool, 0, 0);
               W.set(world, i + 2, j + 4, k, wool, 0, 0);
               W.set(world, i + 3, j + 4, k, wool, 0, 0);
               W.set(world, i + 4, j + 4, k, wool, 0, 0);
               W.set(world, i + 5, j + 4, k, wool, 0, 0);
               W.set(world, i + 6, j + 4, k, wool, 0, 0);
               W.set(world, i, j + 3, k, wool, 0, 0);
               W.set(world, i + 1, j + 3, k, wool, 0, 0);
               W.set(world, i + 2, j + 3, k, wool, 0, 0);
               W.set(world, i + 3, j + 3, k, wool, 0, 0);
               W.set(world, i + 4, j + 3, k, wool, 0, 0);
               W.set(world, i + 5, j + 3, k, wool, 0, 0);
               W.set(world, i + 6, j + 3, k, wool, 0, 0);
               W.set(world, i + 3, j, k + 1, Blocks.CHEST);
               TileEntityChest tileentitychest = (TileEntityChest)W.te(world, i + 3, j, k + 1);
               if (tileentitychest == null) return true;

               for (int l3 = 0; l3 < 20; l3++) {
                  ItemStack itemstack = this.pickCheckLootItem(random);
                  if (itemstack != null) {
                     tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
                  }
               }

               ItemStack ish = new ItemStack(PiratesContent.DavyCode, 1);
               tileentitychest.setInventorySlotContents(1, ish);
               W.set(world, i + 3, j, k - 1, PiratesContent.MobSpawnerPirate);
               TilePirateSpawner tileentitymobspawner = (TilePirateSpawner)W.te(world, i + 3, j, k - 1);
               if (tileentitymobspawner != null) {
                  tileentitymobspawner.setMobID(this.pickMobSpawner(random));
               } else {
                  System.err.println("Failed to fetch mob spawner entity at (" + i + ", " + j + ", " + k + ")");
               }

               return true;
            }

            if (Rot == 1) {
               W.set(world, i, j, k, rail);
               W.set(world, i, j, k - 1, rail);
               W.set(world, i, j, k - 2, rail);
               W.set(world, i, j, k - 3, rail);
               W.set(world, i, j, k - 4, wood);
               W.set(world, i, j, k - 5, wood);
               W.set(world, i, j, k - 6, wood);
               W.set(world, i, j, k - 7, wood);
               W.set(world, i, j, k + 1, rail);
               W.set(world, i, j, k + 2, rail);
               W.set(world, i - 6, j, k, rail);
               W.set(world, i - 6, j, k - 1, rail);
               W.set(world, i - 6, j, k - 2, rail);
               W.set(world, i - 6, j, k - 3, rail);
               W.set(world, i - 6, j, k - 4, wood);
               W.set(world, i - 6, j, k - 5, wood);
               W.set(world, i - 6, j, k - 6, wood);
               W.set(world, i - 6, j, k - 7, wood);
               W.set(world, i - 6, j, k + 1, rail);
               W.set(world, i - 6, j, k + 2, rail);
               W.set(world, i - 1, j - 1, k, wood);
               W.set(world, i - 1, j - 1, k - 1, wood);
               W.set(world, i - 1, j - 1, k - 2, wood);
               W.set(world, i - 1, j - 1, k - 3, wood);
               W.set(world, i - 1, j - 1, k - 4, wood);
               W.set(world, i - 1, j - 1, k - 5, wood);
               W.set(world, i - 1, j - 1, k - 6, wood);
               W.set(world, i - 1, j - 1, k - 7, wood);
               W.set(world, i - 1, j - 1, k + 1, wood);
               W.set(world, i - 1, j - 1, k + 2, wood);
               W.set(world, i - 2, j - 1, k, wood);
               W.set(world, i - 2, j - 1, k - 1, wood);
               W.set(world, i - 2, j - 1, k - 2, wood);
               W.set(world, i - 2, j - 1, k - 3, wood);
               W.set(world, i - 2, j - 1, k - 4, wood);
               W.set(world, i - 2, j - 1, k - 5, wood);
               W.set(world, i - 2, j - 1, k - 6, wood);
               W.set(world, i - 2, j - 1, k - 7, wood);
               W.set(world, i - 2, j - 1, k + 1, wood);
               W.set(world, i - 2, j - 1, k + 2, wood);
               W.set(world, i - 3, j - 1, k, wood);
               W.set(world, i - 3, j - 1, k - 1, wood);
               W.set(world, i - 3, j - 1, k - 2, wood);
               W.set(world, i - 3, j - 1, k - 3, wood);
               W.set(world, i - 3, j - 1, k - 4, wood);
               W.set(world, i - 3, j - 1, k - 5, wood);
               W.set(world, i - 3, j - 1, k - 6, wood);
               W.set(world, i - 3, j - 1, k - 7, wood);
               W.set(world, i - 3, j - 1, k + 1, wood);
               W.set(world, i - 3, j - 1, k + 2, wood);
               W.set(world, i - 4, j - 1, k, wood);
               W.set(world, i - 4, j - 1, k - 1, wood);
               W.set(world, i - 4, j - 1, k - 2, wood);
               W.set(world, i - 4, j - 1, k - 3, wood);
               W.set(world, i - 4, j - 1, k - 4, wood);
               W.set(world, i - 4, j - 1, k - 5, wood);
               W.set(world, i - 4, j - 1, k - 6, wood);
               W.set(world, i - 4, j - 1, k - 7, wood);
               W.set(world, i - 4, j - 1, k + 1, wood);
               W.set(world, i - 4, j - 1, k + 2, wood);
               W.set(world, i - 5, j - 1, k, wood);
               W.set(world, i - 5, j - 1, k - 1, wood);
               W.set(world, i - 5, j - 1, k - 2, wood);
               W.set(world, i - 5, j - 1, k - 3, wood);
               W.set(world, i - 5, j - 1, k - 4, wood);
               W.set(world, i - 5, j - 1, k - 5, wood);
               W.set(world, i - 5, j - 1, k - 6, wood);
               W.set(world, i - 5, j - 1, k - 7, wood);
               W.set(world, i - 5, j - 1, k + 1, wood);
               W.set(world, i - 5, j - 1, k + 2, wood);
               W.set(world, i - 1, j, k + 3, rail);
               W.set(world, i - 5, j, k + 3, rail);
               W.set(world, i - 4, j - 1, k + 3, wood);
               W.set(world, i - 2, j - 1, k + 3, wood);
               W.set(world, i - 3, j - 1, k + 3, wood);
               W.set(world, i - 1, j, k + 4, rail);
               W.set(world, i - 5, j, k + 4, rail);
               W.set(world, i - 4, j - 1, k + 4, wood);
               W.set(world, i - 2, j - 1, k + 4, wood);
               W.set(world, i - 3, j - 1, k + 4, wood);
               W.set(world, i - 4, j, k + 4, half);
               W.set(world, i - 2, j, k + 4, half);
               W.set(world, i, j, k - 8, wood);
               W.set(world, i - 1, j, k - 8, wood);
               W.set(world, i - 2, j, k - 8, wood);
               W.set(world, i - 3, j, k - 8, wood);
               W.set(world, i - 4, j, k - 8, wood);
               W.set(world, i - 5, j, k - 8, wood);
               W.set(world, i - 6, j, k - 8, wood);
               W.set(world, i - 4, j, k + 5, rail);
               W.set(world, i - 2, j, k + 5, rail);
               W.set(world, i - 3, j - 1, k + 5, wood);
               W.set(world, i - 4, j, k + 6, rail);
               W.set(world, i - 2, j, k + 6, rail);
               W.set(world, i - 3, j - 1, k + 6, wood);
               W.set(world, i - 3, j, k + 7, wood);
               W.set(world, i - 3, j, k + 6, stairs, 2, 0);
               W.set(world, i - 3, j, k + 8, stairs, 7, 0);
               W.set(world, i - 3, j + 1, k + 7, half, 0, 0);
               W.set(world, i - 3, j + 1, k + 8, half, 0, 0);
               W.set(world, i, j + 1, k - 8, wood);
               W.set(world, i - 1, j + 1, k - 8, Blocks.GLASS_PANE);
               W.set(world, i - 2, j + 1, k - 8, Blocks.GLASS_PANE);
               W.set(world, i - 3, j + 1, k - 8, Blocks.GLASS_PANE);
               W.set(world, i - 4, j + 1, k - 8, Blocks.GLASS_PANE);
               W.set(world, i - 5, j + 1, k - 8, Blocks.GLASS_PANE);
               W.set(world, i - 6, j + 1, k - 8, wood);
               W.set(world, i, j + 1, k - 5, wood);
               W.set(world, i - 1, j + 1, k - 5, Blocks.GLASS_PANE);
               W.set(world, i - 2, j + 1, k - 5, wood);
               W.set(world, i - 4, j + 1, k - 5, wood);
               W.set(world, i - 5, j + 1, k - 5, Blocks.GLASS_PANE);
               W.set(world, i - 6, j + 1, k - 5, wood);
               W.set(world, i - 1, j, k - 5, wood);
               W.set(world, i - 2, j, k - 5, wood);
               W.set(world, i - 4, j, k - 5, wood);
               W.set(world, i - 5, j, k - 5, wood);
               W.set(world, i, j + 1, k - 7, Blocks.GLASS_PANE);
               W.set(world, i - 6, j + 1, k - 7, Blocks.GLASS_PANE);
               W.set(world, i, j + 1, k - 6, Blocks.GLASS_PANE);
               W.set(world, i - 6, j + 1, k - 6, Blocks.GLASS_PANE);
               W.set(world, i - 1, j + 2, k - 7, half, 0, 0);
               W.set(world, i - 5, j + 2, k - 7, half, 0, 0);
               W.set(world, i - 1, j + 2, k - 6, half, 0, 0);
               W.set(world, i - 5, j + 2, k - 6, half, 0, 0);
               W.set(world, i - 2, j + 2, k - 7, half, 0, 0);
               W.set(world, i - 4, j + 2, k - 7, half, 0, 0);
               W.set(world, i - 2, j + 2, k - 6, half, 0, 0);
               W.set(world, i - 4, j + 2, k - 6, half, 0, 0);
               W.set(world, i - 3, j + 2, k - 7, half, 0, 0);
               W.set(world, i - 3, j + 2, k - 6, half, 0, 0);
               W.set(world, i, j + 2, k - 5, wood);
               W.set(world, i - 1, j + 2, k - 5, wood);
               W.set(world, i - 2, j + 2, k - 5, wood);
               W.set(world, i - 3, j + 2, k - 5, wood);
               W.set(world, i - 4, j + 2, k - 5, wood);
               W.set(world, i - 5, j + 2, k - 5, wood);
               W.set(world, i - 6, j + 2, k - 5, wood);
               W.set(world, i - 6, j + 2, k - 8, wood);
               W.set(world, i, j + 2, k - 8, wood);
               W.set(world, i, j + 2, k - 6, stairs, 0, 0);
               W.set(world, i, j + 2, k - 7, stairs, 0, 0);
               W.set(world, i - 6, j + 2, k - 6, stairs, 1, 0);
               W.set(world, i - 6, j + 2, k - 7, stairs, 1, 0);
               W.set(world, i - 1, j + 2, k - 8, stairs, 3, 0);
               W.set(world, i - 2, j + 2, k - 8, stairs, 3, 0);
               W.set(world, i - 3, j + 2, k - 8, stairs, 3, 0);
               W.set(world, i - 4, j + 2, k - 8, stairs, 3, 0);
               W.set(world, i - 5, j + 2, k - 8, stairs, 3, 0);
               W.set(world, i, j + 1, k - 4, stairs, 3, 0);
               W.set(world, i - 6, j + 1, k - 4, stairs, 3, 0);
               W.set(world, i - 4, j + 1, k - 4, ladder, 3, 0);
               W.set(world, i - 2, j + 1, k - 4, ladder, 3, 0);
               W.set(world, i - 4, j + 2, k - 4, ladder, 3, 0);
               W.set(world, i - 2, j + 2, k - 4, ladder, 3, 0);
               W.set(world, i - 4, j, k - 4, ladder, 3, 0);
               W.set(world, i - 2, j, k - 4, ladder, 3, 0);
               W.set(world, i, j + 3, k - 5, half, 0, 0);
               W.set(world, i, j + 3, k - 8, half, 0, 0);
               W.set(world, i - 6, j + 3, k - 5, half, 0, 0);
               W.set(world, i - 6, j + 3, k - 8, half, 0, 0);
               W.set(world, i - 3, j, k - 2, woody, 0, 0);
               W.set(world, i - 3, j + 1, k - 2, woody, 0, 0);
               W.set(world, i - 3, j + 2, k - 2, woody, 0, 0);
               W.set(world, i - 3, j + 3, k - 2, woody, 0, 0);
               W.set(world, i - 3, j + 4, k - 2, woody, 0, 0);
               W.set(world, i - 3, j + 5, k - 2, woody, 0, 0);
               W.set(world, i - 3, j + 6, k - 2, woody, 0, 0);
               W.set(world, i - 3, j, k - 3, ladder, 2, 0);
               W.set(world, i - 3, j + 1, k - 3, ladder, 2, 0);
               W.set(world, i - 3, j + 2, k - 3, ladder, 2, 0);
               W.set(world, i - 3, j + 3, k - 3, ladder, 2, 0);
               W.set(world, i - 3, j + 4, k - 3, ladder, 2, 0);
               W.set(world, i - 3, j + 5, k - 3, ladder, 2, 0);
               W.set(world, i - 3, j + 6, k - 3, ladder, 2, 0);
               W.set(world, i, j + 2, k - 1, wood);
               W.set(world, i - 1, j + 2, k - 1, wood);
               W.set(world, i - 2, j + 2, k - 1, wood);
               W.set(world, i - 3, j + 2, k - 1, wood);
               W.set(world, i - 4, j + 2, k - 1, wood);
               W.set(world, i - 5, j + 2, k - 1, wood);
               W.set(world, i - 6, j + 2, k - 1, wood);
               W.set(world, i, j + 6, k - 1, wood);
               W.set(world, i - 1, j + 6, k - 1, wood);
               W.set(world, i - 2, j + 6, k - 1, wood);
               W.set(world, i - 3, j + 6, k - 1, wood);
               W.set(world, i - 4, j + 6, k - 1, wood);
               W.set(world, i - 5, j + 6, k - 1, wood);
               W.set(world, i - 6, j + 6, k - 1, wood);
               W.set(world, i, j + 5, k, wool, 0, 0);
               W.set(world, i - 1, j + 5, k, wool, 0, 0);
               W.set(world, i - 2, j + 5, k, wool, 0, 0);
               W.set(world, i - 3, j + 5, k, wool, 0, 0);
               W.set(world, i - 4, j + 5, k, wool, 0, 0);
               W.set(world, i - 5, j + 5, k, wool, 0, 0);
               W.set(world, i - 6, j + 5, k, wool, 0, 0);
               W.set(world, i, j + 4, k, wool, 0, 0);
               W.set(world, i - 1, j + 4, k, wool, 0, 0);
               W.set(world, i - 2, j + 4, k, wool, 0, 0);
               W.set(world, i - 3, j + 4, k, wool, 0, 0);
               W.set(world, i - 4, j + 4, k, wool, 0, 0);
               W.set(world, i - 5, j + 4, k, wool, 0, 0);
               W.set(world, i - 6, j + 4, k, wool, 0, 0);
               W.set(world, i, j + 3, k, wool, 0, 0);
               W.set(world, i - 1, j + 3, k, wool, 0, 0);
               W.set(world, i - 2, j + 3, k, wool, 0, 0);
               W.set(world, i - 3, j + 3, k, wool, 0, 0);
               W.set(world, i - 4, j + 3, k, wool, 0, 0);
               W.set(world, i - 5, j + 3, k, wool, 0, 0);
               W.set(world, i - 6, j + 3, k, wool, 0, 0);
               W.set(world, i - 3, j, k - 1, Blocks.CHEST);
               TileEntityChest tileentitychest = (TileEntityChest)W.te(world, i - 3, j, k - 1);
               if (tileentitychest == null) return true;

               for (int l3x = 0; l3x < 20; l3x++) {
                  ItemStack itemstack = this.pickCheckLootItem(random);
                  if (itemstack != null) {
                     tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
                  }
               }

               ItemStack ish = new ItemStack(PiratesContent.DavyCode, 1);
               tileentitychest.setInventorySlotContents(1, ish);
               W.set(world, i - 3, j, k + 1, PiratesContent.MobSpawnerPirate);
               TilePirateSpawner tileentitymobspawner = (TilePirateSpawner)W.te(world, i - 3, j, k + 1);
               if (tileentitymobspawner != null) {
                  tileentitymobspawner.setMobID(this.pickMobSpawner(random));
               } else {
                  System.err.println("Failed to fetch mob spawner entity at (" + i + ", " + j + ", " + k + ")");
               }

               return true;
            }
         }

         return false;
      }
   }

   private String pickMobSpawner(Random random) {
      int i = random.nextInt(5);
      if (i == 0) {
         return "Pirate2";
      } else if (i == 1) {
         return "Pirate2";
      } else if (i == 2) {
         return "Pirate1";
      } else if (i == 3) {
         return "Pirate1";
      } else {
         return i == 4 ? "Pirate3" : "";
      }
   }

   private ItemStack pickCheckLootItem(Random random) {
      int i = random.nextInt(12);
      if (i == 0) {
         return new ItemStack(Items.GOLD_INGOT, 1);
      } else if (i == 1) {
         return new ItemStack(Items.IRON_INGOT, random.nextInt(4) + 1);
      } else if (i == 2) {
         return new ItemStack(Items.BREAD, random.nextInt(3) + 1);
      } else if (i == 3) {
         return new ItemStack(PiratesContent.IGPT, random.nextInt(4) + 1);
      } else if (i == 4) {
         return new ItemStack(Items.GUNPOWDER, random.nextInt(4) + 1);
      } else if (i == 5) {
         return new ItemStack(PiratesContent.IronPlate, random.nextInt(4) + 1);
      } else if (i == 6) {
         return new ItemStack(Items.BUCKET);
      } else if (i == 7 && random.nextInt(100) == 0) {
         return new ItemStack(Items.DIAMOND);
      } else if (i == 8 && random.nextInt(2) == 0) {
         return new ItemStack(Items.GOLD_NUGGET, random.nextInt(4) + 1);
      } else if (i == 9 && random.nextInt(10) == 0) {
         return new ItemStack(Items.GOLD_INGOT, random.nextInt(3) * 9);
      } else if (i == 10) {
         return new ItemStack(PiratesContent.Shot, random.nextInt(6) + 3);
      } else {
         return i == 11 ? new ItemStack(PiratesContent.cucumberFruit, random.nextInt(6) + 1) : null;
      }
   }
}
