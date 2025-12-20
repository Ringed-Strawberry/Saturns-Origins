package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class CooldownUtil {
    public static void decrementPortalCooldown(PlayerEntity player){
        ModComponents.PORTAL_COOLDOWN.get(player).decrement();
    }

    public static void resetPortalCooldown(PlayerEntity player){
        ModComponents.PORTAL_COOLDOWN.get(player).setValue(1600);
    }

    public static void decrementBackstabCooldown(PlayerEntity player){
        ModComponents.BACKSTAB_COOLDOWN.get(player).decrement();
    }

    public static void resetBackstabCooldown(PlayerEntity player){
        ModComponents.BACKSTAB_COOLDOWN.get(player).setValue(300);
    }

    public static boolean isPortalCooldownOver(PlayerEntity player){
        return ModComponents.PORTAL_COOLDOWN.get(player).isOver();
    }

    public static boolean isBackstabCooldownOver(PlayerEntity player){
        return ModComponents.BACKSTAB_COOLDOWN.get(player).isOver();
    }

    public static void decrementInvisibilityCooldown(PlayerEntity player){
        ModComponents.INVIS_COOLDOWN.get(player).decrement();
    }

    public static void resetInvisibilityCooldown(PlayerEntity player){
        ModComponents.INVIS_COOLDOWN.get(player).setValue(100);
    }

    public static boolean isInvisibilityCooldownOver(PlayerEntity player){
        return ModComponents.INVIS_COOLDOWN.get(player).isOver();
    }

    public static void decrementBloodlustCooldown(LivingEntity entity){
        ModComponents.BLOODLUST_COOLDOWN.get(entity).decrement();
    }

    public static void resetBloodlustCooldown(LivingEntity entity){
        ModComponents.BLOODLUST_COOLDOWN.get(entity).setValue(15*20);
    }

    public static boolean isBloodlustCooldownOver(LivingEntity entity){
        return ModComponents.BLOODLUST_COOLDOWN.get(entity).isOver();
    }
}
