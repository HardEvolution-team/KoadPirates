package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Ghost pirate projectile: blinds, confuses, flings the target and takes its air. */
public class EntityGhostShot extends EntityThrowableBase {
    public EntityGhostShot(World world) {
        super(world);
    }

    public EntityGhostShot(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    @Override
    protected void onImpact(RayTraceResult hit) {
        if (hit.entityHit instanceof EntityLivingBase && hit.entityHit != this.getThrower()) {
            EntityLivingBase t = (EntityLivingBase)hit.entityHit;
            t.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 50, 0));
            t.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
            t.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0));
            t.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 50, 0));
            t.motionX = this.rand.nextInt(15) - 7;
            t.motionY = this.rand.nextInt(15) - 9;
            t.motionZ = this.rand.nextInt(15) - 7;
            t.velocityChanged = true;
            if (t.isPotionActive(MobEffects.WATER_BREATHING) && this.rand.nextInt(100) == 0) {
                t.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 50, 2));
            }
            t.setAir(t.getAir() * 2 / 3);
        }
        if (!this.world.isRemote) this.setDead();
    }
}
