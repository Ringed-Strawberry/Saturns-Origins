package rings_of_saturn.github.io.saturns_origins.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;
import rings_of_saturn.github.io.saturns_origins.particle.ModParticles;
import rings_of_saturn.github.io.saturns_origins.particle.OneTickParticle;
import rings_of_saturn.github.io.saturns_origins.rendering.FeatherProjectileEntityRenderer;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;
import static rings_of_saturn.github.io.saturns_origins.event.KeyInputHandler.registerKeyInputs;
import static rings_of_saturn.github.io.saturns_origins.networking.ClientPackets.registerS2CPackets;

public class SaturnsOriginsClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_FEATHER_PROJECTILE_LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "feather_projectile"), "main");;

    @Override
    public void onInitializeClient() {
        registerKeyInputs();
        registerS2CPackets();
        EntityRendererRegistry.register(ModEntities.FEATHER_PROJECTILE, FeatherProjectileEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(BlockGen.MAGICPLATFORMBLOCK, RenderLayer.getCutout());
        ParticleFactoryRegistry.getInstance().register(ModParticles.ONE_TICK_PARTICLE, OneTickParticle.Factory::new);
    }
}
