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

/** Throwing net: hold right-click and release to throw. */
public class ItemNet extends Item {
    public ItemNet() { this.setMaxStackSize(4); }
    @Override public EnumAction getItemUseAction(ItemStack s) { return EnumAction.BOW; }
    @Override public int getMaxItemUseDuration(ItemStack s) { return 72000; }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack s, World world, EntityLivingBase e, int timeLeft) {
        if (!(e instanceof EntityPlayer)) return;
        EntityPlayer p = (EntityPlayer)e;
        float f = (this.getMaxItemUseDuration(s) - timeLeft) / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f <= 2.0F) return;
        world.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) world.spawnEntity(new EntityNet(world, p));
        if (!p.capabilities.isCreativeMode) s.shrink(1);
    }
}
