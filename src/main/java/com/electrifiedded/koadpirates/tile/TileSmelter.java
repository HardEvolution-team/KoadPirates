package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.archaiclib.legacy.crafting.LegacyMachineRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumParticleTypes;

/** Clay smelter: melts iron things into slag (normal furnace fuel). Melting big iron items uses up the whole fuel. */
public class TileSmelter extends TileFurnaceLike {
    public TileSmelter() {
        super("tile.koadpirates.smelter.name");
    }

    @Override protected LegacyMachineRecipes recipes() { return MachineRecipes.SMELTER; }
    @Override protected int fuelValue(ItemStack fuel) { return TileEntityFurnace.getItemBurnTime(fuel); }

    @Override
    protected void onSmelted(ItemStack input) {
        Item i = input.getItem();
        if (i == Items.BUCKET || i == Items.IRON_DOOR || i == Items.CAULDRON) this.burnTime = 0;
    }

    @Override
    protected void clientEffects() {
        double x = this.pos.getX() + 0.5, y = this.pos.getY() + this.world.rand.nextFloat(), z = this.pos.getZ() + 0.5;
        for (int n = 0; n < 21; n++) {
            if (this.world.rand.nextFloat() > 0.9F) {
                double ox = (this.world.rand.nextInt(7) - 3) * 0.1, oz = (this.world.rand.nextInt(7) - 3) * 0.1;
                this.world.spawnParticle(EnumParticleTypes.FLAME, x + ox, y, z + oz, 0.0, 0.0, 0.0);
            }
        }
    }
}
