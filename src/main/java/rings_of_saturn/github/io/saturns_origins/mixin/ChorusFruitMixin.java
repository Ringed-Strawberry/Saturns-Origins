package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.TeleportRandomlyConsumeEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TeleportRandomlyConsumeEffect.class)
public class ChorusFruitMixin {

    @Unique
    TeleportRandomlyConsumeEffect thisAsEffect = (TeleportRandomlyConsumeEffect)(Object)this;

    @Inject(method = "onConsume", at=@At("HEAD"),  cancellable = true)
    void eat(World world, ItemStack stack, LivingEntity user, CallbackInfoReturnable<Boolean> cir) {
        boolean bl = false;
        if (user.isSneaking()) {
            for (int i = 0; i < 16; ++i) {
                double d = user.getX() + (user.getRandom().nextDouble() - (double) 0.5F) * (double) thisAsEffect.diameter();
                double e = MathHelper.clamp(user.getY() + (user.getRandom().nextDouble() - (double) 0.5F) * (double) thisAsEffect.diameter(), (double) world.getBottomY(), (double) (world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1));
                double f = user.getZ() + (user.getRandom().nextDouble() - (double) 0.5F) * (double) thisAsEffect.diameter();
                if (user.hasVehicle()) {
                    user.stopRiding();
                }

                Vec3d vec3d = user.getEntityPos();
                if (user.teleport(d, e, f, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                    SoundCategory soundCategory;
                    SoundEvent soundEvent;
                    if (user instanceof FoxEntity) {
                        soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                        soundCategory = SoundCategory.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        soundCategory = SoundCategory.PLAYERS;
                    }

                    world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
                    user.onLanding();
                    bl = true;
                    break;
                }
            }

            if (bl && user instanceof PlayerEntity playerEntity) {
                playerEntity.clearCurrentExplosion();
            }
        }
        cir.setReturnValue(bl);
    }
}
