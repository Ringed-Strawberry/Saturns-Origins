package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import rings_of_saturn.github.io.saturns_origins.client.SaturnsOriginsClient;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;
import rings_of_saturn.github.io.saturns_origins.utils.OwlFolkMixinUtil;

@Mixin(BipedEntityRenderState.class)
public class BipedEntityRenderStateMixin implements OwlFolkMixinUtil {

    @Override
    public boolean saturns_origins$isOwlFolk() {
        return OriginUtil.isOwlfolk(SaturnsOriginsClient.client.player);
    }
}
