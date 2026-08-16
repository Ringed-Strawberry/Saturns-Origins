package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.*;

public class CooldownUtil {



    public static void decrementPortalCooldown(PlayerEntity player){
        ModComponents.PORTAL_COOLDOWN.get(player).decrement();
    }

    public static void resetPortalCooldown(PlayerEntity player){
        ModComponents.PORTAL_COOLDOWN.get(player).setValue(PORTAL_COOLDOWN_TICKS);
    }

    public static boolean isPortalCooldownOver(PlayerEntity player){
        return ModComponents.PORTAL_COOLDOWN.get(player).isOver();
    }

    public static void decrementInvisibilityCooldown(PlayerEntity player){
        ModComponents.INVIS_COOLDOWN.get(player).decrement();
    }

    public static void resetInvisibilityCooldown(PlayerEntity player){
        ModComponents.INVIS_COOLDOWN.get(player).setValue(INVIS_COOLDOWN_TICKS);
    }

    public static boolean isInvisibilityCooldownOver(PlayerEntity player){
        return ModComponents.INVIS_COOLDOWN.get(player).isOver();
    }

    public static boolean isInvulnerableFramesOver(LivingEntity entity){
        return ModComponents.INVULNERABLE_FRAMES.get(entity).isOver();
    }

    public static int getInvulnerableFrames(LivingEntity entity){
        return ModComponents.INVULNERABLE_FRAMES.get(entity).getValue();
    }

    public static void decrementInvulnerableFrames(LivingEntity entity){
        ModComponents.INVULNERABLE_FRAMES.get(entity).decrement();
    }

    public static void resetInvulnerableFrames(LivingEntity entity){
        ModComponents.INVULNERABLE_FRAMES.get(entity).setValue(INVULNERABLE_FRAMES_TICKS);
    }
}
