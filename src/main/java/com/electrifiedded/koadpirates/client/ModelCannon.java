package com.electrifiedded.koadpirates.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/** 1.7 ModelCannon: four wheels/legs and a barrel. */
public class ModelCannon extends ModelBase {
    private final ModelRenderer[] parts = new ModelRenderer[5];

    public ModelCannon() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        float[][] legs = {{-8, -8, 3}, {-8, -8, -8}, {3, -8, 3}, {3, -8, -8}};
        for (int i = 0; i < 4; i++) {
            this.parts[i] = new ModelRenderer(this, 0, 0);
            this.parts[i].addBox(0, 0, 0, 5, 8, 5);
            this.parts[i].setRotationPoint(legs[i][0], legs[i][1], legs[i][2]);
            this.parts[i].mirror = true;
        }
        this.parts[4] = new ModelRenderer(this, 0, 0);
        this.parts[4].addBox(0, 0, 0, 10, 10, 20);
        this.parts[4].setRotationPoint(-5, -13, -7);
        this.parts[4].mirror = true;
    }

    @Override
    public void render(Entity e, float a, float b, float c, float d, float f, float scale) {
        for (ModelRenderer r : this.parts) r.render(scale);
    }
}
