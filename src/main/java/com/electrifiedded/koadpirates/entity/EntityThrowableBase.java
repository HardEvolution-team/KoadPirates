package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.world.World;

/** 1.7 EntityThrowable semantics: the thrower constructor also aims along the thrower's look (velocity 1.5). */
public abstract class EntityThrowableBase extends EntityThrowable {
    public EntityThrowableBase(World world) {
        super(world);
    }

    public EntityThrowableBase(World world, EntityLivingBase thrower) {
        super(world, thrower);
        LegacyThrowable.applyLegacyThrowerInit(this, thrower);
    }

    public EntityThrowableBase(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
}
