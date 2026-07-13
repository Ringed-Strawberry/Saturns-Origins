package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import rings_of_saturn.github.io.saturns_origins.util.SwarmUtil;



@Mixin(value = PlayerEntity.class)
public class SwarmPlayerMixin {

    @Unique
    private final PlayerEntity thisPlayer = (PlayerEntity) (Object) this;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onSwarmTick(CallbackInfo ci) {
        if (thisPlayer.getEntityWorld().isClient()) return;
        SwarmUtil.tick((ServerPlayerEntity) thisPlayer);
    }
}
