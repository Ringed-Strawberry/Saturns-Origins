package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record BloodlustTargetPayloadS2C(UUID targetUuid, int remainingTicks) implements CustomPayload {
    public static final Identifier BLOODLUST_TARGET_ID = Identifier.of(MOD_ID, "bloodlust_target");
    public static final PacketCodec<PacketByteBuf, BloodlustTargetPayloadS2C> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC, BloodlustTargetPayloadS2C::targetUuid,
            PacketCodecs.VAR_INT, BloodlustTargetPayloadS2C::remainingTicks,
            BloodlustTargetPayloadS2C::new);
    public static final Id<BloodlustTargetPayloadS2C> ID = new Id<>(BLOODLUST_TARGET_ID);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
