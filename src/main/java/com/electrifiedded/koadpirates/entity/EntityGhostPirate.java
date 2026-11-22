package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Ghost pirate (1.7 squid-like water ghost): drains air on touch, shoots ghost shots at players within 24 blocks and
 * regenerates quickly unless hit with an enchanted item.
 */
public class EntityGhostPirate extends EntitySquid {
    private int shotTimer;
    public boolean canheal = true;
    private int deoxidiseValue = 50;

    public EntityGhostPirate(World world) {
        super(world);
        this.setSize(0.95F, 0.95F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        if (this.world.isRemote) return;
        player.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
        if (this.deoxidiseValue > player.getAir()) this.deoxidiseValue = player.getAir();
        player.setAir(player.getAir() - this.deoxidiseValue);
        for (int i = 0; i < 10; i++) this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float amount) {
        if (this.isEntityInvulnerable(src)) return false;
        if (src.getTrueSource() instanceof EntityPlayer) {
            ItemStack held = ((EntityPlayer)src.getTrueSource()).getHeldItemMainhand();
            if (!held.isEmpty() && held.isItemEnchanted()) this.canheal = false;
        }
        return super.attackEntityFrom(src, amount);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer near = this.world.getClosestPlayer(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, 24.0, false);
        if (near != null) {
            if (!this.world.isRemote && this.shotTimer > 60) {
                this.shotTimer = 0;
                EntityGhostShot shot = new EntityGhostShot(this.world, this);
                double dx = near.posX - this.posX;
                double dy = near.posY + this.getEyeHeight() - 1.1F - shot.posY;
                double dz = near.posZ - this.posZ;
                float f1 = MathHelper.sqrt(dx * dx + dz * dz) * 0.2F;
                shot.shoot(dx, dy + f1, dz, 1.6F, 12.0F);
                this.world.spawnEntity(shot);
            }
            this.shotTimer++;
        } else {
            this.canheal = true;
        }
        if (!this.world.isRemote && this.canheal && this.getHealth() < this.getMaxHealth()) this.heal(4.0F);
        for (int i = 0; i < 2; i++) {
            this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE,
                    this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                    this.posY + this.rand.nextDouble() + this.rand.nextInt(3) * this.height - 0.25,
                    this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                    (this.rand.nextDouble() - 0.5) * 2.0, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5) * 2.0);
        }
    }

    @Override protected SoundEvent getAmbientSound() { return null; }
    @Override protected SoundEvent getHurtSound(DamageSource src) { return null; }
    @Override protected SoundEvent getDeathSound() { return null; }
    @Override protected float getSoundVolume() { return 0.4F; }
    @Override protected ResourceLocation getLootTable() { return null; }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        int j = Math.max(0, this.rand.nextInt(3) - looting);
        if (j == 0) this.dropItem(PiratesContent.ghostWrap, 1);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY > 45.0 && this.posY < 63.0 && super.getCanSpawnHere();
    }
}
