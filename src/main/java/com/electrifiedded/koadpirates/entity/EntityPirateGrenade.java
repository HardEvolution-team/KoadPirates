package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/** "Pirate With Grenade" (1.7 EntityPirate2 / global id Pirate3). */
public class EntityPirateGrenade extends EntityPirateBase implements IRangedAttackMob {
    public EntityPirateGrenade(World world) {
        super(world);
        this.tasks.addTask(3, new EntityAIShotAttack(this, 0.25F, 80, 15.0F));
        addCommonTasks();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23);
    }

    @Override
    protected ItemStack heldItem() {
        return new ItemStack(PiratesContent.Grenado);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityGrenado g = new EntityGrenado(this.world, this);
        double dx = target.posX - this.posX;
        double dy = target.posY + target.getEyeHeight() - 1.1F - g.posY;
        double dz = target.posZ - this.posZ;
        float f1 = MathHelper.sqrt(dx * dx + dz * dz) * 0.2F;
        g.shoot(dx, dy + f1, dz, 1.6F, 12.0F);
        this.world.spawnEntity(g);
    }

    @Override
    public void setSwingingArms(boolean swinging) {
    }

    @Override
    protected void dropCommon(int looting) {
        drop(PiratesContent.IronPlate, this.rand.nextInt(2) + this.rand.nextInt(1 + looting));
        drop(PiratesContent.IGPT, this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + looting));
        drop(Items.GUNPOWDER, this.rand.nextInt(2) + this.rand.nextInt(1 + looting));
    }

    @Override
    protected void dropRare() {
        switch (this.rand.nextInt(3)) {
            case 0: this.dropItem(Items.IRON_INGOT, 2); break;
            case 1: this.dropItem(PiratesContent.Grenado, 1); break;
            default: this.dropItem(Items.GUNPOWDER, 3);
        }
    }
}
