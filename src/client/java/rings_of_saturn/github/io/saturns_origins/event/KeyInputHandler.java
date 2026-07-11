package rings_of_saturn.github.io.saturns_origins.event;

import io.github.apace100.origins.OriginsClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.networking.packet.*;
import rings_of_saturn.github.io.saturns_origins.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

public class KeyInputHandler {
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client.player != null) {
                if (OriginUtil.isChorusfruitborn(client.player)) {
                    //Backstab
                    PlayerEntity player = client.player;
                    if (OriginsClient.usePrimaryActivePowerKeybind.isPressed()) {
                        HitResult hit = client.crosshairTarget;
                        if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
                            EntityHitResult entityHit = (EntityHitResult) hit;
                            Entity entity = entityHit.getEntity();
                            Vec3d vec = Vec3d.fromPolar(0,entity.getYaw()).normalize();
                            Vec3d pos = entity.getEntityPos().subtract(vec.multiply(1.0));
                            ClientPlayNetworking.send(new BackstabPayloadC2S(pos, entity.getYaw()));
                        } else if (CooldownUtil.isBackstabCooldownOver(player)) {
                            player.sendMessage(Text.of("This ability Requires you to Look at an entity"), true);
                        }
                    }
                    //Portal
                    if (OriginsClient.useSecondaryActivePowerKeybind.isPressed()) {
                        if(player.isSneaking()){
                            ClientPlayNetworking.send(new SetPortalPayloadC2S());
                        } else {
                            ClientPlayNetworking.send(new SpawnPortalPayloadC2S());
                        }
                    }
                }
                if (client.crosshairTarget != null && OriginUtil.isOwlfolk(client.player) && client.crosshairTarget.getType() == HitResult.Type.MISS) {
                    if (client.options.attackKey.isPressed()) {
                        ClientPlayNetworking.send(new SwarmAttackPayloadC2S());
                    } else {
                        ClientPlayNetworking.send(new SwarmResetPayloadC2S());
                    }
                }
            }
		});
	}
}
