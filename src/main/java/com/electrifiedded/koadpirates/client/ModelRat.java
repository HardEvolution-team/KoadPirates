package com.electrifiedded.koadpirates.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/** 1.7 ModelTest (the rat): head, body, tail. */
public class ModelRat extends ModelBase {
    private final ModelRenderer head, body, tail;

    public ModelRat() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 32, 4);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 3, 3, 3);
        this.head.setRotationPoint(2.0F, 22.0F, -2.0F);
        this.head.mirror = true;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-3.0F, -3.0F, -3.0F, 5, 5, 5);
        this.body.setRotationPoint(0.0F, 20.0F, -4.0F);
        this.body.mirror = true;
        this.tail = new ModelRenderer(this, 0, 12);
        this.tail.addBox(-5.0F, -4.0F, -6.0F, 1, 1, 10);
        this.tail.setRotationPoint(4.0F, 24.0F, 3.0F);
        this.tail.mirror = true;
    }

    @Override
    public void render(Entity e, float a, float b, float c, float d, float f, float scale) {
        this.head.render(scale);
        this.body.render(scale);
        this.tail.render(scale);
    }
}
