package rings_of_saturn.github.io.saturns_origins.components.util;

import net.minecraft.entity.player.PlayerEntity;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class CooldownUtil {
    public static void decrementPortalCooldown(PlayerEntity player){
        ModComponents.PORTAL_COOLDOWN.get(player).decrement();
    }

    public static void resetPortalCooldown(PlayerEntity player){
        ModComponents.PORTAL_COOLDOWN.get(player).setValue(80*20);
    }

    public static void decrementBackstabCooldown(PlayerEntity player){
        ModComponents.BACKSTAB_COOLDOWN.get(player).decrement();
    }

    public static void resetBackstabCooldown(PlayerEntity player){
        ModComponents.BACKSTAB_COOLDOWN.get(player).setValue(15*20);
    }

    public static boolean isPortalCooldownOver(PlayerEntity player){
        return ModComponents.PORTAL_COOLDOWN.get(player).isOver();
    }

    public static boolean isBackstabCooldownOver(PlayerEntity player){
        return ModComponents.BACKSTAB_COOLDOWN.get(player).isOver();
    }
}
