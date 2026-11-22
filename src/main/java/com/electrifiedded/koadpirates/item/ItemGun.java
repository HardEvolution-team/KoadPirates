package com.electrifiedded.koadpirates.item;

import com.electrifiedded.archaiclib.legacy.entity.LegacyInventory;
import com.electrifiedded.koadpirates.PiratesContent;
import com.electrifiedded.koadpirates.entity.EntityShot;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Flintlock (type 0), musket (1) and musket with bayonet (2). A loaded gun fires on right-click and becomes the empty
 * gun; an empty gun is reloaded by holding right-click (consumes one shot).
 */
public class ItemGun extends Item {
    private final boolean loaded;
    private final int type;

    public ItemGun(boolean loaded, int type) {
        this.loaded = loaded;
        this.type = type;
        this.setMaxStackSize(1);
        this.setMaxDamage(type == 0 ? 128 : type == 1 ? 125 : 150);
        this.setFull3D();
    }

    private Item other() {
        if (this.type == 0) return this.loaded ? PiratesContent.EmptyFlintlock : PiratesContent.Flintlock;
        if (this.type == 1) return this.loaded ? PiratesContent.EmptyMusket : PiratesContent.Musket;
        return this.loaded ? PiratesContent.EmptyMusketWithb : PiratesContent.MusketWithb;
    }

    private static ItemStack swap(ItemStack from, Item to) {
        ItemStack s = new ItemStack(to, from.getCount(), from.getItemDamage());
        if (from.hasTagCompound()) s.setTagCompound(from.getTagCompound().copy());
        return s;
    }

    @Override public EnumAction getItemUseAction(ItemStack s) { return EnumAction.BOW; }
    @Override public int getMaxItemUseDuration(ItemStack s) { return 72000; }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (this.loaded) {
            if (!world.isRemote) {
                world.spawnEntity(new EntityShot(world, player, this.type == 0 ? 2.5F : 3.0F));
                world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.PLAYERS, 4.0F, 0.4F);
                world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0F, 1.3F);
                stack.damageItem(1, player);
            }
            if (stack.isEmpty()) return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            return new ActionResult<>(EnumActionResult.SUCCESS, swap(stack, other()));
        }
        if (player.capabilities.isCreativeMode || LegacyInventory.hasItem(player.inventory, PiratesContent.Shot)) {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, stack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {
        if (this.loaded || !(entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer)entity;
        if (!player.capabilities.isCreativeMode && !LegacyInventory.hasItem(player.inventory, PiratesContent.Shot)) return;
        float f = (this.getMaxItemUseDuration(stack) - timeLeft) / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f < (this.type == 0 ? 2.0F : 2.5F)) return;
        if (!player.capabilities.isCreativeMode) LegacyInventory.consumeInventoryItem(player.inventory, PiratesContent.Shot);
        player.swingArm(player.getActiveHand());
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 1.0F, 2.0F);
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.PLAYERS, 1.0F, 2.0F);
        player.setHeldItem(player.getActiveHand(), swap(stack, other()));
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
        Multimap<String, AttributeModifier> m = super.getItemAttributeModifiers(slot);
        if (slot == EntityEquipmentSlot.MAINHAND) {
            double dmg = this.type == 2 ? 4.0 : 2.0;
            m.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", dmg, 0));
        }
        return m;
    }
}
