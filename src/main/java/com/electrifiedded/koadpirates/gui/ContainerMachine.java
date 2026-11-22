package com.electrifiedded.koadpirates.gui;

import com.electrifiedded.koadpirates.tile.TileFishTrap;
import com.electrifiedded.koadpirates.tile.TileRopeMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/** Container of all four machines (furnace layout; fish trap: one output slot; spinning wheel: fuel slot bottom-left). */
public class ContainerMachine extends Container {
    private final IInventory tile;
    private final int machineSlots;
    private final int[] fields;

    public ContainerMachine(InventoryPlayer inv, IInventory tile) {
        this.tile = tile;
        if (tile instanceof TileFishTrap) {
            this.addSlotToContainer(new OutSlot(tile, 0, 62, 35));
        } else if (tile instanceof TileRopeMaker) {
            this.addSlotToContainer(new Slot(tile, 0, 55, 35));
            this.addSlotToContainer(new Slot(tile, 1, 17, 64));
            this.addSlotToContainer(new OutSlot(tile, 2, 116, 35));
        } else {
            this.addSlotToContainer(new Slot(tile, 0, 56, 17));
            this.addSlotToContainer(new Slot(tile, 1, 56, 53));
            this.addSlotToContainer(new OutSlot(tile, 2, 116, 35));
        }
        this.machineSlots = this.inventorySlots.size();
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 9; c++)
                this.addSlotToContainer(new Slot(inv, c + r * 9 + 9, 8 + c * 18, 84 + r * 18));
        for (int c = 0; c < 9; c++) this.addSlotToContainer(new Slot(inv, c, 8 + c * 18, 142));
        this.fields = new int[tile.getFieldCount()];
    }

    private static class OutSlot extends Slot {
        OutSlot(IInventory inv, int i, int x, int y) { super(inv, i, x, y); }
        @Override public boolean isItemValid(ItemStack s) { return false; }
    }

    @Override
    public void addListener(IContainerListener l) {
        super.addListener(l);
        l.sendAllWindowProperties(this, this.tile);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.fields.length; i++) {
            int v = this.tile.getField(i);
            if (v != this.fields[i]) {
                for (IContainerListener l : this.listeners) l.sendWindowProperty(this, i, v);
                this.fields[i] = v;
            }
        }
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tile.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer p) {
        return this.tile.isUsableByPlayer(p);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot == null || !slot.getHasStack()) return copy;
        ItemStack s = slot.getStack();
        copy = s.copy();
        int end = this.inventorySlots.size();
        if (index < this.machineSlots) {
            if (!this.mergeItemStack(s, this.machineSlots, end, true)) return ItemStack.EMPTY;
        } else {
            boolean moved = false;
            for (int i = 0; i < this.machineSlots && !moved; i++) {
                if (this.tile.isItemValidForSlot(i, s) && this.inventorySlots.get(i).isItemValid(s)) moved = this.mergeItemStack(s, i, i + 1, false);
            }
            if (!moved) {
                int hot = end - 9;
                if (index < hot) { if (!this.mergeItemStack(s, hot, end, false)) return ItemStack.EMPTY; }
                else if (!this.mergeItemStack(s, this.machineSlots, hot, false)) return ItemStack.EMPTY;
            }
        }
        if (s.isEmpty()) slot.putStack(ItemStack.EMPTY); else slot.onSlotChanged();
        if (s.getCount() == copy.getCount()) return ItemStack.EMPTY;
        slot.onTake(player, s);
        return copy;
    }
}
