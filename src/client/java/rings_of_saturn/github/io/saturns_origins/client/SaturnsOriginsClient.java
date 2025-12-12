package rings_of_saturn.github.io.saturns_origins.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;

import static rings_of_saturn.github.io.saturns_origins.event.KeyInputHandler.registerKeyInputs;

public class SaturnsOriginsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerKeyInputs();
        BlockRenderLayerMap.INSTANCE.putBlock(BlockGen.MAGICPLATFORMBLOCK, RenderLayer.getCutout());
    }
}
