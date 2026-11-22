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

/**
 * Ghostform armour. With Davy's Medallion in the inventory, right-click enchants it, paying with: iron plate
 * (protection), powder horn (fire prot.), rope (feather falling), grenade (blast prot.), shot (projectile prot.),
 * sea weed (respiration), coral (aqua affinity), anemone (thorns). Ghost wrappings repair it.
 */
public class ItemGhostArmor extends ItemArmor {
    public ItemGhostArmor(ItemArmor.ArmorMaterial m, EntityEquipmentSlot slot) {
        super(m, 0, slot);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        // the original jar has no armour layer textures for this set; chain mail is the closest look
        return slot == EntityEquipmentSlot.LEGS ? "minecraft:textures/models/armor/chainmail_layer_2.png" : "minecraft:textures/models/armor/chainmail_layer_1.png";
    }

    private static void pay(EntityPlayer p, ItemStack armour, Item cost, Enchantment ench) {
        if (!LegacyInventory.hasItem(p.inventory, PiratesContent.DavyCode) || !LegacyInventory.hasItem(p.inventory, cost)) return;
        armour.addEnchantment(ench, 1);
        LegacyInventory.consumeInventoryItem(p.inventory, PiratesContent.DavyCode);
        LegacyInventory.consumeInventoryItem(p.inventory, cost);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer p, EnumHand hand) {
        ItemStack s = p.getHeldItem(hand);
        if (LegacyInventory.hasItem(p.inventory, PiratesContent.DavyCode)) {
            if (!world.isRemote) {
                pay(p, s, PiratesContent.IronPlate, Enchantments.PROTECTION);
                pay(p, s, PiratesContent.IGPT, Enchantments.FIRE_PROTECTION);
                pay(p, s, PiratesContent.RopeItem, Enchantments.FEATHER_FALLING);
                pay(p, s, PiratesContent.Grenado, Enchantments.BLAST_PROTECTION);
                pay(p, s, PiratesContent.Shot, Enchantments.PROJECTILE_PROTECTION);
                pay(p, s, Item.getItemFromBlock(PiratesContent.seaWeed), Enchantments.RESPIRATION);
                pay(p, s, PiratesContent.kelpItem, Enchantments.AQUA_AFFINITY);
                pay(p, s, Item.getItemFromBlock(PiratesContent.anem), Enchantments.THORNS);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, s);
        }
        if (s.getItemDamage() > 0 && LegacyInventory.hasItem(p.inventory, PiratesContent.ghostWrap)) {
            if (!world.isRemote) {
                s.setItemDamage(0);
                LegacyInventory.consumeInventoryItem(p.inventory, PiratesContent.ghostWrap);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, s);
        }
        return super.onItemRightClick(world, p, hand);
    }
}
