package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
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
    @Inject(method = "tick", at=@At("HEAD"))
    private void tickInvisibility(CallbackInfo ci){
        if(thisAsEntity.getOwner() != null && !ProjectileUtil.getAutoAimTP(thisAsEntity) && !thisAsEntity.getWorld().isClient() && OriginUtil.isOwlfolk(thisAsEntity.getOwner())) {
            double range = thisAsEntity.getClass().equals(FeatherProjectileEntity.class) ? 3 : 1.5;
            LivingEntity closestEntity = thisAsEntity.getWorld().getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT,
                    (LivingEntity) thisAsEntity.getOwner(), thisAsEntity.getX(), thisAsEntity.getY(), thisAsEntity.getZ(),
                    new Box(thisAsEntity.getBlockX()+range, thisAsEntity.getBlockY()+range, thisAsEntity.getBlockZ()+range,
                            thisAsEntity.getBlockX()-range, thisAsEntity.getBlockY()-range, thisAsEntity.getBlockZ()-range));
            if(closestEntity != null && !closestEntity.isBlocking()){
                ProjectileUtil.setAutoAimTP(thisAsEntity, true);
                if(thisAsEntity.getOwner().isPlayer()) {
                    PlayerEntity owner = (PlayerEntity) thisAsEntity.getOwner();
                    owner.getWorld().playSound(null, owner.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, 0);
                }
                thisAsEntity.teleport(closestEntity.getEyePos().getX(), closestEntity.getEyePos().getY()+1f, closestEntity.getEyePos().getZ());
                thisAsEntity.updatePosition(closestEntity.getEyePos().getX(), closestEntity.getEyePos().getY()+1f, closestEntity.getEyePos().getZ());
                thisAsEntity.setVelocity(0, -1.5,0);
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
