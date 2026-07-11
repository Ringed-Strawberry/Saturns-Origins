package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;
import rings_of_saturn.github.io.saturns_origins.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;
import rings_of_saturn.github.io.saturns_origins.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = LivingEntity.class)
public class InvisiblePlayerMixin {

    @Unique
    LivingEntity thisAsEntity = (LivingEntity) (Object)this;
    @Inject(method = "tick", at=@At("HEAD"))
    private void tickInvisibility(CallbackInfo ci){
        if(thisAsEntity.isPlayer() && !thisAsEntity.getEntityWorld().isClient() && OriginUtil.isOwlfolk(thisAsEntity) && thisAsEntity.getActiveStatusEffects().get(StatusEffects.INVISIBILITY) == null) {
            PlayerEntity player = (PlayerEntity) thisAsEntity;
            List<PlayerEntity> playerList = new ArrayList<>(List.of());
            playerList.addAll(player.getEntityWorld().getPlayers());
            playerList.remove(player);
            boolean isPlayerInRange = PlayerUtil.isPlayerInRange(playerList, player.getX(), player.getY(), player.getZ(), 5);
            if (!PlayerUtil.getIsInvis(player)){
                if (player.isSneaking()
                        && !isPlayerInRange) {
                    if (CooldownUtil.isInvisibilityCooldownOver(player)) {
                        player.setInvisible(true);
                        PlayerUtil.setIsInvis(player,true);
                    } else {
                        player.sendMessage(Text.of(String.valueOf((Math.round((double) (ModComponents.INVIS_COOLDOWN.get(player).getValue()-1) /20*10.0)/10.0))), true);
                        CooldownUtil.decrementInvisibilityCooldown(player);
                    }
                } else {
                    CooldownUtil.resetInvisibilityCooldown(player);
                }
                if(player.isSneaking()
                        &&isPlayerInRange){
                    player.sendMessage(Text.of("You cant hide when nearby players!"), true);
                }
            } else {
                if (isPlayerInRange) {
                    player.setInvisible(false);
                    PlayerUtil.setIsInvis(player,false);
                    CooldownUtil.resetInvisibilityCooldown(player);
                }
            }
        }
    }

    @Inject(method = "damage", at=@At("HEAD"))
    private void damageUnInvisibility(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(thisAsEntity.isPlayer() && !thisAsEntity.getEntityWorld().isClient() && OriginUtil.isOwlfolk(thisAsEntity) && PlayerUtil.getIsInvis((PlayerEntity) thisAsEntity)){
            PlayerEntity player = (PlayerEntity) thisAsEntity;
            player.setInvisible(false);
            PlayerUtil.setIsInvis(player,false);
            CooldownUtil.resetInvisibilityCooldown(player);
        }
    }
}
