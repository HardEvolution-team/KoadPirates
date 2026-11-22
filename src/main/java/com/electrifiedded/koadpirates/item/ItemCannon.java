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

/** Places a cannon entity at the feet of the player. */
public class ItemCannon extends Item {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack s = player.getHeldItem(hand);
        if (!world.isRemote) {
            EntityCannon c = new EntityCannon(world);
            c.setLocationAndAngles(MathHelper.floor(player.posX) + 0.5, MathHelper.floor(player.posY), MathHelper.floor(player.posZ) + 0.5, player.rotationYaw - 180.0F, 0.0F);
            world.spawnEntity(c);
            if (!player.capabilities.isCreativeMode) s.shrink(1);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, s);
    }
}
