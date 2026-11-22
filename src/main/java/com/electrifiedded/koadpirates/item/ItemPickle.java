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

/** Cucumber / pickle; the pickle can always be eaten and cures potion effects like milk. */
public class ItemPickle extends ItemFood {
    private final boolean cures;

    public ItemPickle(int heal, float sat, boolean cures) {
        super(heal, sat, false);
        this.cures = cures;
        if (cures) this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack s, World world, EntityPlayer player) {
        if (this.cures && !world.isRemote) player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
    }
}
