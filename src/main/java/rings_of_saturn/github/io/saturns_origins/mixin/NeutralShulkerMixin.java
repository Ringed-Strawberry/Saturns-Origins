package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(targets = "net.minecraft.entity.mob.ShulkerEntity$TargetPlayerGoal")
public abstract class NeutralShulkerMixin  extends ActiveTargetGoal<PlayerEntity> {
    protected NeutralShulkerMixin(MobEntity mob, Class<PlayerEntity> targetClass, boolean checkVisibility) {
        super(mob, targetClass, checkVisibility);
    }

    @Inject(method = "canStart", at = @At("RETURN"), cancellable = true)
    private void checkChorusfruitbornOnStart(CallbackInfoReturnable<Boolean> cir){
        if (cir.getReturnValue() == null) {
            return;
        }

        target = this.targetEntity;
        if (target !=null){
            if(OriginUtil.isChorusfruitborn(target)){
                cir.setReturnValue(false);

                target = null;
            }
        }
    }
}
