package rings_of_saturn.github.io.saturns_origins;

import net.fabricmc.api.ModInitializer;

import static rings_of_saturn.github.io.saturns_origins.block.BlockGen.registerModBlocks;
import static rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities.registerModBlockEntities;
import static rings_of_saturn.github.io.saturns_origins.entity.ModEntities.registerModEntities;
import static rings_of_saturn.github.io.saturns_origins.networking.ModPayloads.registerPayloads;
import static rings_of_saturn.github.io.saturns_origins.particle.ModParticles.registerParticles;
import static rings_of_saturn.github.io.saturns_origins.networking.ServerPackets.registerC2SPackets;

public class SaturnsOrigins implements ModInitializer {
    public static final String MOD_ID = "saturns_origins";

    @Override
    public void onInitialize() {
        registerPayloads();
        registerModBlocks();
        registerModBlockEntities();
        registerParticles();
        registerC2SPackets();
        registerModEntities();
    }
}
