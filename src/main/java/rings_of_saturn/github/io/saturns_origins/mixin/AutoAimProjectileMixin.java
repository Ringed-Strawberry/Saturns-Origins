package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;
import rings_of_saturn.github.io.saturns_origins.util.ProjectileUtil;

@Mixin(value = ProjectileEntity.class)
public class AutoAimProjectileMixin {

    @Unique
    ProjectileEntity thisAsEntity = (ProjectileEntity) (Object)this;

    @Unique
    private LivingEntity storedTarget = null;

    @Inject(method = "tick", at=@At("HEAD"))
    private void tickInvisibility(CallbackInfo ci){
        if(thisAsEntity.getOwner() != null /*&& !ProjectileUtil.getAutoAimTP(thisAsEntity)*/ && !thisAsEntity.getWorld().isClient() && OriginUtil.isOwlfolk(thisAsEntity.getOwner())) {
            double range = thisAsEntity.getClass().equals(FeatherProjectileEntity.class) ? 3 : 1.5;

            if (storedTarget != null && (!storedTarget.isAlive() || storedTarget.isRemoved())) {
                storedTarget = null;
            }

            LivingEntity closestEntity = thisAsEntity.getWorld().getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT,
                    (LivingEntity) thisAsEntity.getOwner(), thisAsEntity.getX(), thisAsEntity.getY(), thisAsEntity.getZ(),
                    new Box(thisAsEntity.getBlockX()+range, thisAsEntity.getBlockY()+range, thisAsEntity.getBlockZ()+range,
                            thisAsEntity.getBlockX()-range, thisAsEntity.getBlockY()-range, thisAsEntity.getBlockZ()-range));
            // Commented the old code in case
            if(closestEntity != null /*&& !closestEntity.isBlocking()*/){

                storedTarget = closestEntity;

                ProjectileUtil.setAutoAimTP(thisAsEntity, true);

                /*
                ProjectileUtil.setAutoAimTP(thisAsEntity, true);
                if(thisAsEntity.getOwner().isPlayer()) {
                    PlayerEntity owner = (PlayerEntity) thisAsEntity.getOwner();
                    owner.getWorld().playSound(null, owner.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, 0);
                }
                thisAsEntity.teleport(closestEntity.getEyePos().getX(), closestEntity.getEyePos().getY()+1f, closestEntity.getEyePos().getZ());
                thisAsEntity.updatePosition(closestEntity.getEyePos().getX(), closestEntity.getEyePos().getY()+1f, closestEntity.getEyePos().getZ());
                thisAsEntity.setVelocity(0, -1.5,0);*/
            }
            if (storedTarget != null){
                Vec3d targetPos = storedTarget.getBoundingBox().getCenter();
                Vec3d arrowPos = thisAsEntity.getPos();
                Vec3d direction = targetPos.subtract(arrowPos).normalize();

                double speed = thisAsEntity.getVelocity().length();

                Vec3d oldVelocity = thisAsEntity.getVelocity().normalize();
                Vec3d newVelocity = oldVelocity.lerp(direction,0.65).normalize().multiply(speed);

                thisAsEntity.setVelocity(newVelocity);
                double horizontalLength = newVelocity.horizontalLength();
                thisAsEntity.setYaw((float)(MathHelper.atan2(newVelocity.x, newVelocity.z) * MathHelper.DEGREES_PER_RADIAN));
                thisAsEntity.setPitch((float)(MathHelper.atan2(newVelocity.y,horizontalLength) * MathHelper.DEGREES_PER_RADIAN));

            }

        }
    }
    @Inject(method = "onCollision", at=@At("HEAD"))
    private void setCollidedWithBlock(HitResult hitResult, CallbackInfo ci){
        HitResult.Type type = hitResult.getType();
        if(type == HitResult.Type.BLOCK){
            ProjectileUtil.setAutoAimTP(thisAsEntity, true);
        }
    }
}
