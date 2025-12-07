package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class)
public class PortalDetectorMixin {
    @Inject(method = "tick", at=@At("HEAD"))
    void tick(CallbackInfo ci){

    }
}
