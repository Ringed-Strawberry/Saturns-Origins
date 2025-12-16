package rings_of_saturn.github.io.saturns_origins.components.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
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

    public static ServerWorld getPortalWorld(PlayerEntity player){
        ServerWorld TPWorld = player.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, Identifier.tryParse(ModComponents.PORTAL_WORLD.get(player).getValue())));
        return TPWorld;
    }

    public static String getPortalWorldAsString(PlayerEntity player){
        return ModComponents.PORTAL_WORLD.get(player).getValue();
    }

    public static void setPortalWorld(PlayerEntity player, String newWorld){
        ModComponents.PORTAL_WORLD.get(player).setValue(newWorld);
    }

    public static void setPortalWorld(PlayerEntity player){
        ModComponents.PORTAL_WORLD.get(player).setValue(player.getWorld().getRegistryKey().getValue().toString());
    }
}
