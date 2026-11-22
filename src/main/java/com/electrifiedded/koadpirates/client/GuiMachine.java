package com.electrifiedded.koadpirates.client;

import com.electrifiedded.koadpirates.gui.ContainerMachine;
import com.electrifiedded.koadpirates.tile.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiMachine extends GuiContainer {
    private static final ResourceLocation FURNACE = new ResourceLocation("textures/gui/container/furnace.png");
    private static final ResourceLocation COOLING = new ResourceLocation("koadpirates", "textures/gui/coolingrackgui.png");
    private static final ResourceLocation FISH = new ResourceLocation("koadpirates", "textures/gui/fishgui.png");
    private static final ResourceLocation WHEEL = new ResourceLocation("koadpirates", "textures/gui/wheelgui.png");
    private final IInventory tile;

    public GuiMachine(InventoryPlayer inv, IInventory tile) {
        super(new ContainerMachine(inv, tile));
        this.tile = tile;
    }

    @Override
    public void drawScreen(int mx, int my, float pt) {
        this.drawDefaultBackground();
        super.drawScreen(mx, my, pt);
        this.renderHoveredToolTip(mx, my);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        String name = I18n.format(this.tile.getName());
        this.fontRenderer.drawString(name, this.tile instanceof TileFishTrap ? 8 : 60, 6, 4210752);
        if (!(this.tile instanceof TileRopeMaker)) this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float pt, int mx, int my) {
        GlStateManager.color(1, 1, 1, 1);
        ResourceLocation tex = this.tile instanceof TileSmelter ? FURNACE : this.tile instanceof TileCoolingRack ? COOLING : this.tile instanceof TileFishTrap ? FISH : WHEEL;
        this.mc.getTextureManager().bindTexture(tex);
        int k = this.guiLeft, l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.tile instanceof TileFurnaceLike) {
            TileFurnaceLike f = (TileFurnaceLike)this.tile;
            if (f.isBurning()) {
                int i1 = f.getBurnTimeRemainingScaled(12);
                this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
            }
            int i1 = f.getCookProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        } else if (this.tile instanceof TileRopeMaker) {
            TileRopeMaker r = (TileRopeMaker)this.tile;
            int i1 = r.wood / 2;
            this.drawTexturedModalRect(k + 26, l + 58 - i1, 218, 51 - i1, 16, i1);
            i1 = r.iron / 4;
            this.drawTexturedModalRect(k + 8, l + 58 - i1, 201, 51 - i1, 16, i1);
            i1 = r.getCookProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
    }
}
