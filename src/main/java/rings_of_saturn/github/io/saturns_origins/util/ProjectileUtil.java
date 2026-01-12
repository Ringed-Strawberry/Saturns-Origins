package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.entity.projectile.ProjectileEntity;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class ProjectileUtil {
    public static boolean getAutoAimTP(ProjectileEntity projectile){
        return ModComponents.PROJECTILE_HAS_TP.get(projectile).getValue();
    }

    public static void setAutoAimTP(ProjectileEntity projectile, boolean newValue){
        ModComponents.PROJECTILE_HAS_TP.get(projectile).setValue(newValue);
    }

    public static boolean getIsUp(ProjectileEntity projectile){
        return ModComponents.PROJECTILE_IS_UP.get(projectile).getValue();
    }

    public static void setIsUp(ProjectileEntity projectile, boolean newValue){
        ModComponents.PROJECTILE_IS_UP.get(projectile).setValue(newValue);
    }
}
