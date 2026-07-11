package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record SwarmAttackPayloadC2S() implements CustomPayload {
    public static final Identifier SPAWN_PORTAL_PAYLOAD_ID = Identifier.of(MOD_ID, "swarm_attack");
    public static final Id<SwarmAttackPayloadC2S> ID = new Id<>(SPAWN_PORTAL_PAYLOAD_ID);
    public static final PacketCodec<PacketByteBuf, SwarmAttackPayloadC2S> CODEC = PacketCodec.of(
            null,null);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
