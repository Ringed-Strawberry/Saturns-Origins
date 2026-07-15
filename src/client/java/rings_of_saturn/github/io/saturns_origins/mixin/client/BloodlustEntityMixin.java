package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.client.BloodlustClientManager;

@Mixin(value = Entity.class)
public class BloodlustEntityMixin {

    @Unique
    Entity thisAsEntity = (Entity) (Object)this;

    @Inject(method = "getTeamColorValue", at=@At("HEAD"), cancellable = true)
    private void applyGlowColor(CallbackInfoReturnable<Integer> cir){
        if (thisAsEntity.getEntityWorld() != null
                && thisAsEntity.getEntityWorld().isClient()
                && BloodlustClientManager.isMarked(thisAsEntity)) {
            cir.setReturnValue(BloodlustClientManager.getColor(thisAsEntity));
        }
    }
}
