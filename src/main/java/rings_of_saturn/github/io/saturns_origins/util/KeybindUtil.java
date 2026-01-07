package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.entity.player.PlayerEntity;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class KeybindUtil {

    public static boolean canAttack(PlayerEntity player) {
        return ModComponents.SWARM_ATTACK.get(player).getValue();
    }

    public static void setAttack(PlayerEntity player, boolean newValue) {
        ModComponents.SWARM_ATTACK.get(player).setValue(newValue);
    }
}
