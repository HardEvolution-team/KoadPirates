package com.electrifiedded.koadpirates;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.registry.LegacyRecipes;
import com.electrifiedded.archaiclib.legacy.world.LegacySpawns;
import com.electrifiedded.archaiclib.v1710.api.item.ArchaicItem;
import com.electrifiedded.koadpirates.block.*;
import com.electrifiedded.koadpirates.entity.*;
import com.electrifiedded.koadpirates.item.*;
import com.electrifiedded.koadpirates.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/** All blocks/items/entities of the mod (field names follow the original koadmastersPirates class). */
public final class PiratesContent {
    private PiratesContent() {}

    public static Block Hull, GPT, MobSpawnerPirate, buriedTreasure, Keg, cucumberParent, cucumber, saltBlock, rock,
            seaWeed, kelp, anem, Smelter, coolingRack, fishTrap, RopeMaker, Net, RopeLadder, cursedChest;

    public static Item Grenado, IronPlate, Flintlock, EmptyFlintlock, Shot, IGPT, DavyCode, Cutlass, Musket, EmptyMusket,
            slagIron, slagIronPlate, slagChainMail, chainMail, mold, slagBlade, blade, slagBarrel, barrel, MusketWithb,
            EmptyMusketWithb, bordingHook, saltItem, Hook, slagHook, cucumberFruit, pickel, tether, kelpItem, CannonB1,
            CannonB2, CannonB3, NetItem, NetGrabber, RopeItem, ghostWrap, CannonItem, ghostH, ghostC, ghostL, ghostB;
    public static ItemSeeds cucumberSeeds;
    public static ItemFood saltedPork, saltedBeef, saltedFish, saltedSalmon;

    public static Item.ToolMaterial PIRATE_IRON;
    public static ArmorMaterial GHOST_ARMOR;

    static void registerAll(KoadPirates mod) {
        // ---- blocks
        Hull = block(new BlockHull(), "hull", "hull", 1.0F, 2.0F, null);
        MobSpawnerPirate = block(new BlockPirateSpawner(), "mob_spawner_pirate", "mobSpawnerPirate", 2.0F, 5.0F, null);
        GPT = block(new BlockGPT(), "gun_powder_trail", "gpt", 0.0F, 0.0F, null);
        buriedTreasure = block(new BlockSimple(Material.SAND, SoundType.SAND, "sand", () -> Items.GOLD_NUGGET, 1, 1), "buried_treasure", "buriedTreasure", 0.5F, 2.5F, null);
        Keg = block(new BlockKeg(), "keg", "keg", 2.0F, 0.0F, CreativeTabs.DECORATIONS);
        fishTrap = block(new BlockMachine(BlockMachine.FISH_TRAP, Material.WOOD), "fish_trap", "fishTrap", 0.5F, 2.5F, CreativeTabs.DECORATIONS);
        Smelter = block(new BlockMachine(BlockMachine.SMELTER, Material.ROCK), "smelter", "smelter", 0.5F, 2.5F, CreativeTabs.DECORATIONS);
        RopeMaker = block(new BlockMachine(BlockMachine.ROPE_MAKER, Material.WOOD), "rope_maker", "ropeMaker", 0.5F, 2.5F, CreativeTabs.DECORATIONS);
        coolingRack = block(new BlockMachine(BlockMachine.COOLING_RACK, Material.ROCK), "cooling_rack", "coolingRack", 0.5F, 2.5F, CreativeTabs.DECORATIONS);
        saltBlock = block(new BlockSimple(Material.ROCK, SoundType.STONE, "koadpirates:SaltBlock", () -> saltItem, 2, 4), "salt_block", "salt", 1.0F, 5.0F, CreativeTabs.BUILDING_BLOCKS);
        seaWeed = block(new BlockSeaPlant(BlockSeaPlant.SEAWEED), "sea_weed", "seaWeed", 0.0F, 0.0F, CreativeTabs.DECORATIONS);
        kelp = block(new BlockSeaPlant(BlockSeaPlant.KELP), "kelp", "kelp", 0.0F, 0.0F, null);
        anem = block(new BlockSeaPlant(BlockSeaPlant.ANEMONE), "anem", "anem", 0.0F, 0.0F, CreativeTabs.DECORATIONS);
        Net = block(new BlockNetTrap(), "net", "net", 0.2F, 2.0F, null);
        RopeLadder = block(new BlockRopeLadder(), "rope_ladder", "ropeLadder", 1.0F, 2.0F, CreativeTabs.DECORATIONS);
        cursedChest = block(new BlockCursedChest(), "cursed_chest", "cursedChest", 1.0F, 2.0F, CreativeTabs.DECORATIONS);
        cucumberParent = block(new BlockCucumberCrop(true), "cucumber_crop_parent", "cucumberParent", 0.0F, 0.0F, null);
        cucumber = block(new BlockCucumberCrop(false), "cucumber_crop", "cucumber", 0.0F, 0.0F, null);
        rock = block(new BlockSimple(Material.ROCK, SoundType.STONE, "stone", () -> Item.getItemFromBlock(Blocks.STONE), 1, 1), "rock", "seaRock", 1.5F, 10.0F, null);
        anem.setLightLevel(0.4F);
        kelp.setLightLevel(0.4F);

        // ---- materials
        PIRATE_IRON = EnumHelper.addToolMaterial("KOADPIRATES_IRONPLATE", 2, 800, 7.0F, 3.0F, 10);
        GHOST_ARMOR = EnumHelper.addArmorMaterial("KOADPIRATES_GHOST", "koadpirates:ghost", 1000, new int[]{1, 1, 1, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);

        // ---- items (texture names are those of the original, including its swapped loaded/empty gun icons)
        Grenado = item(new ItemGrenado(), "grenade", "Grenade", CreativeTabs.COMBAT, "Grenade");
        IronPlate = item(new Item(), "iron_plate", "IronPlate", CreativeTabs.MATERIALS, "IronPlate");
        Shot = item(new Item(), "shot", "shot", CreativeTabs.COMBAT, "shot");
        Flintlock = item(new ItemGun(true, 0), "flintlock", "shortflintlock", CreativeTabs.COMBAT, "SFlockE");
        EmptyFlintlock = item(new ItemGun(false, 0), "empty_flintlock", "SFlockE", CreativeTabs.COMBAT, "shortflintlock");
        Musket = item(new ItemGun(true, 1), "musket", "Musket", CreativeTabs.COMBAT, "EmptyMusket");
        EmptyMusket = item(new ItemGun(false, 1), "empty_musket", "EmptyMusket", CreativeTabs.COMBAT, "Musket");
        MusketWithb = item(new ItemGun(true, 2), "musket_bayonet", "MusketWB", CreativeTabs.COMBAT, "EmptyMusketWB");
        EmptyMusketWithb = item(new ItemGun(false, 2), "empty_musket_bayonet", "EmptyMusketWB", CreativeTabs.COMBAT, "MusketWB");
        IGPT = item(new ItemPowderHorn(), "powder_horn", "PowderHorn", CreativeTabs.MATERIALS, "PowderHorn");
        DavyCode = item(new Item(), "davys_medallion", "DavysMedallion", CreativeTabs.MISC, "davys_medallion");
        Cutlass = item(new ItemCutlass(PIRATE_IRON), "cutlass", "Cutlass", CreativeTabs.COMBAT, "Cutlass");
        slagIron = item(new Item(), "slag_iron", "SlagIron", CreativeTabs.MATERIALS, "SlagIron");
        slagIronPlate = item(new Item(), "slag_iron_plate", "SlagIronPlate", CreativeTabs.MATERIALS, "SlagIronPlate");
        slagChainMail = item(new Item(), "slag_chain_mail", "SlagChainMail", CreativeTabs.MATERIALS, "SlagChainMail");
        chainMail = item(new Item(), "chain_mail", "ChainMail", CreativeTabs.MATERIALS, "ChainMail");
        mold = item(new Item(), "mold", "Mold", CreativeTabs.MATERIALS, "Mold");
        slagBlade = item(new Item(), "slag_blade", "SlagBlade", CreativeTabs.MATERIALS, "SlagBlade");
        blade = item(new Item(), "blade", "Blade", CreativeTabs.MATERIALS, "Blade");
        slagBarrel = item(new Item(), "slag_barrel", "SlagBarrel", CreativeTabs.MATERIALS, "SlagBarrel");
        barrel = item(new Item(), "barrel", "Barrel", CreativeTabs.MATERIALS, "Barrel");
        bordingHook = item(new ItemBoardingHook(), "boarding_hook", "BoardingHook", CreativeTabs.TOOLS, "BoardingHook");
        cucumberFruit = item(new ItemPickle(2, 0.2F, false), "cucumber", "cucumber", CreativeTabs.FOOD, "cucumber");
        pickel = item(new ItemPickle(1, 0.4F, true), "pickle", "pickle", CreativeTabs.FOOD, "pickle");
        cucumberSeeds = (ItemSeeds)item(new ItemSeeds(cucumberParent, Blocks.FARMLAND), "cucumber_seeds", "cucumberSeeds", CreativeTabs.MATERIALS, "cucumberSeeds");
        saltedPork = (ItemFood)item(new ItemFood(10, 1.0F, false), "salted_pork", "saltedPork", CreativeTabs.FOOD, "saltedPork");
        saltedBeef = (ItemFood)item(new ItemFood(10, 1.0F, false), "salted_beef", "saltedBeef", CreativeTabs.FOOD, "saltedBeef");
        saltedFish = (ItemFood)item(new ItemFood(7, 0.7F, false), "salted_fish", "saltedFish", CreativeTabs.FOOD, "saltedFish");
        saltedSalmon = (ItemFood)item(new ItemFood(8, 1.0F, false), "salted_salmon", "saltedSalmon", CreativeTabs.FOOD, "saltedSalmon");
        saltItem = item(new Item(), "salt", "saltItem", CreativeTabs.MATERIALS, "saltItem");
        slagHook = item(new Item(), "slag_hook", "slagHook", CreativeTabs.MATERIALS, "slagHook");
        Hook = item(new Item(), "hook", "Hook", CreativeTabs.MATERIALS, "Hook");
        tether = item(new ItemTether(), "tether", "tether", CreativeTabs.TOOLS, "tether");
        kelpItem = item(new ItemKelp(), "coral", "kelpItem", CreativeTabs.MATERIALS, null);
        NetItem = item(new ItemNet(), "net_item", "netitem", CreativeTabs.COMBAT, "netitem");
        NetGrabber = item(new ItemNetGrabber(), "net_grabber", "netGrabber", CreativeTabs.TOOLS, "NetGrabber");
        RopeItem = item(new Item(), "rope", "rope", CreativeTabs.MATERIALS, "rope");
        ghostWrap = item(new Item(), "ghost_wrap", "ghostWrap", CreativeTabs.MATERIALS, "Ghostwrap");
        CannonB1 = item(new Item(), "cannon_ball_1", "CannonB1", CreativeTabs.MISC, "CannonB1");
        CannonB2 = item(new Item(), "cannon_ball_2", "CannonB2", CreativeTabs.MISC, "CannonB2");
        CannonB3 = item(new Item(), "cannon_ball_3", "CannonB3", CreativeTabs.MISC, "CannonB3");
        CannonItem = item(new ItemCannon(), "cannon", "cannonItem", CreativeTabs.MISC, "CannonItem");
        ghostH = item(new ItemGhostArmor(GHOST_ARMOR, EntityEquipmentSlot.HEAD), "ghost_helmet", "ghostWH", CreativeTabs.COMBAT, "ghost_helm");
        ghostC = item(new ItemGhostArmor(GHOST_ARMOR, EntityEquipmentSlot.CHEST), "ghost_chestplate", "ghostWC", CreativeTabs.COMBAT, "ghost_chest");
        ghostL = item(new ItemGhostArmor(GHOST_ARMOR, EntityEquipmentSlot.LEGS), "ghost_leggings", "ghostWL", CreativeTabs.COMBAT, "ghost_leg");
        ghostB = item(new ItemGhostArmor(GHOST_ARMOR, EntityEquipmentSlot.FEET), "ghost_boots", "ghostWB", CreativeTabs.COMBAT, "ghost_boots");
        for (int m = 0; m < ItemKelp.TEXTURES.length; m++) ArchaicItem.registerMetaTexture(kelpItem, m, "koadpirates:" + ItemKelp.TEXTURES[m]);
        PIRATE_IRON.setRepairItem(new ItemStack(blade));

        // ---- tile entities
        GameRegistry.registerTileEntity(TilePirateSpawner.class, new ResourceLocation(KoadPirates.MODID, "mob_spawner_pirate"));
        GameRegistry.registerTileEntity(TileFishTrap.class, new ResourceLocation(KoadPirates.MODID, "fish_trap"));
        GameRegistry.registerTileEntity(TileSmelter.class, new ResourceLocation(KoadPirates.MODID, "smelter"));
        GameRegistry.registerTileEntity(TileCoolingRack.class, new ResourceLocation(KoadPirates.MODID, "cooling_rack"));
        GameRegistry.registerTileEntity(TileRopeMaker.class, new ResourceLocation(KoadPirates.MODID, "rope_maker"));
        GameRegistry.registerTileEntity(TileCursedChest.class, new ResourceLocation(KoadPirates.MODID, "cursed_chest"));

        // ---- entities
        LegacyGameRegistry.registerModEntity(EntityPirateCutlass.class, PirateNames.CUTLASS, 0, mod, 80, 3, true, 0xFFFFFF, 0);
        LegacyGameRegistry.registerModEntity(EntityPirateGun.class, PirateNames.GUN, 1, mod, 80, 3, true, 0xFFFFFF, 0);
        LegacyGameRegistry.registerModEntity(EntityPirateGrenade.class, PirateNames.GRENADE, 2, mod, 80, 3, true, 0xFFFFFF, 0);
        LegacyGameRegistry.registerModEntity(EntityGhostPirate.class, PirateNames.GHOST, 3, mod, 80, 3, true, 0xFFFFFF, 0);
        LegacyGameRegistry.registerModEntity(EntityRat.class, PirateNames.RAT, 4, mod, 80, 3, true, 0xFFFFFF, 0);
        LegacyGameRegistry.registerModEntity(EntityShot.class, "shot", 5, mod, 64, 1, true);
        LegacyGameRegistry.registerModEntity(EntityGrenado.class, "grenado", 6, mod, 64, 10, true);
        LegacyGameRegistry.registerModEntity(EntityTether.class, "tether", 7, mod, 64, 20, false);
        LegacyGameRegistry.registerModEntity(EntityCannonBall.Light.class, "cannon_ball_1", 8, mod, 64, 10, true);
        LegacyGameRegistry.registerModEntity(EntityCannonBall.Medium.class, "cannon_ball_2", 9, mod, 64, 10, true);
        LegacyGameRegistry.registerModEntity(EntityCannonBall.Heavy.class, "cannon_ball_3", 10, mod, 64, 10, true);
        LegacyGameRegistry.registerModEntity(EntityNet.class, "net", 11, mod, 64, 10, true);
        LegacyGameRegistry.registerModEntity(EntityNetOut.class, "net_out", 12, mod, 64, 20, false);
        LegacyGameRegistry.registerModEntity(EntityCannon.class, "cannon", 13, mod, 64, 1, true);
        LegacyGameRegistry.registerModEntity(EntityGhostShot.class, "ghost_shot", 14, mod, 64, 10, true);
        LegacyGameRegistry.registerModEntity(EntityKegPrimed.class, "keg_primed", 15, mod, 64, 10, true);
    }

    private static Block block(Block b, String name, String key, float hardness, float resistance, CreativeTabs tab) {
        b.setTranslationKey(KoadPirates.MODID + "." + key).setHardness(hardness);
        if (resistance > 0) b.setResistance(resistance);
        if (tab != null) b.setCreativeTab(tab);
        return LegacyGameRegistry.registerBlock(b, name);
    }

    private static Item item(Item i, String name, String key, CreativeTabs tab, String texture) {
        i.setTranslationKey(KoadPirates.MODID + "." + key).setCreativeTab(tab);
        LegacyGameRegistry.registerItem(i, name);
        if (texture != null) ArchaicItem.registerMetaTexture(i, 0, "koadpirates:" + texture);
        return i;
    }

    static void registerRecipes() {
        LegacyRecipes.addShapelessRecipe(new ItemStack(slagIronPlate, 8), slagBarrel);
        LegacyRecipes.addShapelessRecipe(new ItemStack(slagIronPlate, 5), slagBlade);
        LegacyRecipes.addShapelessRecipe(new ItemStack(slagIronPlate, 2), slagChainMail);
        LegacyRecipes.addShapelessRecipe(new ItemStack(slagIronPlate, 6), slagHook);
        LegacyRecipes.addShapelessRecipe(new ItemStack(cucumberSeeds, 2), cucumberFruit);
        LegacyRecipes.addShapelessRecipe(new ItemStack(pickel, 1), cucumberFruit, saltItem);
        LegacyRecipes.addShapelessRecipe(new ItemStack(pickel, 4), cucumberFruit, cucumberFruit, cucumberFruit, cucumberFruit, saltItem);
        LegacyRecipes.addShapelessRecipe(new ItemStack(pickel, 8), cucumberFruit, cucumberFruit, cucumberFruit, cucumberFruit, cucumberFruit, cucumberFruit, cucumberFruit, cucumberFruit, saltItem);
        LegacyRecipes.addRecipe(new ItemStack(RopeItem, 1), " S ", "  S", " S ", 'S', Items.STRING);
        LegacyRecipes.addRecipe(new ItemStack(RopeItem, 1), " S ", "S  ", " S ", 'S', Items.STRING);
        LegacyRecipes.addShapelessRecipe(new ItemStack(saltedPork), Items.COOKED_PORKCHOP, saltItem);
        LegacyRecipes.addShapelessRecipe(new ItemStack(saltedBeef), Items.COOKED_BEEF, saltItem);
        LegacyRecipes.addShapelessRecipe(new ItemStack(saltedFish), new ItemStack(Items.COOKED_FISH, 1, 0), saltItem);
        LegacyRecipes.addShapelessRecipe(new ItemStack(saltedSalmon), new ItemStack(Items.COOKED_FISH, 1, 1), saltItem);
        LegacyRecipes.addRecipe(new ItemStack(saltBlock), "SS", "SS", 'S', saltItem);
        LegacyRecipes.addRecipe(new ItemStack(RopeLadder, 8), "S S", "SSS", "S S", 'S', RopeItem);
        LegacyRecipes.addRecipe(new ItemStack(NetGrabber), "HS ", " S ", " SP", 'S', Items.STICK, 'H', Hook, 'P', IronPlate);
        LegacyRecipes.addRecipe(new ItemStack(NetItem, 4), "HCH", "SSS", "SSS", 'S', RopeItem, 'C', chainMail, 'H', Hook);
        LegacyRecipes.addRecipe(new ItemStack(RopeMaker), "HSH", "SWS", "PSP", 'S', Items.STICK, 'P', IronPlate, 'H', Hook, 'W', "plankWood");
        LegacyRecipes.addRecipe(new ItemStack(tether), "HWH", "SSS", "LLL", 'S', RopeItem, 'L', Items.LEATHER, 'H', Hook, 'W', "plankWood");
        LegacyRecipes.addRecipe(new ItemStack(bordingHook), "HPH", " S ", " S ", 'S', Items.STICK, 'P', IronPlate, 'H', Hook);
        LegacyRecipes.addRecipe(new ItemStack(CannonItem), "R R", "BBB", "W W", 'R', RopeItem, 'B', barrel, 'W', "plankWood");
        LegacyRecipes.addRecipe(new ItemStack(slagHook, 2), " M ", "PP ", "SM ", 'M', mold, 'S', slagBlade, 'P', slagIron);
        LegacyRecipes.addRecipe(new ItemStack(slagBlade), " M ", "PS ", " M ", 'M', mold, 'S', slagIron, 'P', slagIronPlate);
        LegacyRecipes.addRecipe(new ItemStack(slagBarrel), " M ", "SS ", " M ", 'M', mold, 'S', slagIron);
        LegacyRecipes.addRecipe(new ItemStack(mold, 8), "SCS", "CSC", "SCS", 'C', Items.CLAY_BALL, 'S', Items.STICK);
        LegacyRecipes.addShapelessRecipe(new ItemStack(slagChainMail, 2), slagIron, mold);
        LegacyRecipes.addRecipe(new ItemStack(ghostH), "CCC", "C C", 'C', ghostWrap);
        LegacyRecipes.addRecipe(new ItemStack(ghostC), "C C", "CCC", "CCC", 'C', ghostWrap);
        LegacyRecipes.addRecipe(new ItemStack(ghostL), "CCC", "C C", "C C", 'C', ghostWrap);
        LegacyRecipes.addRecipe(new ItemStack(ghostB), "C C", "C C", 'C', ghostWrap);
        LegacyRecipes.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET), "CCC", "C C", 'C', chainMail);
        LegacyRecipes.addRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE), "C C", "CCC", "CCC", 'C', chainMail);
        LegacyRecipes.addRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS), "CCC", "C C", "C C", 'C', chainMail);
        LegacyRecipes.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS), "C C", "C C", 'C', chainMail);
        LegacyRecipes.addRecipe(new ItemStack(slagIron), "II", "II", 'I', slagIronPlate);
        LegacyRecipes.addShapelessRecipe(new ItemStack(slagIronPlate, 4), slagIron);
        LegacyRecipes.addRecipe(new ItemStack(Cutlass), "#", "#", "S", '#', blade, 'S', Items.STICK);
        LegacyRecipes.addRecipe(new ItemStack(Grenado), " S ", "#X#", " # ", '#', IronPlate, 'X', Items.GUNPOWDER, 'S', Items.STRING);
        LegacyRecipes.addRecipe(new ItemStack(EmptyFlintlock), " IF", " iW", "  W", 'W', "plankWood", 'I', barrel, 'i', IronPlate, 'F', Items.FLINT_AND_STEEL);
        LegacyRecipes.addShapelessRecipe(new ItemStack(Shot, 3), Items.PAPER, IronPlate, IGPT);
        LegacyRecipes.addShapelessRecipe(new ItemStack(IGPT, 4), Items.GUNPOWDER);
        LegacyRecipes.addRecipe(new ItemStack(Items.GUNPOWDER), "##", "##", '#', IGPT);
        LegacyRecipes.addRecipe(new ItemStack(Keg), "W#W", "W#W", "W#W", '#', Items.GUNPOWDER, 'W', "plankWood");
        LegacyRecipes.addRecipe(new ItemStack(EmptyMusket), "IIF", " PW", "  W", 'I', barrel, 'P', IronPlate, 'F', Items.FLINT_AND_STEEL, 'W', "plankWood");
        LegacyRecipes.addRecipe(new ItemStack(EmptyMusketWithb), "IIF", "BPW", "  W", 'I', barrel, 'P', IronPlate, 'F', Items.FLINT_AND_STEEL, 'W', "plankWood", 'B', blade);
        LegacyRecipes.addRecipe(new ItemStack(Smelter), "CCC", "CBC", "CCC", 'C', Items.CLAY_BALL, 'B', Items.BUCKET);
        LegacyRecipes.addRecipe(new ItemStack(coolingRack), "SIS", "iIi", "SIS", 'S', Blocks.COBBLESTONE, 'I', Blocks.IRON_BARS, 'i', Items.IRON_INGOT);
        LegacyRecipes.addRecipe(new ItemStack(fishTrap), " W ", " C ", "SSS", 'W', "plankWood", 'C', NetItem, 'S', Items.STICK);
        LegacyRecipes.addRecipe(new ItemStack(CannonB1), " P ", "PGP", " P ", 'P', IronPlate, 'G', Items.GUNPOWDER);
        LegacyRecipes.addRecipe(new ItemStack(CannonB2), " P ", "PGP", "GPG", 'P', IronPlate, 'G', Items.GUNPOWDER);
        LegacyRecipes.addRecipe(new ItemStack(CannonB3), "GPG", "PGP", "GPG", 'P', IronPlate, 'G', Items.GUNPOWDER);
        MachineRecipes.init();
    }

    static void registerSpawns() {
        java.util.List<net.minecraft.world.biome.Biome> biomes = LegacySpawns.biomes(LegacySpawns::isOverworld);
        LegacySpawns.add(EntityRat.class, 1, 1, 1, EnumCreatureType.MONSTER, biomes);
        LegacySpawns.add(EntityPirateCutlass.class, 1, 1, 1, EnumCreatureType.MONSTER, biomes);
        LegacySpawns.add(EntityPirateGun.class, 1, 1, 1, EnumCreatureType.MONSTER, biomes);
        LegacySpawns.add(EntityPirateGrenade.class, 1, 1, 1, EnumCreatureType.MONSTER, biomes);
    }
}
