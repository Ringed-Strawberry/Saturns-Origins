package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record BackstabPayloadC2S(Vec3d pos, float yaw) implements CustomPayload {
    public static final Identifier BACKSTAB_PAYLOAD_ID = Identifier.of(MOD_ID, "chorusfruitborn_backstab");
    public static final PacketCodec<RegistryByteBuf, BackstabPayloadC2S> CODEC = PacketCodec.tuple(
            Vec3d.PACKET_CODEC, BackstabPayloadC2S::pos,
            PacketCodecs.FLOAT, BackstabPayloadC2S::yaw,
            BackstabPayloadC2S::new);
    public static final CustomPayload.Id<BackstabPayloadC2S> ID = new CustomPayload.Id<>(BACKSTAB_PAYLOAD_ID);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
