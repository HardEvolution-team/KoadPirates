package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Thrown net: unfolds into an {@link EntityNetOut} where it lands. */
public class EntityNet extends EntityThrowableBase {
    public EntityNet(World world) {
        super(world);
    }

    public EntityNet(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    @Override
    protected void onImpact(RayTraceResult hit) {
        if (hit.entityHit != null) hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        if (!this.world.isRemote) {
            EntityNetOut net = new EntityNetOut(this.world);
            net.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.world.spawnEntity(net);
            this.setDead();
        }
    }
}
