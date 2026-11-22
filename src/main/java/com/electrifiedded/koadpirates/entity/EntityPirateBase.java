package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

/** 1.7 EntityMopirate: zombie-sounding pirate with a fixed attack strength and a held item. */
public abstract class EntityPirateBase extends EntityMob {
    protected int attackStrength = 2;

    public EntityPirateBase(World world) {
        super(world);
        this.experienceValue = 5;
    }

    protected abstract ItemStack heldItem();

    protected void addCommonTasks() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
        data = super.onInitialSpawn(difficulty, data);
        equip();
        return data;
    }

    private void equip() {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, heldItem());
        this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.0F);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        // spawners / commands skip onInitialSpawn
        if (!this.world.isRemote && this.getHeldItemMainhand().isEmpty()) equip();
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        float i = this.attackStrength;
        if (this.isPotionActive(MobEffects.STRENGTH)) i += 3 << this.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
        if (this.isPotionActive(MobEffects.WEAKNESS)) i -= 2 << this.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier();
        return target.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    @Override protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_ZOMBIE_AMBIENT; }
    @Override protected SoundEvent getHurtSound(DamageSource src) { return SoundEvents.ENTITY_ZOMBIE_HURT; }
    @Override protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_ZOMBIE_DEATH; }
    @Override protected ResourceLocation getLootTable() { return null; }

    /** 1.7 dropFewItems + dropRareDrop. */
    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        dropCommon(looting);
        if (recentlyHit && this.rand.nextInt(200) - looting < 5) dropRare();
    }

    protected abstract void dropCommon(int looting);

    protected abstract void dropRare();

    protected void drop(Item item, int count) {
        for (int k = 0; k < count; k++) this.dropItem(item, 1);
    }
}
