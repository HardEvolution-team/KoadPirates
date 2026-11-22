package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.koadpirates.tile.TileCursedChest;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/** Sea-weed covered chest of sunken ships: holds loot and summons ghost pirates while players are near. */
public class BlockCursedChest extends Block implements ITileEntityProvider, IArchaicRenderBlock {
    @SideOnly(Side.CLIENT)
    private IIcon top, side;

    public BlockCursedChest() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
    }

    @Override public TileEntity createNewTileEntity(World world, int meta) { return new TileCursedChest(); }
    @Override public Item getItemDropped(IBlockState s, Random r, int f) { return net.minecraft.init.Items.AIR; }
    @Override public int quantityDropped(Random r) { return 0; }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.top = reg.registerIcon("koadpirates:chesttopweed");
        this.side = reg.registerIcon("koadpirates:chestsideweed");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return s <= 1 ? this.top : this.side;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        if (player.isSneaking()) return false;
        TileEntity te = world.getTileEntity(pos);
        if (!world.isRemote && te instanceof TileCursedChest) player.displayGUIChest((TileCursedChest)te);
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileCursedChest) InventoryHelper.dropInventoryItems(world, pos, (TileCursedChest)te);
        super.breakBlock(world, pos, state);
    }
}
