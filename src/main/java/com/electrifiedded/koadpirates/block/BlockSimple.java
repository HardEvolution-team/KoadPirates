package com.electrifiedded.koadpirates.block;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.function.Supplier;

/** Plain cube with one texture and a fixed drop (salt block, buried treasure, sea rock). */
public class BlockSimple extends Block implements IArchaicRenderBlock {
    private final String texture;
    private final Supplier<Item> drop;
    private final int min, max;
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockSimple(Material material, SoundType sound, String texture, Supplier<Item> drop, int min, int max) {
        super(material);
        this.setSoundType(sound);
        this.texture = texture;
        this.drop = drop;
        this.min = min;
        this.max = max;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return this.drop.get();
    }

    @Override
    public int quantityDropped(Random rand) {
        return this.min + (this.max > this.min ? rand.nextInt(this.max - this.min + 1) : 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon(this.texture);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.icon;
    }
}
