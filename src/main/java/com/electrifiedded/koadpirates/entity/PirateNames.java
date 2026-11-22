package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.KoadPirates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/** Entity names; maps the 1.7 global ids ("Pirate1".."Pirate3", "GhostPirate", "Test") used by spawners. */
public final class PirateNames {
    private PirateNames() {}

    public static final String CUTLASS = "pirate1", GUN = "pirate2", GRENADE = "pirate3", GHOST = "ghost_pirate", RAT = "rat";

    public static String fromLegacy(String legacy) {
        if (legacy == null || legacy.isEmpty()) return CUTLASS;
        switch (legacy) {
            case "Pirate1": return CUTLASS;
            case "Pirate2": return GUN;
            case "Pirate3": return GRENADE;
            case "GhostPirate": return GHOST;
            case "Test": return RAT;
            default: return legacy;
        }
    }

    public static Entity create(String name, World world) {
        String n = fromLegacy(name);
        ResourceLocation rl = n.indexOf(':') >= 0 ? new ResourceLocation(n) : new ResourceLocation(KoadPirates.MODID, n);
        return EntityList.createEntityByIDFromName(rl, world);
    }
}
