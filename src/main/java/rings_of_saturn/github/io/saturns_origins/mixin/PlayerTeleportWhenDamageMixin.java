package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(value = PlayerEntity.class)
public abstract class PlayerTeleportWhenDamageMixin extends LivingEntity {
    protected PlayerTeleportWhenDamageMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At("TAIL"))
    private void randomTeleport(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (OriginUtil.isChorusfruitborn(this) && this.getHealth() < 6.0 && !this.isDead()) {
            double d = this.getX();
            double e = this.getY();
            double f = this.getZ();

            for (int i = 0; i < 16; ++i) {
                double g = this.getX() + (this.getRandom().nextDouble() - (double) 0.5F) * (double) 16.0F;
                double h = MathHelper.clamp(this.getY() + (double) (this.getRandom().nextInt(16) - 8),  this.getEntityWorld().getBottomY(),  (this.getEntityWorld().getBottomY() + ((ServerWorld) this.getEntityWorld()).getLogicalHeight() - 1));
                double j = this.getZ() + (this.getRandom().nextDouble() - (double) 0.5F) * (double) 16.0F;
                if (this.hasVehicle()) {
                    this.stopRiding();
                }

                Vec3d vec3d = this.getEntityPos();
                if (this.teleport(g, h, j, true)) {
                        this.getEntityWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
                        SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        this.getEntityWorld().playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        this.playSound(soundEvent, 1.0F, 1.0F);
                        break;
                }
            }
        }
    }
}
