package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ElytraFeatureRenderer.class)
public class NoElytraMixin<S extends BipedEntityRenderState, M extends EntityModel<S>> {
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V", at = @At(value = "HEAD"), cancellable = true)
    void hideElytra(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int i, S bipedEntityRenderState, float f, float g, CallbackInfo ci){
        if(bipedEntityRenderState.invisible && bipedEntityRenderState.saturns_origins$isOwlFolk()){
            ci.cancel();
        }
    }
}
