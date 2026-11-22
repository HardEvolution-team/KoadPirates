package com.electrifiedded.koadpirates.entity;

import com.electrifiedded.koadpirates.PiratesContent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.List;

/**
 * Rat (1.7 EntityTest): spawns in the dark on grass, runs from players and eats crops. Tame with paper; tamed rats
 * take a chest (inventory, open with an iron plate) and string (then they pick items up), heal with cucumbers and
 * can be carried with the tether.
 */
public class EntityRat extends EntityTameable {
    private final InventoryBasic inventory = new InventoryBasic("Rat", false, 27);
    private boolean hasChest, hasTether;
    private boolean isHeld;
    private Entity holder;
    private int heldTime;
    private int searchTimer;

    public EntityRat(World world) {
        super(world);
        this.setSize(0.6F, 0.4F);
        this.setTamed(false);
    }

    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityPlayer.class, p -> !this.isTamed(), 16.0F, 0.8, 1.33));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(tamed ? 30.0 : 8.0);
    }

    public InventoryBasic getInventory() { return this.inventory; }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos p = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY), MathHelper.floor(this.posZ));
        if (this.world.getLightFor(EnumSkyBlock.SKY, p) > this.rand.nextInt(32)) return false;
        if (this.world.getLightFromNeighbors(p) > this.rand.nextInt(7)) return false;
        return this.world.getBlockState(p.down()).getBlock() == Blocks.GRASS
                && this.world.checkNoEntityCollision(this.getEntityBoundingBox())
                && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty()
                && !this.world.containsAnyLiquid(this.getEntityBoundingBox());
    }

    @Override
    protected boolean canDespawn() {
        return !this.isTamed() && this.ticksExisted > 2400;
    }

    @Override protected SoundEvent getAmbientSound() { return null; }
    @Override protected SoundEvent getHurtSound(DamageSource s) { return SoundEvents.ENTITY_BAT_HURT; }
    @Override protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_BAT_HURT; }
    @Override protected float getSoundVolume() { return 0.4F; }
    @Override protected float getSoundPitch() { return super.getSoundPitch() * 0.8F; }
    @Override protected ResourceLocation getLootTable() { return null; }

    @Override
    protected void playStepSound(BlockPos pos, Block block) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.8F;
    }

    private static boolean isCrop(Block b) {
        return b == Blocks.WHEAT || b == Blocks.SAPLING || b == Blocks.MELON_STEM || b == Blocks.PUMPKIN_STEM
                || b == Blocks.CARROTS || b == Blocks.POTATOES || b == PiratesContent.cucumber || b == PiratesContent.cucumberParent;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote) return;
        if (!this.isTamed()) {
            BlockPos here = new BlockPos(this);
            if (isCrop(this.world.getBlockState(here).getBlock())) {
                this.world.setBlockToAir(here);
                this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.5F, 1.5F);
            }
            if (++this.searchTimer > 100) {
                this.searchTimer = 0;
                if (this.getNavigator().noPath()) findCrop();
            }
        }
        if (this.hasTether && this.isEntityAlive()) {
            List<EntityItem> items = this.world.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(0.25), Entity::isEntityAlive);
            for (EntityItem ei : items) TileEntityHopper.putDropInInventoryAllSlots(null, this.inventory, ei);
        }
        if (this.isHeld && this.holder != null && this.heldTime < 700) {
            this.setPositionAndUpdate(this.holder.posX, this.holder.posY, this.holder.posZ);
            this.heldTime++;
            this.fallDistance = 0.0F;
        } else {
            this.isHeld = false;
            this.holder = null;
            this.heldTime = 1;
        }
    }

    private void findCrop() {
        BlockPos base = new BlockPos(this);
        for (int tries = 0; tries < 40; tries++) {
            BlockPos p = base.add(this.rand.nextInt(33) - 16, this.rand.nextInt(11) - 5, this.rand.nextInt(33) - 16);
            if (isCrop(this.world.getBlockState(p).getBlock())) {
                this.getNavigator().tryMoveToXYZ(p.getX() + 0.5, p.getY(), p.getZ() + 0.5, 1.0);
                return;
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float amount) {
        if (this.isEntityInvulnerable(src)) return false;
        Entity e = src.getTrueSource();
        if (this.aiSit != null) this.aiSit.setSitting(false);
        if (e != null && !(e instanceof EntityPlayer) && !(e instanceof EntityArrow)) amount = (amount + 1.0F) / 2.0F;
        return super.attackEntityFrom(src, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        return target.attackEntityFrom(DamageSource.causeMobDamage(this), this.isTamed() ? 4 : 2);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (this.isTamed()) {
            if (!stack.isEmpty()) {
                if (stack.getItem() == PiratesContent.cucumberFruit) {
                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.capabilities.isCreativeMode) stack.shrink(1);
                        this.heal(4.0F);
                    }
                    return true;
                }
                if (stack.getItem() == PiratesContent.IronPlate && this.hasChest) {
                    if (!this.world.isRemote) player.displayGUIChest(this.inventory);
                    return true;
                }
                if (stack.getItem() == Items.STRING && this.hasChest && !this.hasTether) {
                    this.hasTether = true;
                    if (!player.capabilities.isCreativeMode) stack.shrink(1);
                    return true;
                }
                if (stack.getItem() == net.minecraft.item.Item.getItemFromBlock(Blocks.CHEST) && !this.hasChest) {
                    this.hasChest = true;
                    if (!player.capabilities.isCreativeMode) stack.shrink(1);
                    return true;
                }
                if (stack.getItem() == Items.GOLDEN_HOE) {
                    this.aiSit.setSitting(true);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    return true;
                }
                if (stack.getItem() == PiratesContent.tether) {
                    if (!this.isHeld) {
                        this.setPositionAndUpdate(player.posX, player.posY, player.posZ);
                        this.isHeld = true;
                        this.holder = player;
                        this.heldTime = 0;
                        this.fallDistance = 0.0F;
                    } else {
                        this.heldTime = 1000;
                    }
                    return true;
                }
            }
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack)) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget(null);
            }
            return true;
        } else if (!stack.isEmpty() && stack.getItem() == Items.PAPER) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget(null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }
            return true;
        }
        return super.processInteract(player, hand);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() == Items.WHEAT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable other) {
        return null;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.world.isRemote) InventoryHelper.dropInventoryItems(this.world, this, this.inventory);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setBoolean("hasChest", this.hasChest);
        tag.setBoolean("hasTether", this.hasTether);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
            ItemStack s = this.inventory.getStackInSlot(i);
            if (s.isEmpty()) continue;
            NBTTagCompound t = new NBTTagCompound();
            t.setByte("Slot", (byte)i);
            s.writeToNBT(t);
            list.appendTag(t);
        }
        tag.setTag("Items", list);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        this.hasChest = tag.getBoolean("hasChest");
        this.hasTether = tag.getBoolean("hasTether");
        NBTTagList list = tag.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound t = list.getCompoundTagAt(i);
            int slot = t.getByte("Slot") & 255;
            if (slot < this.inventory.getSizeInventory()) this.inventory.setInventorySlotContents(slot, new ItemStack(t));
        }
        if (this.aiSit != null) this.aiSit.setSitting(this.isSitting());
    }
}
