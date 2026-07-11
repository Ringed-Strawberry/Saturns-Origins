package rings_of_saturn.github.io.saturns_origins.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.networking.packet.BackstabUpdatePosPayloadS2C;

public class ClientPackets {
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(BackstabUpdatePosPayloadS2C.ID,
                (payload, context) -> {
                    Vec3d pos = payload.pos();
                    float yaw = payload.yaw();
                    context.client().execute(() -> {
                        if(context.player() != null) {
                            context.player().refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), yaw, context.client().player.getPitch());
                        }
                    });
        });
    }
}
