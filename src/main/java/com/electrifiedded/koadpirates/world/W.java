package com.electrifiedded.koadpirates.world;

import com.electrifiedded.archaiclib.legacy.world.LegacyBlockIds;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** 1.7 world access used by the converted structure generators (flag 2: no neighbour updates, like world gen). */
final class W {
    private W() {}

    static Block get(World w, int x, int y, int z) {
        return w.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    static boolean set(World w, int x, int y, int z, Block b) {
        return LegacyBlockIds.setBlock(w, x, y, z, b, 0, 2);
    }

    static boolean set(World w, int x, int y, int z, Block b, int meta, int flags) {
        return LegacyBlockIds.setBlock(w, x, y, z, b, meta, flags & ~1);
    }

    static boolean air(World w, int x, int y, int z) {
        return w.setBlockToAir(new BlockPos(x, y, z));
    }

    static TileEntity te(World w, int x, int y, int z) {
        return w.getTileEntity(new BlockPos(x, y, z));
    }
}
