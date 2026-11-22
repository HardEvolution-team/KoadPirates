package com.electrifiedded.koadpirates.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Cannon balls x1/x2/x3: low gravity, block-breaking explosion of power 2/3/4. */
public abstract class EntityCannonBall extends EntityThrowableBase {
    public EntityCannonBall(World world) {
        super(world);
    }

    public EntityCannonBall(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    protected abstract float power();

    @Override
    protected float getGravityVelocity() {
        return 0.01F;
    }

    @Override
    protected void onImpact(RayTraceResult hit) {
        if (this.world.isRemote) return;
        if (hit.entityHit != null) hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 2.0F);
        this.setDead();
        this.world.createExplosion(this, this.posX, this.posY, this.posZ, power(), true);
    }

    public static EntityCannonBall create(int type, World world, EntityLivingBase shooter) {
        switch (type) {
            case 2: return new Medium(world, shooter);
            case 3: return new Heavy(world, shooter);
            default: return new Light(world, shooter);
        }
    }

    public static class Light extends EntityCannonBall {
        public Light(World w) { super(w); }
        public Light(World w, EntityLivingBase t) { super(w, t); }
        @Override protected float power() { return 2.0F; }
    }

    public static class Medium extends EntityCannonBall {
        public Medium(World w) { super(w); }
        public Medium(World w, EntityLivingBase t) { super(w, t); }
        @Override protected float power() { return 3.0F; }
    }

    public static class Heavy extends EntityCannonBall {
        public Heavy(World w) { super(w); }
        public Heavy(World w, EntityLivingBase t) { super(w, t); }
        @Override protected float power() { return 4.0F; }
    }
}
