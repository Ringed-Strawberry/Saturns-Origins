package rings_of_saturn.github.io.saturns_origins.event;

import io.github.apace100.origins.OriginsClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.components.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.networking.packet.PacketConstants;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

public class KeyInputHandler {
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client.player != null) {
                if (OriginUtil.isChorusfruitborn(client.player)) {
                    //Backstab
                    PlayerEntity player = client.player;
                    if (OriginsClient.usePrimaryActivePowerKeybind.isPressed()) {
                        player.sendMessage(Text.of("key pressed"));
                        HitResult hit = client.crosshairTarget;
                        if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
                            if(CooldownUtil.isBackstabCooldownOver(player)) {
                                player.sendMessage(Text.of("looking at entity"));
                                EntityHitResult entityHit = (EntityHitResult) hit;
                                Entity entity = entityHit.getEntity();
                                Vec3d vec = Vec3d.fromPolar(0,entity.getYaw()).normalize();
                                Vec3d pos = entity.getPos().subtract(vec.multiply(1.0));
                                PacketByteBuf buf = PacketByteBufs.create();
                                buf.writeString(pos.getX() + "," + pos.getY() + "," + pos.getZ());
                                buf.writeFloat(entity.getYaw());
                                ClientPlayNetworking.send(PacketConstants.BACKSTAB_PACKET_ID, buf);
                            }
                        }
                    }
                    //Portal Work
                    if (OriginsClient.useSecondaryActivePowerKeybind.isPressed()) {
                        if(player.isSneaking()){
                            PacketByteBuf buf = PacketByteBufs.create();
                            ClientPlayNetworking.send(PacketConstants.SET_PORTAL_PACKET_ID, buf);
                        } else {
                            PacketByteBuf buf = PacketByteBufs.create();
                            ClientPlayNetworking.send(PacketConstants.SPAWN_PORTAL_PACKET_ID, buf);
                        }
                    }
                }
            }
		});
	}
}
