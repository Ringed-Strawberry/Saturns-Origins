package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import rings_of_saturn.github.io.saturns_origins.client.SaturnsOriginsClient;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;
import rings_of_saturn.github.io.saturns_origins.utils.OwlFolkMixinUtil;

@Mixin(BipedEntityRenderState.class)
public class BipedEntityRenderStateMixin implements OwlFolkMixinUtil {

    @Unique
    private boolean saturns_origins$isOwlFolk = false;

    @Override
    public boolean saturns_origins$isOwlFolk() {
        return this.saturns_origins$isOwlFolk;
    }


    @Override
    public void saturns_origins$setOwlFolk(boolean value) {
        this.saturns_origins$isOwlFolk = value;
    }

}
