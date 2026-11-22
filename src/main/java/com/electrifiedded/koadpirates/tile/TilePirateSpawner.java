package com.electrifiedded.koadpirates.tile;

import com.electrifiedded.koadpirates.entity.PirateNames;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

/** Pirate ship spawner: up to 3 pirates every 7-20 s while a player is within 24 blocks (max 12 around). */
public class TilePirateSpawner extends TileEntity implements ITickable {
    private int delay = 1;
    private String mobID = "Pirate1";

    public void setMobID(String id) { this.mobID = id; }
    public String getMobID() { return this.mobID; }

    @Override
    public void update() {
        if (this.world.isRemote) return;
        if (this.world.getClosestPlayer(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 24.0, false) == null) return;
        if (this.delay > 0) {
            this.delay--;
            return;
        }
        for (int n = 0; n < 3; n++) {
            Entity e = PirateNames.create(this.mobID, this.world);
            if (e == null) return;
            int count = this.world.getEntitiesWithinAABB(e.getClass(), new AxisAlignedBB(this.pos).grow(8.0, 4.0, 8.0)).size();
            if (count >= 12) break;
            double x = this.pos.getX() + (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 4.0;
            double y = this.pos.getY() + this.world.rand.nextInt(3) - 1;
            double z = this.pos.getZ() + (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 4.0;
            e.setLocationAndAngles(x, y, z, this.world.rand.nextFloat() * 360.0F, 0.0F);
            if (e instanceof EntityLiving && !((EntityLiving)e).isNotColliding()) continue;
            this.world.spawnEntity(e);
            for (String extra : new String[]{"Pirate1", "Pirate2", "Pirate3"}) {
                if (this.world.rand.nextInt(50) == 1) {
                    Entity m = PirateNames.create(extra, this.world);
                    if (m != null) {
                        m.setLocationAndAngles(x, y, z, this.world.rand.nextFloat() * 360.0F, 0.0F);
                        this.world.spawnEntity(m);
                    }
                }
            }
            this.world.playEvent(2004, this.pos, 0);
        }
        this.delay = 150 + this.world.rand.nextInt(250);
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
