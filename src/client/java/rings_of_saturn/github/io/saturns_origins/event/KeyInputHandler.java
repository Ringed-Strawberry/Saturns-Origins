package rings_of_saturn.github.io.saturns_origins.event;

import io.github.apace100.origins.OriginsClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

public class KeyInputHandler {
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client.player != null) {
                if (OriginUtil.isChorusfruitborn(client.player)) {
                    //Backstab
                    if (OriginsClient.usePrimaryActivePowerKeybind.isPressed()) {
                        client.player.sendMessage(Text.of("key pressed"));
                        HitResult hit = client.crosshairTarget;
                        if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
                            client.player.sendMessage(Text.of("looking at entity"));
                            EntityHitResult entityHit = (EntityHitResult) hit;
                            Vec3d pos = entityHit.getEntity().getPos().add(
                                    -entityHit.getEntity().getHorizontalFacing().getOffsetX(),
                                    -entityHit.getEntity().getHorizontalFacing().getOffsetY(),
                                    -entityHit.getEntity().getHorizontalFacing().getOffsetZ()
                            );
                            client.player.setPos(pos.getX(), pos.getY(), pos.getZ());
                            client.player.setYaw(entityHit.getEntity().getYaw());
                        }
                    }
                }
            }
		});
	}
}
