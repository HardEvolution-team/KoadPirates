package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Placeable, rideable cannon. Load with cannon balls (right-click), ride it, hold flint and steel to fire along your
 * view. Rope turns it, the net reclaimer picks it up again.
 */
public class EntityCannon extends Entity {
    private int shotType;
    private int reloadTime = 180;
    private int timeTillPickup;

    public EntityCannon(World world) {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    @Override protected void entityInit() {}
    @Override public AxisAlignedBB getCollisionBox(Entity other) { return null; }
    @Override public AxisAlignedBB getCollisionBoundingBox() { return this.getEntityBoundingBox(); }
    @Override public boolean canBePushed() { return false; }
    @Override public boolean canBeCollidedWith() { return !this.isDead; }
    @Override protected boolean canBeRidden(Entity e) { return true; }
    @Override public double getMountedYOffset() { return 0.75; }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty()) {
            Item it = stack.getItem();
            int load = it == PiratesContent.CannonB1 ? 1 : it == PiratesContent.CannonB2 ? 2 : it == PiratesContent.CannonB3 ? 3 : 0;
            if (load > 0) {
                if (!this.world.isRemote) {
                    if (this.shotType > 0) dropCannonBall(this.shotType);
                    this.shotType = load;
                    if (!player.capabilities.isCreativeMode) stack.shrink(1);
                }
                return true;
            }
            if (it == PiratesContent.NetGrabber) {
                if (!this.world.isRemote) {
                    this.timeTillPickup += 5;
                    if (this.timeTillPickup >= 20) breakCannon();
                }
                return true;
            }
            if (it == PiratesContent.RopeItem) {
                if (!this.world.isRemote) {
                    this.rotationYaw = MathHelper.wrapDegrees(this.rotationYaw + 6.0F);
                    this.setPositionAndRotation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
                }
                return true;
            }
        }
        if (!this.getPassengers().isEmpty()) return true;
        if (!this.world.isRemote) player.startRiding(this);
        return true;
    }

    private void spawnDrop(ItemStack s) {
        if (this.world.isRemote || !this.world.getGameRules().getBoolean("doTileDrops")) return;
        float f = 0.7F;
        double d0 = this.world.rand.nextFloat() * f + (1.0F - f) * 0.5;
        double d1 = this.world.rand.nextFloat() * f + (1.0F - f) * 0.5;
        double d2 = this.world.rand.nextFloat() * f + (1.0F - f) * 0.5;
        EntityItem ei = new EntityItem(this.world, this.posX + d0 - 0.5, this.posY + d1, this.posZ + d2 - 0.5, s);
        ei.setPickupDelay(10);
        this.world.spawnEntity(ei);
    }

    private void dropCannonBall(int q) {
        Item ball = q == 1 ? PiratesContent.CannonB1 : q == 2 ? PiratesContent.CannonB2 : q == 3 ? PiratesContent.CannonB3 : null;
        if (ball != null) spawnDrop(new ItemStack(ball));
    }

    /** picked up / lost its ground: drops itself and the loaded ball */
    public void breakCannon() {
        if (this.world.isRemote || this.isDead) return;
        dropCannonBall(this.shotType);
        this.shotType = 0;
        spawnDrop(new ItemStack(PiratesContent.CannonItem));
        this.removePassengers();
        this.setDead();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.timeTillPickup > 0) this.timeTillPickup--;
        if (this.world.isRemote) return;
        BlockPos below = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY) - 1, MathHelper.floor(this.posZ));
        boolean allAir = true;
        for (int dx = -1; dx <= 1 && allAir; dx++)
            for (int dz = -1; dz <= 1 && allAir; dz++)
                if (!this.world.isAirBlock(below.add(dx, 0, dz))) allAir = false;
        if (allAir) {
            breakCannon();
            return;
        }
        if (this.shotType < 0 || this.shotType > 3) this.shotType = 0;
        if (this.shotType > 0) this.reloadTime++;
        Entity rider = this.getControllingPassenger();
        if (this.reloadTime > 20 && rider instanceof EntityPlayer && this.shotType > 0) {
            EntityPlayer p = (EntityPlayer)rider;
            if (p.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL || p.getHeldItemOffhand().getItem() == Items.FLINT_AND_STEEL) {
                this.world.spawnEntity(EntityCannonBall.create(this.shotType, this.world, (EntityLivingBase)rider));
                this.reloadTime = this.shotType == 1 ? 10 : this.shotType == 2 ? 5 : 0;
                this.shotType = 0;
                this.playSound(net.minecraft.init.SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.4F);
            }
        }
    }

    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        this.shotType = (int)tag.getFloat("ShotType");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setFloat("ShotType", this.shotType);
    }
}
