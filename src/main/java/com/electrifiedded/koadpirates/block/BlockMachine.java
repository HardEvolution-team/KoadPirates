package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.koadpirates.KoadPirates;
import com.electrifiedded.koadpirates.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Smelter, cooling rack, fish trap and spinning wheel ("rope maker"): top/bottom/side textured machine blocks. */
public class BlockMachine extends Block implements ITileEntityProvider, IArchaicRenderBlock {
    public static final int SMELTER = 0, COOLING_RACK = 1, FISH_TRAP = 2, ROPE_MAKER = 3;
    private static final String[] TEX = {"Smelter", "CoolingRack", "FishTrap", "Rope"};
    private final int kind;
    @SideOnly(Side.CLIENT)
    private IIcon top, bottom, side;

    public BlockMachine(int kind, Material material) {
        super(material);
        this.kind = kind;
        this.setSoundType(material == Material.WOOD ? SoundType.WOOD : SoundType.STONE);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        switch (this.kind) {
            case SMELTER: return new TileSmelter();
            case COOLING_RACK: return new TileCoolingRack();
            case FISH_TRAP: return new TileFishTrap();
            default: return new TileRopeMaker();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        String t = "koadpirates:" + TEX[this.kind];
        this.top = reg.registerIcon(t + "Top");
        this.bottom = reg.registerIcon(t + "Bottom");
        this.side = reg.registerIcon(t + "Side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return s == 1 ? this.top : s == 0 ? this.bottom : this.side;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
        if (player.isSneaking()) return false;
        if (!world.isRemote) player.openGui(KoadPirates.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof IInventory) InventoryHelper.dropInventoryItems(world, pos, (IInventory)te);
        super.breakBlock(world, pos, state);
    }
}
