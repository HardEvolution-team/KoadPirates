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

/** Coral item (red, purple, yellow, blue): plants coral under water. */
public class ItemKelp extends Item {
    public static final String[] TEXTURES = {"Kelp_red", "Kelp_purple", "Kelp_yellow", "Kelp_blue"};

    public ItemKelp() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getTranslationKey(ItemStack s) {
        return super.getTranslationKey() + "." + MathHelper.clamp(s.getMetadata(), 0, 3);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) for (int i = 0; i < 4; i++) items.add(new ItemStack(this, 1, i));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        ItemStack s = player.getHeldItem(hand);
        pos = pos.offset(facing);
        if (world.getBlockState(pos).getMaterial() != Material.WATER || !player.canPlayerEdit(pos, facing, s)) return EnumActionResult.FAIL;
        if (PiratesContent.kelp.canPlaceBlockAt(world, pos)) {
            if (!world.isRemote) {
                world.setBlockState(pos, PiratesContent.kelp.getStateFromMeta(8 + 2 * MathHelper.clamp(s.getMetadata(), 0, 3)), 2);
                if (!player.capabilities.isCreativeMode) s.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
