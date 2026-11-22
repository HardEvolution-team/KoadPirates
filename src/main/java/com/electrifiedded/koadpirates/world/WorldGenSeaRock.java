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

public class WorldGenSeaRock {
   int yint;
   int xint;
   int zint;
   int odds = 8;
   int odds2 = 6;
   int odds3 = 3;
   int odds4 = 10;
   int odds5 = 8;
   int odds6 = 6;
   int oddsN = 6;
   int odds7 = 10;
   int odds8 = 25;
   Block rock;

   public WorldGenSeaRock() {
      this.rock = PiratesContent.rock;
   }

   public boolean generate(World world, Random random, int i, int j, int k) {
      if (W.get(world, i, j + 1, k) == Blocks.WATER
         && W.get(world, i, j, k) == Blocks.WATER
         && W.get(world, i, j, k + 4) == Blocks.WATER
         && W.get(world, i, j, k + 2) == Blocks.WATER
         && W.get(world, i, j + 2, k) == Blocks.WATER
         && W.get(world, i, j + 3, k) == Blocks.WATER
         && W.get(world, i, j + 4, k) == Blocks.WATER
         && W.get(world, i, j + 5, k) == Blocks.WATER
         && W.get(world, i, j, k + 1) == Blocks.WATER
         && (
            W.get(world, i, j - 1, k) == Blocks.DIRT
               || W.get(world, i, j - 1, k) == Blocks.SAND
               || W.get(world, i, j - 1, k) == Blocks.CLAY
               || W.get(world, i, j - 1, k) == Blocks.GRAVEL
               || W.get(world, i, j - 1, k) == Blocks.STONE
         )) {
         int h = 8;
         int d = 7;
         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i + random.nextInt(10) + d, j2, k + random.nextInt(10) + d);
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i + random.nextInt(10) + d, j2, k);
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i + random.nextInt(10) + d, j2, k - (random.nextInt(10) + d));
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i - (random.nextInt(10) + d), j2, k + random.nextInt(10) + d);
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i - (random.nextInt(10) + d), j2, k - (random.nextInt(10) + d));
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i - (random.nextInt(10) + d), j2, k);
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i, j2, k - (random.nextInt(10) + d));
            }
         }

         if (random.nextInt(this.oddsN) == 0) {
            for (int j2 = j + h; j2 > j - h; j2--) {
               this.generate(world, random, i, j2, k + random.nextInt(10) + d);
            }
         }

         int raner = random.nextInt(18) + 4;

         for (int j2 = 0 + j; j2 < raner + j; j2++) {
            W.set(world, i, j2, k, this.rock);
            W.set(world, i + 1, j2, k - 1, this.rock);
            W.set(world, i + 1, j2, k + 1, this.rock);
            W.set(world, i + 1, j2, k, this.rock);
            W.set(world, i - 1, j2, k + 1, this.rock);
            W.set(world, i - 1, j2, k - 1, this.rock);
            W.set(world, i - 1, j2, k, this.rock);
            W.set(world, i, j2, k + 1, this.rock);
            W.set(world, i, j2, k - 1, this.rock);
            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i + 2, j2, k, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i + 2, j2, k - 1, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i + 2, j2, k + 1, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i - 2, j2, k, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i - 2, j2, k - 1, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i - 2, j2, k + 1, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i, j2, k + 2, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i - 1, j2, k + 2, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i + 1, j2, k + 2, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i, j2, k - 2, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i - 1, j2, k - 2, this.rock);
            }

            if (random.nextInt(this.odds4) != 0) {
               W.set(world, i + 1, j2, k - 2, this.rock);
            }

            W.set(world, i, j2 + 1, k, this.rock);
            W.set(world, i + 1, j2 + 1, k - 1, this.rock);
            W.set(world, i + 1, j2 + 1, k + 1, this.rock);
            W.set(world, i + 1, j2 + 1, k, this.rock);
            W.set(world, i - 1, j2 + 1, k + 1, this.rock);
            W.set(world, i - 1, j2 + 1, k - 1, this.rock);
            W.set(world, i - 1, j2 + 1, k, this.rock);
            W.set(world, i, j2 + 1, k + 1, this.rock);
            W.set(world, i, j2 + 1, k - 1, this.rock);
            W.set(world, i, j2 + 2, k, this.rock);
         }

         if (raner > 6) {
            for (int j2 = 0 + j - 6; j2 < raner + j - 6; j2++) {
               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 3, j2, k, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 3, j2, k - 1, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 3, j2, k + 1, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 3, j2, k, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 3, j2, k - 1, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 3, j2, k + 1, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i, j2, k + 3, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 1, j2, k + 3, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 1, j2, k + 3, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i, j2, k - 3, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 1, j2, k - 3, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 1, j2, k - 3, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 2, j2, k - 2, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i + 2, j2, k + 2, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 2, j2, k - 2, this.rock);
               }

               if (random.nextInt(this.odds5) != 0) {
                  W.set(world, i - 2, j2, k + 2, this.rock);
               }
            }
         }

         if (raner > 11) {
            for (int j2 = 0 + j - 11; j2 < raner + j - 11; j2++) {
               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 4, j2, k, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 4, j2, k - 1, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 4, j2, k + 1, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 4, j2, k, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 4, j2, k - 1, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 4, j2, k + 1, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i, j2, k + 4, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 1, j2, k + 4, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 1, j2, k + 4, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i, j2, k - 4, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 1, j2, k - 4, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 1, j2, k - 4, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 2, j2, k - 3, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 2, j2, k + 3, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 2, j2, k - 3, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 2, j2, k + 3, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 3, j2, k - 2, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i + 3, j2, k + 2, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 3, j2, k - 2, this.rock);
               }

               if (random.nextInt(this.odds6) != 0) {
                  W.set(world, i - 3, j2, k + 2, this.rock);
               }
            }
         }

         int j3 = j + 2 + raner - 3;
         int j4 = j + 4 + raner - 3;
         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k - 2, this.rock);
            W.set(world, i - 1, j3 + 1, k - 2, this.rock);
            W.set(world, i + 1, j3 + 1, k - 2, this.rock);
            W.set(world, i, j3 + 2, k - 2, this.rock);
            W.set(world, i - 1, j3 + 2, k - 2, this.rock);
            W.set(world, i + 1, j3 + 2, k - 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k - 2, this.rock);
            W.set(world, i - 1, j3 + 1, k - 2, this.rock);
            W.set(world, i + 1, j3 + 1, k - 2, this.rock);
            W.set(world, i - 1, j3 + 2, k - 2, this.rock);
            W.set(world, i + 1, j3 + 2, k - 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k - 2, this.rock);
            W.set(world, i - 1, j3 + 1, k - 2, this.rock);
            W.set(world, i + 1, j3 + 1, k - 2, this.rock);
            W.set(world, i, j3 + 2, k - 2, this.rock);
            W.set(world, i + 1, j3 + 2, k - 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k - 2, this.rock);
            W.set(world, i - 1, j3 + 1, k - 2, this.rock);
            W.set(world, i + 1, j3 + 1, k - 2, this.rock);
            W.set(world, i, j3 + 2, k - 2, this.rock);
            W.set(world, i - 1, j3 + 2, k - 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k - 2, this.rock);
            W.set(world, i - 1, j3 + 1, k - 2, this.rock);
            W.set(world, i, j3 + 2, k - 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 1, j3 + 1, k - 2, this.rock);
            W.set(world, i + 1, j3 + 1, k - 2, this.rock);
            W.set(world, i + 1, j3 + 2, k - 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k + 2, this.rock);
            W.set(world, i - 1, j3 + 1, k + 2, this.rock);
            W.set(world, i + 1, j3 + 1, k + 2, this.rock);
            W.set(world, i, j3 + 2, k + 2, this.rock);
            W.set(world, i - 1, j3 + 2, k + 2, this.rock);
            W.set(world, i + 1, j3 + 2, k + 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k + 2, this.rock);
            W.set(world, i - 1, j3 + 1, k + 2, this.rock);
            W.set(world, i + 1, j3 + 1, k + 2, this.rock);
            W.set(world, i - 1, j3 + 2, k + 2, this.rock);
            W.set(world, i + 1, j3 + 2, k + 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k + 2, this.rock);
            W.set(world, i - 1, j3 + 1, k + 2, this.rock);
            W.set(world, i + 1, j3 + 1, k + 2, this.rock);
            W.set(world, i, j3 + 2, k + 2, this.rock);
            W.set(world, i + 1, j3 + 2, k + 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k + 2, this.rock);
            W.set(world, i - 1, j3 + 1, k + 2, this.rock);
            W.set(world, i + 1, j3 + 1, k + 2, this.rock);
            W.set(world, i, j3 + 2, k + 2, this.rock);
            W.set(world, i - 1, j3 + 2, k + 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i, j3 + 1, k + 2, this.rock);
            W.set(world, i - 1, j3 + 1, k + 2, this.rock);
            W.set(world, i, j3 + 2, k + 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 1, j3 + 1, k + 2, this.rock);
            W.set(world, i + 1, j3 + 1, k + 2, this.rock);
            W.set(world, i + 1, j3 + 2, k + 2, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i + 2, j3 + 1, k, this.rock);
            W.set(world, i + 2, j3 + 1, k - 1, this.rock);
            W.set(world, i + 2, j3 + 1, k + 1, this.rock);
            W.set(world, i + 2, j3 + 2, k, this.rock);
            W.set(world, i + 2, j3 + 2, k - 1, this.rock);
            W.set(world, i + 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i + 2, j3 + 1, k, this.rock);
            W.set(world, i + 2, j3 + 1, k - 1, this.rock);
            W.set(world, i + 2, j3 + 1, k + 1, this.rock);
            W.set(world, i + 2, j3 + 2, k - 1, this.rock);
            W.set(world, i + 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i + 2, j3 + 1, k, this.rock);
            W.set(world, i + 2, j3 + 1, k - 1, this.rock);
            W.set(world, i + 2, j3 + 1, k + 1, this.rock);
            W.set(world, i + 2, j3 + 2, k, this.rock);
            W.set(world, i + 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i + 2, j3 + 1, k, this.rock);
            W.set(world, i + 2, j3 + 1, k - 1, this.rock);
            W.set(world, i + 2, j3 + 1, k + 1, this.rock);
            W.set(world, i + 2, j3 + 2, k, this.rock);
            W.set(world, i + 2, j3 + 2, k - 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i + 2, j3 + 1, k, this.rock);
            W.set(world, i + 2, j3 + 1, k - 1, this.rock);
            W.set(world, i + 2, j3 + 2, k, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i + 2, j3 + 1, k, this.rock);
            W.set(world, i + 2, j3 + 1, k + 1, this.rock);
            W.set(world, i + 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 2, j3 + 1, k, this.rock);
            W.set(world, i - 2, j3 + 1, k - 1, this.rock);
            W.set(world, i - 2, j3 + 1, k + 1, this.rock);
            W.set(world, i - 2, j3 + 2, k, this.rock);
            W.set(world, i - 2, j3 + 2, k - 1, this.rock);
            W.set(world, i - 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 2, j3 + 1, k, this.rock);
            W.set(world, i - 2, j3 + 1, k - 1, this.rock);
            W.set(world, i - 2, j3 + 1, k + 1, this.rock);
            W.set(world, i - 2, j3 + 2, k - 1, this.rock);
            W.set(world, i - 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 2, j3 + 1, k, this.rock);
            W.set(world, i - 2, j3 + 1, k - 1, this.rock);
            W.set(world, i - 2, j3 + 1, k + 1, this.rock);
            W.set(world, i - 2, j3 + 2, k, this.rock);
            W.set(world, i - 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 2, j3 + 1, k, this.rock);
            W.set(world, i - 2, j3 + 1, k - 1, this.rock);
            W.set(world, i - 2, j3 + 1, k + 1, this.rock);
            W.set(world, i - 2, j3 + 2, k, this.rock);
            W.set(world, i - 2, j3 + 2, k - 1, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 2, j3 + 1, k, this.rock);
            W.set(world, i - 2, j3 + 1, k - 1, this.rock);
            W.set(world, i - 2, j3 + 2, k, this.rock);
         }

         if (random.nextInt(this.odds) == 0) {
            W.set(world, i - 2, j3 + 1, k, this.rock);
            W.set(world, i - 2, j3 + 1, k + 1, this.rock);
            W.set(world, i - 2, j3 + 2, k + 1, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i + 1, j4, k - 1, this.rock);
            W.set(world, i + 1, j4, k + 1, this.rock);
            W.set(world, i + 1, j4, k, this.rock);
            W.set(world, i + 1, j4 + 1, k - 1, this.rock);
            W.set(world, i + 1, j4 + 1, k + 1, this.rock);
            W.set(world, i + 1, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i + 1, j4, k - 1, this.rock);
            W.set(world, i + 1, j4, k + 1, this.rock);
            W.set(world, i + 1, j4, k, this.rock);
            W.set(world, i + 1, j4 + 1, k - 1, this.rock);
            W.set(world, i + 1, j4 + 1, k + 1, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i + 1, j4, k - 1, this.rock);
            W.set(world, i + 1, j4, k + 1, this.rock);
            W.set(world, i + 1, j4, k, this.rock);
            W.set(world, i + 1, j4 + 1, k - 1, this.rock);
            W.set(world, i + 1, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i + 1, j4, k - 1, this.rock);
            W.set(world, i + 1, j4, k + 1, this.rock);
            W.set(world, i + 1, j4, k, this.rock);
            W.set(world, i + 1, j4 + 1, k + 1, this.rock);
            W.set(world, i + 1, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i + 1, j4, k - 1, this.rock);
            W.set(world, i + 1, j4, k + 1, this.rock);
            W.set(world, i + 1, j4, k, this.rock);
            W.set(world, i + 1, j4 + 1, k - 1, this.rock);
            W.set(world, i + 1, j4 + 1, k + 1, this.rock);
            W.set(world, i + 1, j4 + 1, k, this.rock);
            W.set(world, i + 1, j4 + 2, k - 1, this.rock);
            W.set(world, i + 1, j4 + 2, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i - 1, j4, k + 1, this.rock);
            W.set(world, i - 1, j4, k - 1, this.rock);
            W.set(world, i - 1, j4, k, this.rock);
            W.set(world, i - 1, j4 + 1, k + 1, this.rock);
            W.set(world, i - 1, j4 + 1, k - 1, this.rock);
            W.set(world, i - 1, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i - 1, j4, k + 1, this.rock);
            W.set(world, i - 1, j4, k - 1, this.rock);
            W.set(world, i - 1, j4, k, this.rock);
            W.set(world, i - 1, j4 + 1, k - 1, this.rock);
            W.set(world, i - 1, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i - 1, j4, k + 1, this.rock);
            W.set(world, i - 1, j4, k - 1, this.rock);
            W.set(world, i - 1, j4, k, this.rock);
            W.set(world, i - 1, j4 + 1, k + 1, this.rock);
            W.set(world, i - 1, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i - 1, j4, k + 1, this.rock);
            W.set(world, i - 1, j4, k - 1, this.rock);
            W.set(world, i - 1, j4, k, this.rock);
            W.set(world, i - 1, j4 + 1, k + 1, this.rock);
            W.set(world, i - 1, j4 + 1, k - 1, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i - 1, j4, k + 1, this.rock);
            W.set(world, i - 1, j4, k - 1, this.rock);
            W.set(world, i - 1, j4, k, this.rock);
            W.set(world, i - 1, j4 + 1, k + 1, this.rock);
            W.set(world, i - 1, j4 + 1, k - 1, this.rock);
            W.set(world, i - 1, j4 + 1, k, this.rock);
            W.set(world, i - 1, j4 + 2, k + 1, this.rock);
            W.set(world, i - 1, j4 + 2, k, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i, j4, k + 1, this.rock);
            W.set(world, i, j4 + 1, k + 1, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i, j4, k - 1, this.rock);
            W.set(world, i, j4 + 1, k - 1, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i, j4, k + 1, this.rock);
            W.set(world, i, j4 + 1, k + 1, this.rock);
            W.set(world, i, j4 + 2, k + 1, this.rock);
            W.set(world, i, j4 + 3, k + 1, this.rock);
         }

         if (random.nextInt(this.odds2) == 0) {
            W.set(world, i, j4, k - 1, this.rock);
            W.set(world, i, j4 + 1, k - 1, this.rock);
            W.set(world, i, j4 + 2, k - 1, this.rock);
            W.set(world, i, j4 + 3, k - 1, this.rock);
         }

         if (random.nextInt(this.odds3) == 0) {
            W.set(world, i, j4, k, this.rock);
         }

         if (random.nextInt(this.odds3) == 0) {
            W.set(world, i, j4, k, this.rock);
            W.set(world, i, j4 + 1, k, this.rock);
         }

         if (random.nextInt(this.odds3) == 0) {
            W.set(world, i, j4, k, this.rock);
            W.set(world, i, j4 + 1, k, this.rock);
            W.set(world, i, j4 + 2, k, this.rock);
         }

         if (random.nextInt(this.odds3) == 0) {
            W.set(world, i, j4, k, this.rock);
            W.set(world, i, j4 + 1, k, this.rock);
            W.set(world, i, j4 + 2, k, this.rock);
            W.set(world, i, j4 + 3, k, this.rock);
         }

         if (raner > 15) {
            int and = random.nextInt(20);
            int j5 = j + 5;
            if (and < 10) {
               Block rock2 = Blocks.AIR;
               if (and == 0) {
                  rock2 = Blocks.GLOWSTONE;
                  this.odds7 = 5;
               }

               if (and == 1) {
                  rock2 = Blocks.COAL_ORE;
                  this.odds7 = 6;
               }

               if (and == 2) {
                  rock2 = Blocks.IRON_ORE;
                  this.odds7 = 4;
               }

               if (and == 3 && random.nextInt(2) == 0) {
                  rock2 = Blocks.GOLD_ORE;
                  this.odds7 = 2;
               }

               if (and == 4 && random.nextInt(5) == 0) {
                  rock2 = Blocks.DIAMOND_ORE;
                  this.odds7 = 1;
               }

               if (and == 5 && random.nextInt(1) == 0) {
                  rock2 = Blocks.REDSTONE_ORE;
                  this.odds7 = 3;
               }

               if (and == 7) {
                  rock2 = this.rock;
                  this.odds7 = 14;
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i + 1, j5, k + 1, rock2);
               } else {
                  W.air(world, i + 1, j5, k + 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i + 1, j5, k - 1, rock2);
               } else {
                  W.air(world, i + 1, j5, k - 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i + 1, j5, k, rock2);
               } else {
                  W.air(world, i + 1, j5, k);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i - 1, j5, k + 1, rock2);
               } else {
                  W.air(world, i - 1, j5, k + 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i - 1, j5, k - 1, rock2);
               } else {
                  W.air(world, i - 1, j5, k - 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i - 1, j5, k, rock2);
               } else {
                  W.air(world, i - 1, j5, k);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i, j5, k + 1, rock2);
               } else {
                  W.air(world, i, j5, k + 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i, j5, k - 1, rock2);
               } else {
                  W.air(world, i, j5, k - 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i, j5, k, rock2);
               } else {
                  W.air(world, i, j5, k);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i + 1, j5 - 4, k + 1, rock2);
               } else {
                  W.air(world, i + 1, j5 - 4, k + 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i + 1, j5 - 4, k - 1, rock2);
               } else {
                  W.air(world, i + 1, j5 - 4, k - 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i + 1, j5 - 4, k, rock2);
               } else {
                  W.air(world, i + 1, j5 - 4, k);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i - 1, j5 - 4, k + 1, rock2);
               } else {
                  W.air(world, i - 1, j5 - 4, k + 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i - 1, j5 - 4, k - 1, rock2);
               } else {
                  W.air(world, i - 1, j5 - 4, k - 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i - 1, j5 - 4, k, rock2);
               } else {
                  W.air(world, i - 1, j5 - 4, k);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i, j5 - 4, k + 1, rock2);
               } else {
                  W.air(world, i, j5 - 4, k + 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i, j5 - 4, k - 1, rock2);
               } else {
                  W.air(world, i, j5 - 4, k - 1);
               }

               if (random.nextInt(this.odds7) != 0) {
                  W.set(world, i, j5 - 4, k, rock2);
               } else {
                  W.air(world, i, j5 - 4, k);
               }

               int randerer = random.nextInt(5);
               if (randerer == 0) {
                  W.air(world, i - 2, j5 - 3, k);
                  W.air(world, i - 3, j5 - 3, k);
                  W.air(world, i - 2, j5 - 2, k);
                  W.air(world, i - 3, j5 - 2, k);
                  W.air(world, i - 4, j5 - 3, k);
                  W.air(world, i - 4, j5 - 2, k);
               }

               if (randerer == 1) {
                  W.air(world, i + 2, j5 - 3, k);
                  W.air(world, i + 3, j5 - 3, k);
                  W.air(world, i + 2, j5 - 3, k);
                  W.air(world, i + 3, j5 - 2, k);
                  W.air(world, i + 4, j5 - 3, k);
                  W.air(world, i + 4, j5 - 3, k);
               }

               if (randerer == 2) {
                  W.air(world, i, j5 - 3, k - 2);
                  W.air(world, i, j5 - 3, k - 3);
                  W.air(world, i, j5 - 2, k - 2);
                  W.air(world, i, j5 - 3, k - 2);
                  W.air(world, i, j5 - 3, k - 4);
                  W.air(world, i, j5 - 2, k - 4);
               }

               if (randerer == 3) {
                  W.air(world, i, j5 - 3, k + 2);
                  W.air(world, i, j5 - 3, k + 3);
                  W.air(world, i, j5 - 2, k + 2);
                  W.air(world, i, j5 - 2, k + 3);
                  W.air(world, i, j5 - 3, k + 4);
                  W.air(world, i, j5 - 2, k + 4);
               }

               for (int j6 = j5 - 1; j6 > j5 - 4; j6--) {
                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i + 2, j6, k, rock2);
                  } else {
                     W.air(world, i + 2, j6, k);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i + 2, j6, k - 1, rock2);
                  } else {
                     W.air(world, i + 2, j6, k - 1);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i + 2, j6, k + 1, rock2);
                  } else {
                     W.air(world, i + 2, j6, k + 1);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i - 2, j6, k, rock2);
                  } else {
                     W.air(world, i - 2, j6, k);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i - 2, j6, k - 1, rock2);
                  } else {
                     W.air(world, i - 2, j6, k - 1);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i - 2, j6, k + 1, rock2);
                  } else {
                     W.air(world, i - 2, j6, k + 1);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i, j6, k + 2, rock2);
                  } else {
                     W.air(world, i, j6, k + 2);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i - 1, j6, k + 2, rock2);
                  } else {
                     W.air(world, i - 1, j6, k + 2);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i + 1, j6, k + 2, rock2);
                  } else {
                     W.air(world, i + 1, j6, k + 2);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i, j6, k - 2, rock2);
                  } else {
                     W.air(world, i, j6, k - 2);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i - 1, j6, k - 2, rock2);
                  } else {
                     W.air(world, i - 1, j6, k - 2);
                  }

                  if (random.nextInt(this.odds7) != 0) {
                     W.set(world, i + 1, j6, k - 2, rock2);
                  } else {
                     W.air(world, i + 1, j6, k - 2);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i + 1, j6, k + 1);
                  } else {
                     W.air(world, i + 1, j6, k + 1);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i + 1, j6, k - 1);
                  } else {
                     W.air(world, i + 1, j6, k - 1);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i + 1, j6, k);
                  } else {
                     W.air(world, i + 1, j6, k);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i - 1, j6, k + 1);
                  } else {
                     W.air(world, i - 1, j6, k + 1);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i - 1, j6, k - 1);
                  } else {
                     W.air(world, i - 1, j6, k - 1);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i - 1, j6, k);
                  } else {
                     W.air(world, i - 1, j6, k);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i, j6, k + 1);
                  } else {
                     W.air(world, i, j6, k + 1);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i, j6, k - 1);
                  } else {
                     W.air(world, i, j6, k - 1);
                  }

                  if (random.nextInt(this.odds8) != 0) {
                     W.air(world, i, j6, k);
                  } else {
                     W.air(world, i, j6, k);
                  }
               }

               if (and == 6 && random.nextInt(2) == 0) {
                  W.set(world, i, j5, k, Blocks.CHEST);
                  TileEntityChest tileentitychest = (TileEntityChest)W.te(world, i, j5, k);
                  if (tileentitychest == null) return true;

                  for (int l3 = 0; l3 < 20; l3++) {
                     ItemStack itemstack = this.pickCheckLootItem(random);
                     if (itemstack != null) {
                        tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
                     }
                  }
               }
            }
         }

         return true;
      } else {
         return false;
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
