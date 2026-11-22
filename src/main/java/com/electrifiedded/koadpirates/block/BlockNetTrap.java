package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/** Falling net block (unused by the original's items, kept for completeness): pulls living things down and holds them. */
public class BlockNetTrap extends BlockFalling implements IArchaicRenderBlock {
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockNetTrap() {
        super(Material.GRASS);
        this.setSoundType(SoundType.CLOTH);
    }

    @Override public Item getItemDropped(IBlockState s, Random r, int f) { return net.minecraft.init.Items.AIR; }
    @Override public int quantityDropped(Random r) { return 0; }
    @Override public AxisAlignedBB getCollisionBoundingBox(IBlockState s, IBlockAccess w, BlockPos p) { return NULL_AABB; }
    @Override public boolean isOpaqueCube(IBlockState s) { return false; }
    @Override public boolean isFullCube(IBlockState s) { return false; }
    @Override public BlockFaceShape getBlockFaceShape(IBlockAccess w, IBlockState s, BlockPos p, EnumFacing f) { return BlockFaceShape.UNDEFINED; }
    @Override public int getArchaicRenderType(int key) { return RENDER_CROSS; }
    @Override @SideOnly(Side.CLIENT) public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT; }
    @Override public int tickRate(World w) { return 2; }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        // the original references "netblock", which its jar does not contain; the spread-net texture is the closest
        this.icon = reg.registerIcon("koadpirates:entity/NetOut");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return this.icon;
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity e) {
        if (e instanceof EntityLivingBase) {
            e.motionX /= 1000.0;
            e.motionZ /= 1000.0;
            e.motionY = -2.0;
            e.setInWeb();
        }
    }
}
