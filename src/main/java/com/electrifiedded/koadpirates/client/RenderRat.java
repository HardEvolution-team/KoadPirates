package com.electrifiedded.koadpirates.client;

import com.electrifiedded.koadpirates.entity.EntityRat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRat extends RenderLiving<EntityRat> {
    private static final ResourceLocation RAT = new ResourceLocation("koadpirates", "textures/entity/rat.png");
    private static final ResourceLocation SAT = new ResourceLocation("koadpirates", "textures/entity/ratsat.png");

    public RenderRat(RenderManager m) {
        super(m, new ModelRat(), 0.3F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRat e) {
        return e.isSitting() ? SAT : RAT;
    }

    @Override
    protected void preRenderCallback(EntityRat e, float pt) {
        if (e.isTamed()) GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
}
