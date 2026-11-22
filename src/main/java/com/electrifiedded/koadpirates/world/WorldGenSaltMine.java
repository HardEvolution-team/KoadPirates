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

public class WorldGenSaltMine {
   Block salt = PiratesContent.saltBlock;
   int size = 7;
   int hight = 4;
   int InXpos = 0;
   int InYpos = 0;
   int InZpos = 0;

   public boolean generate(World world, Random random, int x, int y, int z) {
      if (random.nextInt(2) == 0) {
         this.size = random.nextInt(4) + 5;
         this.hight = random.nextInt(4) + 3;
         if (W.get(world, x, y - 1, z) == Blocks.STONE
            && W.get(world, x, y + this.hight, z) == Blocks.STONE
            && W.get(world, x - 1, y - 1, z) == Blocks.STONE
            && W.get(world, x + this.size, y + this.hight, z) == Blocks.STONE
            && W.get(world, x, y - 1, z - 1) == Blocks.STONE
            && W.get(world, x, y + this.hight, z + this.size) == Blocks.STONE) {
            this.generateBase(world, random, x, y, z);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void generateBase(World world, Random random, int x, int y, int z) {
      for (int Xplace = x; Xplace <= x + this.size; Xplace++) {
         for (int Zplace = z; Zplace <= z + this.size; Zplace++) {
            if (this.randomChance(random)) {
               W.set(world, Xplace, y, Zplace, this.salt);
            }
         }
      }

      for (int Xplace = x; Xplace <= x + this.size; Xplace++) {
         for (int Zplacex = z; Zplacex <= z + this.size; Zplacex++) {
            if (this.randomChance(random)) {
               W.set(world, Xplace, y + 1 + this.hight, Zplacex, this.salt);
            }
         }
      }

      for (int Xplace = x + 1; Xplace <= x + this.size - 1; Xplace++) {
         for (int Zplacexx = z + 1; Zplacexx <= z + this.size - 1; Zplacexx++) {
            if (this.randomChance(random)) {
               W.set(world, Xplace, y - 1, Zplacexx, this.salt);
            }
         }
      }

      for (int Xplace = x + 1; Xplace <= x + this.size - 1; Xplace++) {
         for (int Zplacexxx = z + 1; Zplacexxx <= z + this.size - 1; Zplacexxx++) {
            if (this.randomChance(random)) {
               W.set(world, Xplace, y + 2 + this.hight, Zplacexxx, this.salt);
            }
         }
      }

      for (int Yplace = y + 1; Yplace <= y + 1 + this.hight; Yplace++) {
         for (int Xplace = x; Xplace <= x + this.size; Xplace++) {
            if (this.randomChance(random)) {
               W.set(world, Xplace, Yplace, z, this.salt);
            }
         }

         for (int Xplacex = x; Xplacex <= x + this.size; Xplacex++) {
            if (this.randomChance(random)) {
               W.set(world, Xplacex, Yplace, z + this.size, this.salt);
            }
         }

         for (int Zplacexxxx = z; Zplacexxxx <= z + this.size; Zplacexxxx++) {
            if (this.randomChance(random)) {
               W.set(world, x, Yplace, Zplacexxxx, this.salt);
            }
         }

         for (int Zplacexxxxx = z; Zplacexxxxx <= z + this.size; Zplacexxxxx++) {
            if (this.randomChance(random)) {
               W.set(world, x + this.size, Yplace, Zplacexxxxx, this.salt);
            }
         }
      }

      for (int Yplace = y + 1; Yplace <= y + this.hight; Yplace++) {
         for (int Xplacexx = x + 1; Xplacexx <= x + this.size - 1; Xplacexx++) {
            if (this.randomChance(random)) {
               W.set(world, Xplacexx, Yplace, z - 1, this.salt);
            }
         }

         for (int Xplacexxx = x + 1; Xplacexxx <= x + this.size - 1; Xplacexxx++) {
            if (this.randomChance(random)) {
               W.set(world, Xplacexxx, Yplace, z + this.size + 1, this.salt);
            }
         }

         for (int Zplacexxxxxx = z + 1; Zplacexxxxxx <= z + this.size - 1; Zplacexxxxxx++) {
            if (this.randomChance(random)) {
               W.set(world, x - 1, Yplace, Zplacexxxxxx, this.salt);
            }
         }

         for (int Zplacexxxxxxx = z + 1; Zplacexxxxxxx <= z + this.size - 1; Zplacexxxxxxx++) {
            if (this.randomChance(random)) {
               W.set(world, x + this.size + 1, Yplace, Zplacexxxxxxx, this.salt);
            }
         }
      }

      if (random.nextInt(3) != 2) {
         for (int Yplace = y + 1; Yplace <= y + this.hight; Yplace++) {
            for (int Xplacexxxx = x; Xplacexxxx <= x + this.size; Xplacexxxx++) {
               for (int Zplacexxxxxxxx = z; Zplacexxxxxxxx <= z + this.size; Zplacexxxxxxxx++) {
                  if (this.randomChance(random)) {
                     if (W.get(world, Xplacexxxx, Yplace, Zplacexxxxxxxx) != PiratesContent.saltBlock) {
                        W.air(world, Xplacexxxx, Yplace, Zplacexxxxxxxx);
                     }
                  } else if (random.nextInt(3) == 1) {
                     W.set(world, Xplacexxxx, Yplace, Zplacexxxxxxxx, Blocks.STONE);
                  } else {
                     W.set(world, Xplacexxxx, Yplace, Zplacexxxxxxxx, this.salt);
                  }
               }
            }
         }
      } else {
         for (int Yplace = y; Yplace <= y + this.hight; Yplace++) {
            for (int Xplacexxxx = x; Xplacexxxx <= x + this.size; Xplacexxxx++) {
               for (int Zplacexxxxxxxxx = z; Zplacexxxxxxxxx <= z + this.size; Zplacexxxxxxxxx++) {
                  if (W.get(world, Xplacexxxx, Yplace, Zplacexxxxxxxxx) != PiratesContent.saltBlock && random.nextInt(3) == 1) {
                     W.air(world, Xplacexxxx, Yplace, Zplacexxxxxxxxx);
                  }
               }
            }
         }

         for (int num = 0; num <= 20; num++) {
            this.getRandomLocationInside(random, x, y, z);
            int thingsInside = random.nextInt(20);
            if (thingsInside == 0) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.PLANKS);
            }

            if (thingsInside == 1) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.RAIL);
            }

            if (thingsInside == 2) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.OAK_FENCE);
            }

            if (thingsInside == 3) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.OAK_STAIRS);
            }

            if (thingsInside == 4) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.FURNACE);
            }

            if (thingsInside == 5) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.LOG);
            }

            if (thingsInside == 6) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.LEVER);
            }

            if (thingsInside == 7 && random.nextInt(5) == 1) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.SKULL);
            }

            if (thingsInside == 8) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.TORCH);
            }

            if (thingsInside == 9) {
               W.set(world, this.InXpos, this.InYpos, this.InZpos, Blocks.CHEST);
               TileEntityChest tileentitychest = (TileEntityChest)W.te(world, this.InXpos, this.InYpos, this.InZpos);
               if (tileentitychest == null) return;

               for (int l3 = 0; l3 < 10; l3++) {
                  ItemStack itemstack = this.pickCheckLootItem(random);
                  if (itemstack != null) {
                     tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
                  }
               }
            }
         }
      }
   }

   private void getRandomLocationInside(Random random, int x, int y, int z) {
      this.InXpos = x + random.nextInt(this.size - 2) + 1;
      this.InYpos = y + random.nextInt(this.hight - 2) + 1;
      this.InZpos = z + random.nextInt(this.size - 2) + 1;
   }

   private boolean randomChance(Random random) {
      return random.nextInt(4) < 3;
   }

   private ItemStack pickCheckLootItem(Random random) {
      int i = random.nextInt(10);
      if (i == 0) {
         return new ItemStack(Items.IRON_INGOT, random.nextInt(4) + 1);
      } else if (i == 1) {
         return new ItemStack(PiratesContent.IronPlate, random.nextInt(4) + 1);
      } else if (i == 2 && random.nextInt(2) == 1) {
         return new ItemStack(Items.IRON_PICKAXE, 1);
      } else if (i == 3) {
         return new ItemStack(Items.COAL, random.nextInt(4) + 1);
      } else if (i == 4) {
         return new ItemStack(Items.FLINT, random.nextInt(4) + 1);
      } else if (i == 5) {
         return new ItemStack(Items.IRON_SHOVEL, 1);
      } else {
         return i == 6 ? new ItemStack(Items.PAPER, random.nextInt(4) + 1) : null;
      }
   }
}
