package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;

import java.util.List;

public class PlayerUtil {
    public static boolean isPlayerInRange(List<PlayerEntity> list, double x, double y, double z, double range) {
        for(PlayerEntity playerEntity : list) {
            if (EntityPredicates.EXCEPT_SPECTATOR.test(playerEntity) && EntityPredicates.VALID_LIVING_ENTITY.test(playerEntity)) {
                double d = playerEntity.squaredDistanceTo(x, y, z);
                if (range < (double)0.0F || d < range * range) {
                    return true;
                }
            }
        }

        return false;
    }
}
