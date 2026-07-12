package rings_of_saturn.github.io.saturns_origins.mixin.client;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import rings_of_saturn.github.io.saturns_origins.utils.OwlFolk;

@Mixin(BipedEntityRenderState.class)
public class BipedEntityRenderStateMixin implements OwlFolk {
    @Unique
    private boolean isOwlFolk;

    @Override
    public boolean saturns_origins$isOwlFolk() {
        return this.isOwlFolk;
    }

    @Override
    public void saturns_origins$setOwlFolk(boolean value) {
        isOwlFolk = value;
    }
}
