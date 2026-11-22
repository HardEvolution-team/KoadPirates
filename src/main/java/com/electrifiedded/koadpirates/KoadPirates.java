package com.electrifiedded.koadpirates;

import com.electrifiedded.archaiclib.original.OriginalModAssets;
import com.electrifiedded.koadpirates.gui.GuiHandler;
import com.electrifiedded.koadpirates.proxy.CommonProxy;
import com.electrifiedded.koadpirates.world.WorldGenHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Reimplementation of Koadmaster's Pirates (KoditheMaster, 1.5.2 for 1.7.x) for 1.12.2.
 * No original asset is shipped: textures come from the player's copy of the original jar in archaic_originals/.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:archaiclib@[1.32.0,)")
public class KoadPirates {
    public static final String MODID = Reference.MOD_ID;
    public static final String ORIGINAL_MODID = "KoadPirates";
    public static final String ORIGINAL_NAME = "pirates_mod_1.7.10.jar - Koadmaster's Pirates 1.5.2 (by KoditheMaster)";
    public static final String ORIGINAL_URL = "http://www.minecraftforum.net/topic/1857984-164forge-koadmasters-pirates/";

    @Mod.Instance(Reference.MOD_ID)
    public static KoadPirates instance;

    @SidedProxy(clientSide = "com.electrifiedded.koadpirates.proxy.ClientProxy", serverSide = "com.electrifiedded.koadpirates.proxy.CommonProxy")
    public static CommonProxy proxy;

    public KoadPirates() {
        // the original 1.7 lang uses other keys - the port ships its own lang file
        OriginalModAssets.hide(MODID, "lang/en_us.lang");
        // file names with spaces / odd names in the original jar
        OriginalModAssets.alias(MODID, "textures/entity/ghost_pirate.png", "textures/entity/tras pirate2.png");
        OriginalModAssets.alias(MODID, "textures/entity/zombie_ghost_pirate.png", "textures/entity/Zombie Ghost Pirate.png");
        OriginalModAssets.alias(MODID, "textures/items/davys_medallion.png", "textures/items/Davy'sMedallion.png");
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OriginalModAssets.require(MODID, ORIGINAL_MODID, ORIGINAL_NAME, ORIGINAL_URL, MODID);
        PiratesContent.registerAll(this);
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PiratesContent.registerRecipes();
        PiratesContent.registerSpawns();
        GameRegistry.registerWorldGenerator(new WorldGenHandler(), 100);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        proxy.init();
    }
}
