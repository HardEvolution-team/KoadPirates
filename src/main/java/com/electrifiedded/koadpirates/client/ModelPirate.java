package com.electrifiedded.koadpirates.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/** Biped holding both arms forward (zombie-like), 1.7 ModelPirate. */
public class ModelPirate extends ModelBiped {
    @Override
    public void setRotationAngles(float limbSwing, float limbAmount, float age, float yaw, float pitch, float scale, Entity e) {
        super.setRotationAngles(limbSwing, limbAmount, age, yaw, pitch, scale, e);
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
        this.bipedRightArm.rotateAngleX = (float)-Math.PI * 4.0F / 11.0F;
        this.bipedLeftArm.rotateAngleX = (float)-Math.PI * 4.0F / 11.0F;
        this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(age * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(age * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(age * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(age * 0.067F) * 0.05F;
    }
}
