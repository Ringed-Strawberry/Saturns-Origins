package rings_of_saturn.github.io.saturns_origins.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;
import rings_of_saturn.github.io.saturns_origins.event.BackstabUltrakillReferenceHUD;
import rings_of_saturn.github.io.saturns_origins.event.SlowFallingHUD;
import rings_of_saturn.github.io.saturns_origins.particle.ModParticles;
import rings_of_saturn.github.io.saturns_origins.particle.OneTickParticle;
import rings_of_saturn.github.io.saturns_origins.rendering.FeatherProjectileEntityRenderer;
import rings_of_saturn.github.io.saturns_origins.rendering.FeatherUpEntityRenderer;

import static rings_of_saturn.github.io.saturns_origins.event.KeyInputHandler.registerKeyInputs;
import static rings_of_saturn.github.io.saturns_origins.networking.ClientPackets.registerS2CPackets;

public class SaturnsOriginsClient implements ClientModInitializer {
    public static final MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {

        registerKeyInputs();
        registerS2CPackets();
        HudRenderCallback.EVENT.register(new SlowFallingHUD());
        HudRenderCallback.EVENT.register(new BackstabUltrakillReferenceHUD());
        EntityRendererFactories.register(ModEntities.FEATHER_UP_PROJECTILE, FeatherUpEntityRenderer::new);
        EntityRendererFactories.register(ModEntities.FEATHER_PROJECTILE, FeatherProjectileEntityRenderer::new);
        BlockRenderLayerMap.putBlock(BlockGen.MAGICPLATFORMBLOCK, BlockRenderLayer.CUTOUT);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ONE_TICK_PARTICLE, OneTickParticle.Factory::new);
    }
}
