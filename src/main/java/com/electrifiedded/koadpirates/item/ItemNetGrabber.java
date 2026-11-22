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

/** Net reclaimer: hold and release near a spread net to pick it up (also picks up cannons). */
public class ItemNetGrabber extends Item {
    public ItemNetGrabber() {
        this.setMaxStackSize(1);
        this.setMaxDamage(128);
        this.setFull3D();
    }
    @Override public EnumAction getItemUseAction(ItemStack s) { return EnumAction.BOW; }
    @Override public int getMaxItemUseDuration(ItemStack s) { return 72000; }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack s, World world, EntityLivingBase e, int timeLeft) {
        if (world.isRemote || !(e instanceof EntityPlayer)) return;
        EntityPlayer p = (EntityPlayer)e;
        float f = (this.getMaxItemUseDuration(s) - timeLeft) / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f < 0.5F) return;
        List<EntityNetOut> nets = world.getEntitiesWithinAABB(EntityNetOut.class, p.getEntityBoundingBox().grow(1.5));
        for (EntityNetOut n : nets) {
            n.setDead();
            ItemStack net = new ItemStack(PiratesContent.NetItem);
            if (!p.inventory.addItemStackToInventory(net)) p.dropItem(net, false);
            s.damageItem(1, p);
        }
    }
}
