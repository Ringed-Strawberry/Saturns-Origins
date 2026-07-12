package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.client.SaturnsOriginsClient;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private static void cancelFood(CallbackInfo ci) {
        if(OriginUtil.isChorusfruitborn(SaturnsOriginsClient.client.player)) {
            ci.cancel();
        }
    }
}
