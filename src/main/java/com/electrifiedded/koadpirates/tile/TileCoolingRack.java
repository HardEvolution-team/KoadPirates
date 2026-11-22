package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.archaiclib.legacy.crafting.LegacyMachineRecipes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;

/** Cooling rack: quenches slag back into usable iron parts; "fuel" is a water bucket (1600) or snowball (400). */
public class TileCoolingRack extends TileFurnaceLike {
    public TileCoolingRack() {
        super("tile.koadpirates.coolingRack.name");
    }

    @Override protected LegacyMachineRecipes recipes() { return MachineRecipes.COOLING; }

    @Override
    protected int fuelValue(ItemStack fuel) {
        if (fuel.isEmpty()) return 0;
        Item i = fuel.getItem();
        return i == Items.WATER_BUCKET ? 1600 : i == Items.SNOWBALL ? 400 : 0;
    }

    @Override
    protected void onSmelted(ItemStack input) {
        this.world.playSound(null, this.pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.2F,
                1.6F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.8F);
    }
}
