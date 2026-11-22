package com.electrifiedded.koadpirates.client;

import com.electrifiedded.koadpirates.entity.EntityGhostPirate;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/** Translucent pirate ghost (1.7 rendered a see-through biped render pass). */
public class RenderGhostPirate extends RenderLiving<EntityGhostPirate> {
    private static final ResourceLocation TEX = new ResourceLocation("koadpirates", "textures/entity/ghost_pirate.png");

    public RenderGhostPirate(RenderManager m) {
        super(m, new ModelPirate(), 0.3F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGhostPirate e) {
        return TEX;
    }

    @Override
    protected void renderModel(EntityGhostPirate e, float a, float b, float c, float d, float f, float scale) {
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.6F);
        super.renderModel(e, a, b, c, d, f, scale);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
    }
}
