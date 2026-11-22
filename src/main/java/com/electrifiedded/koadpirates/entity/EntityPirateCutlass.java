package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/** "Pirate With Cutlass" (1.7 EntityPirate / global id Pirate1). */
public class EntityPirateCutlass extends EntityPirateBase {
    public EntityPirateCutlass(World world) {
        super(world);
        this.attackStrength = 4;
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
        this.tasks.addTask(4, new EntityAIMoveThroughVillage(this, 1.0, false));
        addCommonTasks();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.37);
    }

    @Override
    protected ItemStack heldItem() {
        return new ItemStack(PiratesContent.Cutlass);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.isInWater() ? 0.75 : 0.37);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        if (super.attackEntityAsMob(target)) {
            if (target instanceof EntityLivingBase) {
                ((EntityLivingBase)target).hurtResistantTime = 0;
                target.attackEntityFrom(DamageSource.GENERIC, 2.0F);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void dropCommon(int looting) {
        drop(PiratesContent.IronPlate, this.rand.nextInt(3) + this.rand.nextInt(1 + looting));
        drop(PiratesContent.IGPT, this.rand.nextInt(2) + this.rand.nextInt(1 + looting));
    }

    @Override
    protected void dropRare() {
        switch (this.rand.nextInt(3)) {
            case 0: this.dropItem(Items.IRON_INGOT, 1); break;
            case 1: this.dropItem(PiratesContent.blade, 1); break;
            default: this.dropItem(PiratesContent.Hook, 1);
        }
    }
}
