package rings_of_saturn.github.io.saturns_origins.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.client.BloodlustClientManager;
import rings_of_saturn.github.io.saturns_origins.networking.packet.BackstabUpdatePosPayloadS2C;
import rings_of_saturn.github.io.saturns_origins.networking.packet.BloodlustInstinctResultPayloadS2C;
import rings_of_saturn.github.io.saturns_origins.networking.packet.BloodlustTargetPayloadS2C;

import java.util.UUID;

import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.BLOODLUST_INSTINCT_TIMEOUT_TICKS;

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

        ClientPlayNetworking.registerGlobalReceiver(BloodlustTargetPayloadS2C.ID,
                (payload, context) -> {
                    UUID targetUuid = payload.targetUuid();
                    int remainingTicks = payload.remainingTicks();
                    context.client().execute(() -> {
                        BloodlustClientManager.addTarget(targetUuid, remainingTicks, BloodlustClientManager.MarkType.BLOODLUST);
                    });
        });

        ClientPlayNetworking.registerGlobalReceiver(BloodlustInstinctResultPayloadS2C.ID,
                (payload, context) -> {
                    var uuids = payload.mobUuids();
                    context.client().execute(() -> {
                        for (UUID uuid : uuids) {
                            BloodlustClientManager.addTarget(
                                    uuid,
                                    BLOODLUST_INSTINCT_TIMEOUT_TICKS,
                                    BloodlustClientManager.MarkType.SCAN);
                        }
                    });
        });

    }
}
