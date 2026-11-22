package com.electrifiedded.koadpirates.proxy;

import com.electrifiedded.archaiclib.legacy.client.LegacyModelEntityRenderer;
import com.electrifiedded.archaiclib.legacy.client.LegacySpriteRenderer;
import com.electrifiedded.koadpirates.client.*;
import com.electrifiedded.koadpirates.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    private static ResourceLocation tex(String name) {
        return new ResourceLocation("koadpirates", "textures/entity/" + name + ".png");
    }

    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPirateCutlass.class, m -> new RenderPirate(m, tex("pirate1")));
        RenderingRegistry.registerEntityRenderingHandler(EntityPirateGun.class, m -> new RenderPirate(m, tex("pirate1")));
        RenderingRegistry.registerEntityRenderingHandler(EntityPirateGrenade.class, m -> new RenderPirate(m, tex("pirate3")));
        RenderingRegistry.registerEntityRenderingHandler(EntityGhostPirate.class, RenderGhostPirate::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRat.class, RenderRat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShot.class, m -> new LegacySpriteRenderer<>(m, tex("shotinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenado.class, m -> new LegacySpriteRenderer<>(m, tex("grenadoinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGhostShot.class, m -> new LegacySpriteRenderer<>(m, tex("ghostinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNet.class, m -> new LegacySpriteRenderer<>(m, tex("netinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTether.class, m -> new LegacySpriteRenderer<>(m, tex("shotinair2"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCannonBall.Light.class, m -> new LegacySpriteRenderer<>(m, tex("cannonballinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCannonBall.Medium.class, m -> new LegacySpriteRenderer<>(m, tex("cannonballinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCannonBall.Heavy.class, m -> new LegacySpriteRenderer<>(m, tex("cannonballinair"), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCannon.class, m -> new LegacyModelEntityRenderer<>(m, new ModelCannon(), tex("entitycannon")));
        RenderingRegistry.registerEntityRenderingHandler(EntityNetOut.class, m -> new LegacyModelEntityRenderer<>(m, new ModelNetOut(), tex("netout")));
        RenderingRegistry.registerEntityRenderingHandler(EntityKegPrimed.class, RenderKegPrimed::new);
    }
}
