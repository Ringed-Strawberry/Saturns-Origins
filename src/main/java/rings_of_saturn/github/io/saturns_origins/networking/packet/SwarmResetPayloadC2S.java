package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record SwarmResetPayloadC2S(Short stupid) implements CustomPayload {
    public static final Identifier SPAWN_PORTAL_PAYLOAD_ID = Identifier.of(MOD_ID, "swarm_reset");
    public static final Id<SwarmResetPayloadC2S> ID = new Id<>(SPAWN_PORTAL_PAYLOAD_ID);
    public static final PacketCodec<PacketByteBuf, SwarmResetPayloadC2S> CODEC = PacketCodec.tuple(
            PacketCodecs.SHORT, SwarmResetPayloadC2S::stupid, SwarmResetPayloadC2S::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
