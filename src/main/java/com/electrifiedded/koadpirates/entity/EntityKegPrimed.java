package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/** Lit powder keg (1.7 EntityKegPrimed - a short-fused, power 4 TNT). */
public class EntityKegPrimed extends Entity {
    public int fuse;

    public EntityKegPrimed(World world) {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
    }

    public EntityKegPrimed(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
        float f = (float)(Math.random() * Math.PI * 2.0);
        this.motionX = -((float)Math.sin(f)) * 0.02F;
        this.motionY = 0.2F;
        this.motionZ = -((float)Math.cos(f)) * 0.02F;
        this.fuse = 1 + world.rand.nextInt(1) + 1;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override protected void entityInit() {}
    @Override protected boolean canTriggerWalking() { return false; }
    @Override public boolean canBeCollidedWith() { return !this.isDead; }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.04F;
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.98F;
        this.motionY *= 0.98F;
        this.motionZ *= 0.98F;
        if (this.onGround) {
            this.motionX *= 0.7F;
            this.motionZ *= 0.7F;
            this.motionY *= -0.5;
        }
        if (this.fuse-- <= 0) {
            this.setDead();
            if (!this.world.isRemote) this.world.createExplosion(null, this.posX, this.posY, this.posZ, 4.0F, true);
        } else {
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setByte("Fuse", (byte)this.fuse);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        this.fuse = tag.getByte("Fuse");
    }
}
