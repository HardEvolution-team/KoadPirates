package com.electrifiedded.koadpirates.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Invisible anchor of the tether / boarding hook: while its player stays next to a wall (or leaves) and below the
 * anchor, the player hangs there without fall damage. Dies when the player moves away.
 */
public class EntityTether extends Entity {
    private EntityPlayer shooter;
    private boolean needSneak;

    public EntityTether(World world) {
        super(world);
        this.setSize(0.25F, 0.25F);
    }

    public void setShooter(EntityPlayer p) { this.shooter = p; }
    public void setSNeed(boolean b) { this.needSneak = b; }

    /** solid top surface or leaves next to the player (1.7 boarding hook rule) */
    public static boolean nextToWall(World world, Entity e) {
        BlockPos p = new BlockPos(MathHelper.floor(e.posX), MathHelper.floor(e.getEntityBoundingBox().minY), MathHelper.floor(e.posZ));
        for (EnumFacing f : EnumFacing.HORIZONTALS) {
            BlockPos q = p.offset(f);
            Block b = world.getBlockState(q).getBlock();
            if (world.getBlockState(q).isSideSolid(world, q, EnumFacing.UP) || b == Blocks.LEAVES || b == Blocks.LEAVES2) return true;
        }
        return false;
    }

    public static void cancelFall(EntityPlayer p) {
        int dmg = MathHelper.ceil(p.fallDistance - 3.0F);
        if (dmg > 0) {
            p.playSound(dmg > 4 ? SoundEvents.ENTITY_GENERIC_BIG_FALL : SoundEvents.ENTITY_GENERIC_SMALL_FALL, 1.0F, 1.0F);
            p.attackEntityFrom(DamageSource.FALL, dmg);
        }
        p.fallDistance = 0.0F;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote) return;
        if (this.shooter == null || this.shooter.isDead) {
            this.setDead();
            return;
        }
        boolean wall = nextToWall(this.world, this.shooter);
        cancelFall(this.shooter);
        if (this.shooter.posY < this.posY && wall) {
            this.shooter.setInWeb();
            this.shooter.motionY = 0.1F;
            this.shooter.velocityChanged = true;
        }
        if (Math.abs(this.shooter.posX - this.posX) > 1.0 || Math.abs(this.shooter.posZ - this.posZ) > 1.0
                || this.shooter.posY > this.posY + 1.0 || !wall
                || (!this.shooter.isSneaking() && this.needSneak) || (this.shooter.isSneaking() && !this.needSneak)) {
            this.setDead();
        }
    }

    @Override protected void entityInit() {}
    @Override protected void readEntityFromNBT(NBTTagCompound tag) {}
    @Override protected void writeEntityToNBT(NBTTagCompound tag) {}
    @Override public boolean writeToNBTOptional(NBTTagCompound tag) { return false; }
}
