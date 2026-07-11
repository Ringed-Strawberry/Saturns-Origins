package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record SpawnPortalPayloadC2S() implements CustomPayload {
    public static final Identifier SPAWN_PORTAL_PAYLOAD_ID = Identifier.of(MOD_ID, "chorusfruitborn_spawn_portal");
    public static final Id<SpawnPortalPayloadC2S> ID = new Id<>(SPAWN_PORTAL_PAYLOAD_ID);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
