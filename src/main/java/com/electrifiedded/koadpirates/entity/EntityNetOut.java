package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/** A spread net: everything in it is slowed like in cobweb (spiders are stopped). Removed with the net reclaimer. */
public class EntityNetOut extends Entity {
    public EntityNetOut(World world) {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        player.setInWeb();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.hasNoGravity() && !this.onGround) {
            this.motionY -= 0.04;
            this.move(net.minecraft.entity.MoverType.SELF, 0, this.motionY, 0);
            this.motionY *= 0.98;
        }
        for (Entity e : this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(1.0))) {
            if (e instanceof EntityNetOut) continue;
            if (e instanceof EntitySpider) {
                e.motionX = 0.0;
                e.motionY = 0.0;
                e.motionZ = 0.0;
            } else {
                e.setInWeb();
            }
        }
    }

    @Override protected void entityInit() {}
    @Override protected void readEntityFromNBT(NBTTagCompound tag) {}
    @Override protected void writeEntityToNBT(NBTTagCompound tag) {}
}
