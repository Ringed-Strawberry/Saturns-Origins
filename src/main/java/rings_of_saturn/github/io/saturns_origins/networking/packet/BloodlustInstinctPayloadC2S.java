package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.CustomPayload.Id;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public record BloodlustInstinctPayloadC2S() implements CustomPayload {
    public static final Identifier BLOODLUST_INSTINCT_ID = Identifier.of(MOD_ID, "bloodlust_instinct");
    public static final PacketCodec<PacketByteBuf, BloodlustInstinctPayloadC2S> CODEC = PacketCodec.unit(new BloodlustInstinctPayloadC2S());
    public static final Id<BloodlustInstinctPayloadC2S> ID = new Id<>(BLOODLUST_INSTINCT_ID);

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
