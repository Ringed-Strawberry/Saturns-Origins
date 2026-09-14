package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.PlayerLikeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;
import rings_of_saturn.github.io.saturns_origins.utils.OwlFolkMixinUtil;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/PlayerLikeEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V",
            at = @At("TAIL")
    )
    private void setOwlFolkFlag(PlayerLikeEntity entity, PlayerEntityRenderState state, float f, CallbackInfo ci) {
        state.saturns_origins$setOwlFolk(OriginUtil.isOwlfolk(entity));
    }
}
