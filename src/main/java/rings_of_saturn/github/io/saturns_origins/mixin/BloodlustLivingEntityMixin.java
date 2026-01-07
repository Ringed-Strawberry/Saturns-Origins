package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(value = LivingEntity.class)
public class BloodlustLivingEntityMixin {

    @Unique
    LivingEntity thisAsLivingEntity = (LivingEntity) (Object)this;
    @Inject(method = "damage", at=@At("HEAD"))
    private void applyGlow(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (source.getAttacker() != null && source.getAttacker().isPlayer() && thisAsLivingEntity.isLiving()) {
            LivingEntity thisAsLiving = thisAsLivingEntity;
            PlayerEntity attacker = (PlayerEntity) source.getAttacker();
            if (!thisAsLiving.getWorld().isClient() && OriginUtil.isOwlfolk(attacker)) {
                CooldownUtil.resetBloodlustCooldown(thisAsLiving);
            }
        }
    }
    @Inject(method = "tick", at=@At("HEAD"))
    private void applyBloodlustEffectsOnTick(CallbackInfo ci){
        if(thisAsLivingEntity.isLiving()) {
            LivingEntity thisAsLiving = thisAsLivingEntity;
            if(thisAsLiving.getStatusEffect(StatusEffects.GLOWING) == null) {
                if (!CooldownUtil.isBloodlustCooldownOver(thisAsLiving)) {
                    thisAsLiving.setGlowing(true);
                    CooldownUtil.decrementBloodlustCooldown(thisAsLiving);
                } else {
                    thisAsLiving.setGlowing(false);
                }
            }
        }
    }
}
