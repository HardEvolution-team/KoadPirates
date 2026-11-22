package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.koadpirates.entity.PirateNames;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

/** Cursed chest: 27-slot chest that summons ghost pirates (2 at a time, max 12 around) every 30-40 s while a player is near. */
public class TileCursedChest extends TileInventory implements ITickable {
    private int delay = 100;
    private String mobID = "GhostPirate";

    public TileCursedChest() {
        super(27, "Cursed Chest");
    }

    @Override public boolean hasCustomName() { return true; }

    @Override
    public void update() {
        if (this.world.isRemote) return;
        if (this.world.getClosestPlayer(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 24.0, false) == null) return;
        if (this.delay > 0) {
            this.delay--;
            return;
        }
        for (int n = 0; n < 2; n++) {
            Entity e = PirateNames.create(this.mobID, this.world);
            if (e == null) return;
            int count = this.world.getEntitiesWithinAABB(e.getClass(), new AxisAlignedBB(this.pos).grow(8.0, 4.0, 8.0)).size();
            if (count >= 12) break;
            double x = this.pos.getX() + (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 4.0;
            double y = this.pos.getY() + this.world.rand.nextInt(3) - 1;
            double z = this.pos.getZ() + (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 4.0;
            e.setLocationAndAngles(x, y, z, this.world.rand.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(e);
            this.world.playEvent(2004, this.pos, 0);
        }
        this.delay = 600 + this.world.rand.nextInt(200);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("EntityId")) this.mobID = tag.getString("EntityId");
        this.delay = tag.getShort("Delay");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("EntityId", this.mobID);
        tag.setShort("Delay", (short)this.delay);
        return tag;
    }
}
