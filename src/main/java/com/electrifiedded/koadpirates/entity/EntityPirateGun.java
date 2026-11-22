package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/** "Pirate With Gun" (1.7 EntityPirate1 / global id Pirate2). */
public class EntityPirateGun extends EntityPirateBase implements IRangedAttackMob {
    public EntityPirateGun(World world) {
        super(world);
        this.tasks.addTask(3, new EntityAIShotAttack(this, 0.25F, 70, 20.0F));
        addCommonTasks();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23);
    }

    @Override
    protected ItemStack heldItem() {
        return new ItemStack(PiratesContent.Flintlock);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityShot shot = new EntityShot(this.world, this, 3.0F);
        double dx = target.posX - this.posX;
        double dy = target.posY + target.getEyeHeight() - 1.1F - shot.posY;
        double dz = target.posZ - this.posZ;
        float f1 = MathHelper.sqrt(dx * dx + dz * dz) * 0.2F;
        shot.shoot(dx, dy + f1, dz, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_FIREWORK_BLAST, 3.9F, 0.4F);
        this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 0.9F, 1.3F);
        this.world.spawnEntity(shot);
    }

    @Override
    public void setSwingingArms(boolean swinging) {
    }

    @Override
    protected void dropCommon(int looting) {
        drop(PiratesContent.IronPlate, this.rand.nextInt(3) + this.rand.nextInt(1 + looting));
        drop(PiratesContent.Shot, this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + looting));
        drop(PiratesContent.IGPT, this.rand.nextInt(2) + this.rand.nextInt(1 + looting));
    }

    @Override
    protected void dropRare() {
        switch (this.rand.nextInt(3)) {
            case 0: this.dropItem(Items.IRON_INGOT, 1); break;
            case 1: this.dropItem(PiratesContent.barrel, 1); break;
            default: this.dropItem(Items.GUNPOWDER, 2);
        }
    }
}
