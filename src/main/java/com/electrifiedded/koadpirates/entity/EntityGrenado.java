package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Thrown grenade: small explosion without block damage. */
public class EntityGrenado extends EntityThrowableBase {
    public EntityGrenado(World world) {
        super(world);
    }

    public EntityGrenado(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    @Override
    protected void onImpact(RayTraceResult hit) {
        if (this.world.isRemote) return;
        if (hit.entityHit != null) hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 2.0F);
        this.world.createExplosion(this, this.posX, this.posY, this.posZ, 3.5F, false);
        this.setDead();
    }
}
