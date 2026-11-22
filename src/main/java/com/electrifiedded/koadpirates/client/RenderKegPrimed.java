package com.electrifiedded.koadpirates.client;

import com.electrifiedded.koadpirates.PiratesContent;
import com.electrifiedded.koadpirates.entity.EntityKegPrimed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

/** Lit keg: renders the keg block like primed TNT. */
public class RenderKegPrimed extends Render<EntityKegPrimed> {
    public RenderKegPrimed(RenderManager m) {
        super(m);
        this.shadowSize = 0.5F;
    }

    @Override
    public void doRender(EntityKegPrimed e, double x, double y, double z, float yaw, float pt) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 0.5F, (float)z);
        this.bindEntityTexture(e);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, 0.5F);
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(PiratesContent.Keg.getDefaultState(), e.getBrightness());
        GlStateManager.popMatrix();
        super.doRender(e, x, y, z, yaw, pt);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKegPrimed e) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
