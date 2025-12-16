package rings_of_saturn.github.io.saturns_origins.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.particle.ModParticles;
import rings_of_saturn.github.io.saturns_origins.particle.OneTickParticle;

import static rings_of_saturn.github.io.saturns_origins.event.KeyInputHandler.registerKeyInputs;
import static rings_of_saturn.github.io.saturns_origins.networking.ClientPackets.registerS2CPackets;

public class SaturnsOriginsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerKeyInputs();
        registerS2CPackets();
        BlockRenderLayerMap.INSTANCE.putBlock(BlockGen.MAGICPLATFORMBLOCK, RenderLayer.getCutout());
        ParticleFactoryRegistry.getInstance().register(ModParticles.ONE_TICK_PARTICLE, OneTickParticle.Factory::new);
    }
}
