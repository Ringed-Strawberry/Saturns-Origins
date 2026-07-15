package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.List;
import java.util.UUID;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record BloodlustInstinctResultPayloadS2C(List<UUID> mobUuids) implements CustomPayload {
    public static final Identifier BLOODLUST_INSTINCT_RESULT_ID = Identifier.of(MOD_ID, "radar_scan_result");
    public static final PacketCodec<PacketByteBuf, BloodlustInstinctResultPayloadS2C> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC.collect(PacketCodecs.toList()), BloodlustInstinctResultPayloadS2C::mobUuids,
            BloodlustInstinctResultPayloadS2C::new);
    public static final Id<BloodlustInstinctResultPayloadS2C> ID = new Id<>(BLOODLUST_INSTINCT_RESULT_ID);

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

}
