package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
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

import java.util.List;

@Mixin(value = LivingEntity.class)
public class InvisiblePlayerMixin {

    @Unique
    LivingEntity thisAsEntity = (LivingEntity) (Object)this;
    @Inject(method = "tick", at=@At("HEAD"))
    private void tickInvisibility(CallbackInfo ci){
        if(thisAsEntity.isPlayer() && !thisAsEntity.getWorld().isClient() && OriginUtil.isOwlfolk(thisAsEntity)) {
            PlayerEntity player = (PlayerEntity) thisAsEntity;
            List<PlayerEntity> playerList = new java.util.ArrayList<>(List.of());
            playerList.addAll(player.getWorld().getPlayers());
            playerList.remove(player);
            if (player.isSneaking()
                    && player.getActiveStatusEffects().get(StatusEffects.INVISIBILITY) != null
                    && player.getWorld().getClosestEntity(playerList, TargetPredicate.DEFAULT, null, player.getX(), player.getY(), player.getZ()) == null) {
                if(CooldownUtil.isInvisibilityCooldownOver(player)){
                    player.setInvisible(true);
                } else {
                    CooldownUtil.decrementInvisibilityCooldown(player);
                }
            } else {
                player.setInvisible(false);
                CooldownUtil.resetInvisibilityCooldown(player);
            }
        }
    }

    @Inject(method = "getArmorVisibility", at=@At("HEAD"), cancellable = true)
    private void makeArmorInvisible(CallbackInfoReturnable<Float> cir){
        if(thisAsEntity.isPlayer() && OriginUtil.isOwlfolk(thisAsEntity) && thisAsEntity.isInvisible()) {
            cir.setReturnValue(0f);
        }
    }
}
