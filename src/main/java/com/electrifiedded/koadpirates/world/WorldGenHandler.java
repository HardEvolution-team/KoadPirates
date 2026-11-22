package com.electrifiedded.koadpirates.world;

import com.electrifiedded.koadpirates.PiratesContent;
import com.electrifiedded.koadpirates.block.BlockSeaPlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Overworld generation of the original: sea rocks, pirate ships (floating + sunken), buried treasure and salt veins in
 * sand, sea plant fields and salt mines. Positions are offset by +8 into the populated area (the 1.7 code used up to
 * 50 blocks around the chunk, which cascades chunk generation on 1.12).
 */
public class WorldGenHandler implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator gen, IChunkProvider provider) {
        if (!world.provider.isSurfaceWorld()) return;
        int x = chunkX * 16 + 8, z = chunkZ * 16 + 8;
        for (int k = 0; k < 5; k++) new WorldGenSeaRock().generate(world, random, x + random.nextInt(16), random.nextInt(65), z + random.nextInt(16));
        for (int k = 0; k < 2; k++) {
            int px = x + random.nextInt(16), py = random.nextInt(65), pz = z + random.nextInt(16);
            new WorldGenBoat().generate(world, random, px, py, pz);
            new WorldGenSunkBoat().generate(world, random, px, py, pz);
        }
        for (int k = 0; k < 7; k++) minable(world, random, PiratesContent.buriedTreasure, 7, x + random.nextInt(16), random.nextInt(128), z + random.nextInt(16));
        for (int k = 0; k < 7; k++) minable(world, random, PiratesContent.saltBlock, 7, x + random.nextInt(16), random.nextInt(128), z + random.nextInt(16));
        for (int k = 0; k < 30; k++) seaPlants(world, random, x + random.nextInt(16), random.nextInt(65), z + random.nextInt(16));
        new WorldGenSaltMine().generate(world, random, x + random.nextInt(16), random.nextInt(65), z + random.nextInt(16));
    }

    /** 1.7 WorldGenMinableS: vein that only replaces sand */
    private static void minable(World w, Random r, net.minecraft.block.Block block, int n, int x, int y, int z) {
        float f = r.nextFloat() * (float)Math.PI;
        double d = x + 8 + MathHelper.sin(f) * n / 8.0F - 8, d1 = x + 8 - MathHelper.sin(f) * n / 8.0F - 8;
        double d2 = z + 8 + MathHelper.cos(f) * n / 8.0F - 8, d3 = z + 8 - MathHelper.cos(f) * n / 8.0F - 8;
        double d4 = y + r.nextInt(3) - 2, d5 = y + r.nextInt(3) - 2;
        for (int i = 0; i <= n; i++) {
            double cx = d + (d1 - d) * i / n, cy = d4 + (d5 - d4) * i / n, cz = d2 + (d3 - d2) * i / n;
            double d9 = r.nextDouble() * n / 16.0;
            double rad = (MathHelper.sin(i * (float)Math.PI / n) + 1.0F) * d9 + 1.0;
            for (int bx = MathHelper.floor(cx - rad / 2); bx <= MathHelper.floor(cx + rad / 2); bx++)
                for (int by = MathHelper.floor(cy - rad / 2); by <= MathHelper.floor(cy + rad / 2); by++)
                    for (int bz = MathHelper.floor(cz - rad / 2); bz <= MathHelper.floor(cz + rad / 2); bz++) {
                        double a = (bx + 0.5 - cx) / (rad / 2), b = (by + 0.5 - cy) / (rad / 2), c = (bz + 0.5 - cz) / (rad / 2);
                        if (a * a + b * b + c * c >= 1.0) continue;
                        BlockPos p = new BlockPos(bx, by, bz);
                        if (w.isBlockLoaded(p) && w.getBlockState(p).getBlock() == Blocks.SAND) w.setBlockState(p, block.getDefaultState(), 2);
                    }
        }
    }

    /** 1.7 WorldGenSeaPlants: a 10x2x10 patch of sea weed with some coral and rare anemones */
    private static void seaPlants(World w, Random r, int i, int j, int k) {
        BlockSeaPlant weed = (BlockSeaPlant)PiratesContent.seaWeed;
        BlockSeaPlant kelp = (BlockSeaPlant)PiratesContent.kelp;
        BlockSeaPlant anem = (BlockSeaPlant)PiratesContent.anem;
        if (!weed.canBlockStay(w, new BlockPos(i, j, k))) return;
        for (int yr = 0; yr < 2; yr++)
            for (int zr = 0; zr < 10; zr++)
                for (int xr = 0; xr < 10; xr++) {
                    BlockPos p = new BlockPos(i + zr - 5, j + yr, k + xr - 5);
                    if (r.nextInt(3) == 0 || !weed.canBlockStay(w, p)) continue;
                    if (r.nextInt(20) == 0) {
                        if (r.nextInt(20) == 0) {
                            if (anem.canBlockStay(w, p)) w.setBlockState(p, anem.getDefaultState(), 2);
                        } else if (kelp.canBlockStay(w, p)) {
                            w.setBlockState(p, kelp.getStateFromMeta(r.nextInt(8) + 8), 2);
                        }
                    } else {
                        w.setBlockState(p, weed.getDefaultState(), 2);
                    }
                }
    }
}
