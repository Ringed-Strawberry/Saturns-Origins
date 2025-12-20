package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
        if(thisAsEntity.isPlayer() && !thisAsEntity.getWorld().isClient() && OriginUtil.isOwlfolk(thisAsEntity) && thisAsEntity.getActiveStatusEffects().get(StatusEffects.INVISIBILITY) == null) {
            PlayerEntity player = (PlayerEntity) thisAsEntity;
            List<PlayerEntity> playerList = new ArrayList<>(List.of());
            playerList.addAll(player.getWorld().getPlayers());
            playerList.remove(player);
            boolean isPlayerInRange = PlayerUtil.isPlayerInRange(playerList, player.getX(), player.getY(), player.getZ(), 5);
            if (player.isSneaking()
                    && !isPlayerInRange) {
                player.sendMessage(Text.of("pls help"), true);
                if (CooldownUtil.isInvisibilityCooldownOver(player)) {
                    player.setInvisible(true);
                    player.sendMessage(Text.of("boop"), true);
                } else {
                    player.sendMessage(Text.of(String.valueOf(ModComponents.INVIS_COOLDOWN.get(player).getValue())), true);
                    CooldownUtil.decrementInvisibilityCooldown(player);
                }
            } else {
                player.setInvisible(false);
                CooldownUtil.resetInvisibilityCooldown(player);
            }
        }
    }
}
