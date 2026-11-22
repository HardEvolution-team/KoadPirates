package com.electrifiedded.koadpirates.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

/**
 * Spinning wheel: wool -> 2 string, 3 string -> rope. Needs a "wood condition" (planks) and an "iron condition"
 * (iron ingot), both fed through slot 1 and worn down by each craft; only works while a player stands next to it.
 */
public class TileRopeMaker extends TileInventory implements ITickable {
    public int cookTime, wood, iron;

    public TileRopeMaker() {
        super(3, "tile.koadpirates.ropeMaker.name");
    }

    public boolean isBurning() { return this.wood > 0 && this.iron > 0; }
    public int getCookProgressScaled(int px) { return this.cookTime * px / 200; }

    private boolean canSmelt() {
        ItemStack in = this.stacks.get(0);
        if (in.isEmpty()) return false;
        if (in.getItem() == Items.STRING && in.getCount() < 3) return false;
        ItemStack res = MachineRecipes.ROPE.getResult(in);
        if (res.isEmpty()) return false;
        ItemStack out = this.stacks.get(2);
        return out.isEmpty() || (out.isItemEqual(res) && out.getCount() + res.getCount() <= out.getMaxStackSize());
    }

    @Override
    public void update() {
        if (this.world.isRemote) return;
        if (this.world.getClosestPlayer(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 2.0, false) == null) return;
        ItemStack fuel = this.stacks.get(1);
        if (this.wood <= 0 && !fuel.isEmpty() && fuel.getItem() == Item_PLANKS()) { this.wood = 100; fuel.shrink(1); markDirty(); }
        fuel = this.stacks.get(1);
        if (this.iron <= 0 && !fuel.isEmpty() && fuel.getItem() == Items.IRON_INGOT) { this.iron = 200; fuel.shrink(1); markDirty(); }
        if (isBurning() && canSmelt()) {
            if (++this.cookTime >= 200) {
                this.cookTime = 0;
                ItemStack in = this.stacks.get(0);
                ItemStack res = MachineRecipes.ROPE.getResult(in).copy();
                ItemStack out = this.stacks.get(2);
                if (out.isEmpty()) this.stacks.set(2, res); else out.grow(res.getCount());
                in.shrink(in.getItem() == Items.STRING ? 3 : 1);
                this.wood--;
                this.iron--;
                markDirty();
            }
        } else {
            this.cookTime = 0;
        }
    }

    private static net.minecraft.item.Item Item_PLANKS() {
        return net.minecraft.item.Item.getItemFromBlock(Blocks.PLANKS);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack s) {
        if (i == 2) return false;
        if (i == 1) return s.getItem() == Items.IRON_INGOT || Block.getBlockFromItem(s.getItem()) == Blocks.PLANKS;
        return true;
    }

    @Override public int getField(int id) { return id == 0 ? this.cookTime : id == 1 ? this.wood : id == 2 ? this.iron : 0; }
    @Override public int getFieldCount() { return 3; }
    @Override public void setField(int id, int v) { if (id == 0) this.cookTime = v; else if (id == 1) this.wood = v; else if (id == 2) this.iron = v; }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.cookTime = tag.getShort("CookTime");
        this.wood = tag.getShort("Wood");
        this.iron = tag.getShort("Iron");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setShort("CookTime", (short)this.cookTime);
        tag.setShort("Wood", (short)this.wood);
        tag.setShort("Iron", (short)this.iron);
        return tag;
    }
}
