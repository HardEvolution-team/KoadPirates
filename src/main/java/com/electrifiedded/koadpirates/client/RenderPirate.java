package com.electrifiedded.koadpirates.client;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderPirate<T extends EntityLiving> extends RenderBiped<T> {
    private final ResourceLocation tex;

    public RenderPirate(RenderManager m, ResourceLocation tex) {
        super(m, new ModelPirate(), 0.3F);
        this.tex = tex;
    }

    @Override
    protected ResourceLocation getEntityTexture(T e) {
        return this.tex;
    }
}
