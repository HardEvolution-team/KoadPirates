package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.archaiclib.legacy.crafting.LegacyMachineRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.electrifiedded.koadpirates.PiratesContent.*;

/** Recipe lists of the smelter, cooling rack and spinning wheel (1.7 RecipesSmelter / RecipesCoolingRack / RecipesRope). */
public final class MachineRecipes {
    private MachineRecipes() {}

    public static final LegacyMachineRecipes SMELTER = new LegacyMachineRecipes();
    public static final LegacyMachineRecipes COOLING = new LegacyMachineRecipes();
    public static final LegacyMachineRecipes ROPE = new LegacyMachineRecipes();

    public static void init() {
        SMELTER.add(Items.IRON_INGOT, new ItemStack(slagIron), 0.0F);
        SMELTER.add(Blocks.IRON_ORE, new ItemStack(slagIron), 0.7F);
        SMELTER.add(IronPlate, new ItemStack(slagIronPlate), 0.0F);
        SMELTER.add(chainMail, new ItemStack(slagChainMail), 0.0F);
        SMELTER.add(blade, new ItemStack(slagBlade), 0.0F);
        SMELTER.add(barrel, new ItemStack(slagBarrel), 0.0F);
        SMELTER.add(Items.BUCKET, new ItemStack(slagIron, 3), 0.0F);
        SMELTER.add(Items.IRON_DOOR, new ItemStack(slagIron, 6), 0.0F);
        SMELTER.add(Hook, new ItemStack(slagHook), 0.0F);
        SMELTER.add(Blocks.RAIL, new ItemStack(slagIronPlate), 0.0F);
        SMELTER.add(Items.CAULDRON, new ItemStack(slagIron, 7), 0.0F);

        COOLING.add(slagIron, new ItemStack(Items.IRON_INGOT), 0.0F);
        COOLING.add(slagIronPlate, new ItemStack(IronPlate), 0.0F);
        COOLING.add(slagChainMail, new ItemStack(chainMail), 0.0F);
        COOLING.add(slagBarrel, new ItemStack(barrel), 0.0F);
        COOLING.add(slagBlade, new ItemStack(blade), 0.0F);
        COOLING.add(slagHook, new ItemStack(Hook), 0.0F);

        ROPE.add(Blocks.WOOL, new ItemStack(Items.STRING, 2), 0.0F);
        ROPE.add(Items.STRING, new ItemStack(RopeItem), 0.0F);
    }
}
