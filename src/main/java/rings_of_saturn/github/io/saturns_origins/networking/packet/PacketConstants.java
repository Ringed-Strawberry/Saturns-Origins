package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class PacketConstants {
    public static final Identifier BACKSTAB_PACKET_ID = Identifier.of(MOD_ID, "chorusfruitborn_backstab");

    public static final Identifier SET_PORTAL_PACKET_ID = Identifier.of(MOD_ID, "chorusfruitborn_set_portal");

    public static final Identifier SPAWN_PORTAL_PACKET_ID = Identifier.of(MOD_ID, "chorusfruitborn_spawn_portal");
    public static final Identifier BACKSTAB_UPDATE_POS_ID = Identifier.of(MOD_ID, "backstab_update_pos");
}
