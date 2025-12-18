package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.components.util.CooldownUtil;

@Mixin(value = PlayerEntity.class)
public class PlayerCooldownMixin {

    @Unique
    PlayerEntity thisAsPlayer = (PlayerEntity) (Object)this;
    @Inject(method = "tick", at=@At("HEAD"))
    private void tickCooldowns(CallbackInfo ci){
        if(!thisAsPlayer.getWorld().isClient()) {
            CooldownUtil.decrementBackstabCooldown(thisAsPlayer);
            CooldownUtil.decrementPortalCooldown(thisAsPlayer);
        }
    }
}
