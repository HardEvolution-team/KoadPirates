package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Musket / flintlock ball (1.7 arrow clone; here a fast throwable that is gone on block impact). */
public class EntityShot extends EntityThrowableBase {
    private double damage = 3.2;
    private int knockback;

    public EntityShot(World world) {
        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityShot(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter);
        this.setSize(0.5F, 0.5F);
        LegacyThrowable.applyLegacyThrowerInit(this, shooter, velocity * 1.5F, 1.0F, 0.0F);
        if (!(shooter instanceof EntityPlayer)) this.damage = 4.0;
    }

    public void setDamage(double d) { this.damage = d; }
    public double getDamage() { return this.damage; }
    public void setKnockbackStrength(int k) { this.knockback = k; }

    @Override
    protected float getGravityVelocity() {
        return 0.05F;
    }

    @Override
    protected void onImpact(RayTraceResult hit) {
        if (this.world.isRemote) return;
        Entity e = hit.entityHit;
        if (e != null) {
            float speed = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            int dmg = Math.max(2, MathHelper.ceil(speed * this.damage - 2.0));
            Entity thrower = this.getThrower();
            DamageSource src = DamageSource.causeThrownDamage(this, thrower == null ? this : thrower);
            if (this.isBurning() && !(e instanceof EntityEnderman)) e.setFire(5);
            if (e.attackEntityFrom(src, dmg)) {
                if (this.knockback > 0) {
                    float h = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                    if (h > 0.0F) e.addVelocity(this.motionX * this.knockback * 0.6F / h, 0.1, this.motionZ * this.knockback * 0.6F / h);
                }
                if (!(e instanceof EntityEnderman)) this.setDead();
                return;
            }
        } else {
            this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        }
        this.setDead();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setDouble("damage", this.damage);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("damage")) this.damage = tag.getDouble("damage");
    }
}
