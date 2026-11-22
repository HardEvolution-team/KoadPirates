package com.electrifiedded.koadpirates.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

/** Small IInventory tile base (1.7 ItemStack[] tiles). */
public abstract class TileInventory extends TileEntity implements IInventory {
    protected NonNullList<ItemStack> stacks;
    private final String name;

    protected TileInventory(int size, String name) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
        this.name = name;
    }

    @Override public int getSizeInventory() { return this.stacks.size(); }
    @Override public boolean isEmpty() { for (ItemStack s : this.stacks) if (!s.isEmpty()) return false; return true; }
    @Override public ItemStack getStackInSlot(int i) { return this.stacks.get(i); }
    @Override public ItemStack decrStackSize(int i, int n) { ItemStack s = ItemStackHelper.getAndSplit(this.stacks, i, n); if (!s.isEmpty()) this.markDirty(); return s; }
    @Override public ItemStack removeStackFromSlot(int i) { return ItemStackHelper.getAndRemove(this.stacks, i); }

    @Override
    public void setInventorySlotContents(int i, ItemStack s) {
        this.stacks.set(i, s);
        if (s.getCount() > this.getInventoryStackLimit()) s.setCount(this.getInventoryStackLimit());
        this.markDirty();
    }

    @Override public int getInventoryStackLimit() { return 64; }

    @Override
    public boolean isUsableByPlayer(EntityPlayer p) {
        return this.world.getTileEntity(this.pos) == this && p.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }

    @Override public void openInventory(EntityPlayer p) {}
    @Override public void closeInventory(EntityPlayer p) {}
    @Override public boolean isItemValidForSlot(int i, ItemStack s) { return true; }
    @Override public int getField(int id) { return 0; }
    @Override public void setField(int id, int v) {}
    @Override public int getFieldCount() { return 0; }
    @Override public void clear() { this.stacks.clear(); }
    @Override public String getName() { return this.name; }
    @Override public boolean hasCustomName() { return false; }
    @Override public ITextComponent getDisplayName() { return new TextComponentTranslation(this.name); }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.stacks);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        ItemStackHelper.saveAllItems(tag, this.stacks);
        return tag;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @SuppressWarnings("unused")
    protected static ITextComponent literal(String s) { return new TextComponentString(s); }
}
