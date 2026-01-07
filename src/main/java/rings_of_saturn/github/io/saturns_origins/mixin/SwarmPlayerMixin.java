package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;
import rings_of_saturn.github.io.saturns_origins.util.MathUtil;
import rings_of_saturn.github.io.saturns_origins.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sin;

@Mixin(value = PlayerEntity.class)
public class SwarmPlayerMixin {

    @Unique
    PlayerEntity thisAsPlayer = (PlayerEntity) (Object)this;
    @Unique
    ItemStack featherUpStack = new ItemStack(ModEntities.FEATHER_UP_PROJECTILE_ITEM);

    @Unique
    List<FeatherProjectileEntity> playerSwarm = new ArrayList<>();


    @Inject(method = "tick", at=@At("HEAD"))
    private void circleProjectiles(CallbackInfo ci) {
        if (thisAsPlayer != null && ResourceUtil.isSwarmActive(thisAsPlayer)) {
            int charge = ResourceUtil.getSwarmCharge(thisAsPlayer);
            double speed = 1.5;
            Vec3d[] circlePos = MathUtil.getOffsetPointsInCircle(thisAsPlayer.getPos().add(0, 1 + (sin(thisAsPlayer.age / 4d) / 6), 0), charge, 1, thisAsPlayer.age * speed);
            if (!thisAsPlayer.getWorld().isClient()) {
                ServerWorld world = thisAsPlayer.getWorld().getServer().getWorld(thisAsPlayer.getWorld().getRegistryKey());

                playerSwarm.removeIf(Entity::isRemoved);

                if (playerSwarm.size() < charge) {
                    int featherNeeded = charge - playerSwarm.size();
                    for (int i = 0; i < featherNeeded; i++) {
                        FeatherProjectileEntity entity = new FeatherProjectileEntity(world, thisAsPlayer);
                        entity.setNoGravity(true);
                        entity.setInvulnerable(true);
                        entity.setPos(circlePos[i].getX(), circlePos[i].getY(), circlePos[i].getZ());
                        entity.setItem(featherUpStack);

                        world.spawnEntity(entity);
                        playerSwarm.add(entity);
                    }
                } else if (playerSwarm.size() > charge) {
                    while (playerSwarm.size() > charge) {
                        FeatherProjectileEntity toRemove = playerSwarm.remove(0);
                        toRemove.kill();
                    }
                }

                for (int i = 0; i < playerSwarm.size(); i++) {
                    if (i >= circlePos.length) {
                        return;
                    }

                    FeatherProjectileEntity feather = playerSwarm.get(i);

                    if (feather.getWorld() == thisAsPlayer.getWorld()) {
                        Vec3d target = circlePos[i];

                        Vec3d vec = target.subtract(feather.getPos()).add(thisAsPlayer.getVelocity());
                        feather.setVelocity(vec);
                    }

                }

            } else {

                for (int i = 0; i < playerSwarm.size(); i++) {
                    if (i >= circlePos.length) {
                        return;
                    }

                    FeatherProjectileEntity feather = playerSwarm.get(i);

                    if (feather.getWorld() == thisAsPlayer.getWorld()) {
                        Vec3d target = circlePos[i];

                        Vec3d vec = target.subtract(feather.getPos()).add(thisAsPlayer.getVelocity());
                        feather.setVelocity(vec);
                    }

                }
            }
        } else {
            for (FeatherProjectileEntity feather : playerSwarm) {
                feather.kill();
            }
        }
    }




}
