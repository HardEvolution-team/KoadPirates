package com.electrifiedded.koadpirates.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/** 1.7 ModelNetOut: two nested cubes (3 and 2 blocks wide). */
public class ModelNetOut extends ModelBase {
    private final ModelRenderer outer, inner;

    public ModelNetOut() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.outer = new ModelRenderer(this, 0, 0);
        this.outer.addBox(0, 0, 0, 48, 48, 48);
        this.outer.setRotationPoint(-24, -24, -24);
        this.inner = new ModelRenderer(this, 0, 0);
        this.inner.addBox(0, 0, 0, 32, 32, 32);
        this.inner.setRotationPoint(-16, -16, -16);
    }

    @Override
    public void render(Entity e, float a, float b, float c, float d, float f, float scale) {
        this.outer.render(scale);
        this.inner.render(scale);
    }
}
