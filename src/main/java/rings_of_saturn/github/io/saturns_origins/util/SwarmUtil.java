package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherUpProjectileEntity;

import java.util.*;

import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.SWARM_RADIUS;
import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.SWARM_SPEED;

public class SwarmUtil {
    private static final Map<UUID, List<FeatherUpProjectileEntity>> FEATHERS = new HashMap<>();

    public static void tick(ServerPlayerEntity player) {
        UUID uuid = player.getUuid();

        if (ResourceUtil.isSwarmActive(player)) {
            int charge = ResourceUtil.getSwarmCharge(player);

            List<FeatherUpProjectileEntity> feathers = FEATHERS.computeIfAbsent(uuid, k -> new ArrayList<>());
            feathers.removeIf(Entity::isRemoved);

            while (feathers.size() < charge) {
                FeatherUpProjectileEntity feather = new FeatherUpProjectileEntity(
                        ModEntities.FEATHER_UP_PROJECTILE, player.getEntityWorld());
                feather.setPosition(player.getEntityPos());
                feather.setSwarmIndex(feathers.size());
                feather.setSwarmCharge(charge);
                player.getEntityWorld().spawnEntity(feather);
                feathers.add(feather);
            }

            while (feathers.size() > charge) {
                FeatherUpProjectileEntity excess = feathers.removeLast();
                excess.discard();
            }

            Vec3d center = player.getEntityPos()
                    .add(0, 1 + Math.sin(player.age / 4.0) / 6.0, 0);
            Vec3d[] positions = MathUtil.getOffsetPointsInCircle(
                    center, charge, SWARM_RADIUS, player.age * SWARM_SPEED);

            for (int i = 0; i < feathers.size() && i < positions.length; i++) {
                feathers.get(i).setSwarmCharge(charge);
                feathers.get(i).setPosition(positions[i]);
            }

        } else {
            removeAll(player);
        }
    }


    public static void removeAll(ServerPlayerEntity player) {
        List<FeatherUpProjectileEntity> feathers = FEATHERS.remove(player.getUuid());
        if (feathers != null) {
            for (FeatherUpProjectileEntity feather : feathers) {
                if (!feather.isRemoved()){
                    feather.discard();
                }
            }
        }
    }

    public static void onDisconnect(ServerPlayerEntity player) {
        removeAll(player);
    }
}
