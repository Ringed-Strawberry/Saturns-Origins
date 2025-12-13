package rings_of_saturn.github.io.saturns_origins.components.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class PortalPositionUtil {
    public static Vec3d getPortalPos(PlayerEntity player){
        return ModComponents.PORTAL_POSITION.get(player).getValue();
    }

    public static void setPortalPos(PlayerEntity player, Vec3d newPos){
        ModComponents.PORTAL_POSITION.get(player).setValue(newPos);
    }

    public static void setPortalPos(PlayerEntity player){
            ModComponents.PORTAL_POSITION.get(player).setValue(player.getPos());
    }
}
