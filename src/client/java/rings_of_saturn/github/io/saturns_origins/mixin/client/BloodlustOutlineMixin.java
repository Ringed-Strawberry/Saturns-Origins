package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.client.BloodlustClientManager;

@Mixin(MinecraftClient.class)
public class BloodlustOutlineMixin {

    @Inject(method = "hasOutline", at = @At("RETURN"), cancellable = true)
    private void onHasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && BloodlustClientManager.isMarked(entity)) {
            cir.setReturnValue(true);
        }
    }
}
