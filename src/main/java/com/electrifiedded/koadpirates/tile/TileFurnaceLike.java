package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.archaiclib.legacy.crafting.LegacyMachineRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

/** The original's 3-slot furnace clones (input 0, fuel 1, output 2; 200 tick cook time). */
public abstract class TileFurnaceLike extends TileInventory implements ITickable {
    public int burnTime, itemBurnTime, cookTime;

    protected TileFurnaceLike(String name) {
        super(3, name);
    }

    protected abstract LegacyMachineRecipes recipes();

    protected abstract int fuelValue(ItemStack fuel);

    protected void onSmelted(ItemStack input) {}

    protected void clientEffects() {}

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public int getCookProgressScaled(int px) {
        return this.cookTime * px / 200;
    }

    public int getBurnTimeRemainingScaled(int px) {
        int max = this.itemBurnTime == 0 ? 200 : this.itemBurnTime;
        return this.burnTime * px / max;
    }

    protected boolean canSmelt() {
        ItemStack in = this.stacks.get(0);
        if (in.isEmpty()) return false;
        ItemStack res = recipes().getResult(in);
        if (res.isEmpty()) return false;
        ItemStack out = this.stacks.get(2);
        if (out.isEmpty()) return true;
        if (!out.isItemEqual(res)) return false;
        int n = out.getCount() + res.getCount();
        return n <= this.getInventoryStackLimit() && n <= out.getMaxStackSize();
    }

    protected void smeltItem() {
        ItemStack in = this.stacks.get(0);
        ItemStack res = recipes().getResult(in);
        ItemStack out = this.stacks.get(2);
        if (out.isEmpty()) this.stacks.set(2, res.copy());
        else out.grow(res.getCount());
        onSmelted(in);
        in.shrink(1);
    }

    protected void consumeFuel() {
        ItemStack fuel = this.stacks.get(1);
        Item container = fuel.getItem().getContainerItem();
        fuel.shrink(1);
        if (fuel.isEmpty() && container != null) this.stacks.set(1, new ItemStack(container));
    }

    @Override
    public void update() {
        boolean wasBurning = this.burnTime > 0;
        boolean dirty = false;
        if (this.burnTime > 0) {
            this.burnTime--;
            if (this.world.isRemote) clientEffects();
        }
        if (!this.world.isRemote) {
            if (this.burnTime == 0 && canSmelt()) {
                this.itemBurnTime = this.burnTime = fuelValue(this.stacks.get(1));
                if (this.burnTime > 0) {
                    dirty = true;
                    consumeFuel();
                }
            }
            if (isBurning() && canSmelt()) {
                if (++this.cookTime >= 200) {
                    this.cookTime = 0;
                    smeltItem();
                    dirty = true;
                }
            } else {
                this.cookTime = 0;
            }
            if (wasBurning != this.burnTime > 0) {
                dirty = true;
                net.minecraft.block.state.IBlockState s = this.world.getBlockState(this.pos);
                this.world.notifyBlockUpdate(this.pos, s, s, 3);
            }
        }
        if (dirty) this.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack s) {
        return i != 2 && (i != 1 || fuelValue(s) > 0);
    }

    @Override public int getField(int id) { return id == 0 ? this.cookTime : id == 1 ? this.burnTime : id == 2 ? this.itemBurnTime : 0; }
    @Override public int getFieldCount() { return 3; }

    @Override
    public void setField(int id, int v) {
        if (id == 0) this.cookTime = v;
        else if (id == 1) this.burnTime = v;
        else if (id == 2) this.itemBurnTime = v;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.burnTime = tag.getShort("BurnTime");
        this.cookTime = tag.getShort("CookTime");
        this.itemBurnTime = tag.getShort("ItemBurnTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setShort("BurnTime", (short)this.burnTime);
        tag.setShort("CookTime", (short)this.cookTime);
        tag.setShort("ItemBurnTime", (short)this.itemBurnTime);
        return tag;
    }
}
