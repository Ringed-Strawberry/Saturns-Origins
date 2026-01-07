package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(value = ElytraFeatureRenderer.class)
public class NoElytraMixin<T extends LivingEntity> {
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "HEAD"), cancellable = true)
    void hideElytra(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci){
        if(OriginUtil.isOwlfolk(entity) && entity.isInvisible()){
            ci.cancel();
        }
    }
}
