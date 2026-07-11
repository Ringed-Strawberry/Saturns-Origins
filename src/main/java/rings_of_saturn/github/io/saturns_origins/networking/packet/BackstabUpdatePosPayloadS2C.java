package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record BackstabUpdatePosPayloadS2C(Vec3d pos, float yaw) implements CustomPayload {
    public static final Identifier BACKSTAB_PAYLOAD_ID = Identifier.of(MOD_ID, "chorusfruitborn_backstab");
    public static final PacketCodec<PacketByteBuf, BackstabUpdatePosPayloadS2C> CODEC = PacketCodec.tuple(
            Vec3d.PACKET_CODEC, BackstabUpdatePosPayloadS2C::pos,
            PacketCodecs.FLOAT, BackstabUpdatePosPayloadS2C::yaw,
            BackstabUpdatePosPayloadS2C::new);
    public static final Id<BackstabUpdatePosPayloadS2C> ID = new Id<>(BACKSTAB_PAYLOAD_ID);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
