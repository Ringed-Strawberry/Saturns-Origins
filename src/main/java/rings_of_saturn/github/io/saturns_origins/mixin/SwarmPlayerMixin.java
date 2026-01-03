package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
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
            int charge = ResourceUtil.getSwarmCharge(thisAsPlayer);
            Vec3d[] circlePos = MathUtil.getPointsInCircle(thisAsPlayer.getPos(), charge, 1);
        if (!thisAsPlayer.getWorld().isClient()){
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
            }else if (playerSwarm.size() > charge) {
                while (playerSwarm.size() > charge) {
                    FeatherProjectileEntity toRemove = playerSwarm.remove(0);
                    toRemove.kill();
                }
            }

            for (int i = 0; i < playerSwarm.size(); i++) {
                if (i >= circlePos.length){
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
                if (i >= circlePos.length){
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


    }




}

    //Idk what that is, redoing it
//        if (!thisAsPlayer.getWorld().isClient()) {
//            ServerWorld world = thisAsPlayer.getWorld().getServer().getWorld(thisAsPlayer.getWorld().getRegistryKey());
//            int charge = ResourceUtil.getSwarmCharge(thisAsPlayer);
//            List<FeatherProjectileEntity> feathers = world.getEntitiesByClass(FeatherProjectileEntity.class,
//                    Box.of(thisAsPlayer.getPos(), 16, 16, 16),
//                    EntityPredicates.EXCEPT_SPECTATOR);
//
//            Vec3d[] circlePos = MathUtil.getPointsInCircle(thisAsPlayer.getPos(), charge, 1);
//            if (!feathers.isEmpty()) {
//                for (int i = 0; i < charge; i++) {
//                    if (
//                            feathers.get(i) != null &&
//                                    feathers.get(i).getStack() == featherUpStack &&
//                                    feathers.get(i).getOwner() == thisAsPlayer) {
//                        feathers.get(i).setVelocity(thisAsPlayer.getVelocity());
//                    }
//                    if (feathers.get(i) == null) {
//                        FeatherProjectileEntity entity = new FeatherProjectileEntity(world, thisAsPlayer);
//                        entity.setPos(circlePos[i].getX(), circlePos[i].getY(), circlePos[i].getZ());
//                        entity.setNoGravity(true);
//                        entity.setInvulnerable(true);
//                        world.spawnEntity(entity);
//                    }
//                }
//            }
//        }
//    }
