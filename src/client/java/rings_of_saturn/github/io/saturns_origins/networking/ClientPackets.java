package rings_of_saturn.github.io.saturns_origins.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.networking.packet.PacketConstants;
import rings_of_saturn.github.io.saturns_origins.util.PosUtil;

public class ClientPackets {
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(PacketConstants.BACKSTAB_UPDATE_POS_ID,
                (minecraftClient, clientPlayNetworkHandler,
                 packetByteBuf, packetSender) -> {
                    Vec3d pos = PosUtil.getVec3dFromString(packetByteBuf.readString());
                    float yaw = packetByteBuf.readFloat();
                    minecraftClient.execute(() -> {
                        if(minecraftClient.player != null) {
                            minecraftClient.player.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), yaw, minecraftClient.player.getPitch());
                        }
                    });
        });
    }
}
