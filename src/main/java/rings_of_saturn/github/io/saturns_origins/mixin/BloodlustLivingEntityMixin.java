package rings_of_saturn.github.io.saturns_origins.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.networking.packet.BloodlustTargetPayloadS2C;
import rings_of_saturn.github.io.saturns_origins.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.BLOODLUST_DURATION_TICKS;

@Mixin(value = LivingEntity.class)
public class BloodlustLivingEntityMixin {

    @Unique
    LivingEntity thisAsLivingEntity = (LivingEntity) (Object)this;

    @Inject(method = "damage", at=@At("HEAD"))
    private void applyGlow(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (source.getAttacker() != null && source.getAttacker().isPlayer() && thisAsLivingEntity.isLiving()) {
            PlayerEntity attacker = (PlayerEntity) source.getAttacker();
            if (!thisAsLivingEntity.getEntityWorld().isClient() && OriginUtil.isOwlfolk(attacker)) {
                ServerPlayerEntity serverAttacker = (ServerPlayerEntity) attacker;
                ServerPlayNetworking.send(serverAttacker,
                        new BloodlustTargetPayloadS2C(thisAsLivingEntity.getUuid(), BLOODLUST_DURATION_TICKS));
            }
        }
    }
}
