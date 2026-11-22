package com.electrifiedded.koadpirates.item;

import com.electrifiedded.archaiclib.legacy.entity.LegacyInventory;
import com.electrifiedded.koadpirates.PiratesContent;
import com.electrifiedded.koadpirates.entity.*;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import java.util.List;

/** Boarding axe: right-click next to a wall to jump up it (no fall damage); sneak with a tether in the inventory to hang there. */
public class ItemBoardingHook extends Item {
    public ItemBoardingHook() {
        this.setMaxStackSize(1);
        this.setMaxDamage(5000);
        this.setFull3D();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack s = player.getHeldItem(hand);
        if (!world.isRemote && player.isSneaking() && LegacyInventory.hasItem(player.inventory, PiratesContent.tether)) {
            EntityTether t = new EntityTether(world);
            t.setLocationAndAngles(player.posX, player.posY, player.posZ, world.rand.nextFloat() * 360.0F, 0.0F);
            t.setShooter(player);
            t.setSNeed(true);
            world.spawnEntity(t);
        }
        if (EntityTether.nextToWall(world, player)) {
            player.swingArm(hand);
            if (!world.isRemote) EntityTether.cancelFall(player);
            player.fallDistance = 0.0F;
            player.motionY = 0.2;
            s.damageItem(1, player);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, s);
    }

    @Override
    public com.google.common.collect.Multimap<String, net.minecraft.entity.ai.attributes.AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
        com.google.common.collect.Multimap<String, net.minecraft.entity.ai.attributes.AttributeModifier> m = super.getItemAttributeModifiers(slot);
        if (slot == EntityEquipmentSlot.MAINHAND) m.put(net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new net.minecraft.entity.ai.attributes.AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 3.0, 0));
        return m;
    }
}
