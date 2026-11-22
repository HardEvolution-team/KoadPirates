package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;

/** 1.7 EntityAIShotAttack: keeps distance, fires every maxRangedAttackTime ticks while the target is visible. */
public class EntityAIShotAttack extends EntityAIBase {
    private final EntityLiving host;
    private final IRangedAttackMob ranged;
    private EntityLivingBase target;
    private int rangedAttackTime;
    private final float moveSpeed;
    private int seeTime;
    private final int maxRangedAttackTime;
    private final float maxAttackDistance;

    public EntityAIShotAttack(IRangedAttackMob mob, float speed, int maxTime, float maxDistance) {
        this.ranged = mob;
        this.host = (EntityLiving)mob;
        this.moveSpeed = speed;
        this.maxRangedAttackTime = maxTime;
        this.maxAttackDistance = maxDistance * maxDistance;
        this.rangedAttackTime = maxTime / 2;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        EntityLivingBase t = this.host.getAttackTarget();
        if (t == null || !t.isEntityAlive()) return false;
        this.target = t;
        return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute() || !this.host.getNavigator().noPath();
    }

    @Override
    public void resetTask() {
        this.target = null;
        this.seeTime = 0;
        this.rangedAttackTime = this.maxRangedAttackTime / 2;
    }

    @Override
    public void updateTask() {
        if (this.target == null) return;
        double d = this.host.getDistanceSq(this.target.posX, this.target.getEntityBoundingBox().minY, this.target.posZ);
        boolean see = this.host.getEntitySenses().canSee(this.target);
        this.seeTime = see ? this.seeTime + 1 : 0;
        if (d <= this.maxAttackDistance && this.seeTime >= 20) this.host.getNavigator().clearPath();
        if (d > this.maxAttackDistance - this.maxAttackDistance / 2.0) {
            this.rangedAttackTime--;
        } else {
            this.host.getNavigator().tryMoveToEntityLiving(this.target, this.moveSpeed);
        }
        this.host.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
        this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);
        if (this.rangedAttackTime <= 0 && d <= this.maxAttackDistance && see) {
            this.ranged.attackEntityWithRangedAttack(this.target, this.moveSpeed);
            this.rangedAttackTime = this.maxRangedAttackTime;
        }
    }
}
