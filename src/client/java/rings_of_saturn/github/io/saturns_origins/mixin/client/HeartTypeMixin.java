package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.client.SaturnsOriginsClient;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

@Mixin(InGameHud.HeartType.class)
public class HeartTypeMixin {

    @Unique
    private final InGameHud.HeartType thisAsHeartType = (InGameHud.HeartType) (Object) this;

    @Inject(method = "getTexture", at = @At("RETURN"), cancellable = true)
    private void changeTexture(boolean hardcore, boolean half, boolean blinking, CallbackInfoReturnable<Identifier> cir) {
        if(OriginUtil.isChorusfruitborn(SaturnsOriginsClient.client.player)) {
            if(thisAsHeartType == InGameHud.HeartType.POISONED || thisAsHeartType == InGameHud.HeartType.NORMAL) {
                cir.setReturnValue(Identifier.of(MOD_ID,cir.getReturnValue().getPath()));
            }
        }
    }
}
