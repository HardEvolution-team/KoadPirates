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

/** Powder horn: places gun powder trail where redstone could go. */
public class ItemPowderHorn extends Item {
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        ItemStack s = player.getHeldItem(hand);
        if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) pos = pos.offset(facing);
        if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) return EnumActionResult.FAIL;
        if (!player.canPlayerEdit(pos, facing, s)) return EnumActionResult.FAIL;
        if (PiratesContent.GPT.canPlaceBlockAt(world, pos)) {
            if (!world.isRemote) {
                world.setBlockState(pos, PiratesContent.GPT.getDefaultState());
                if (!player.capabilities.isCreativeMode) s.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
