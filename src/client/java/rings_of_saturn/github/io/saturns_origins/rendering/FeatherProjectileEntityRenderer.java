package rings_of_saturn.github.io.saturns_origins.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

@Environment(EnvType.CLIENT)
public class FeatherProjectileEntityRenderer extends ProjectileEntityRenderer<FeatherProjectileEntity, ProjectileEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.of(MOD_ID, "textures/entity/feather_projectile_entity.png");

    public FeatherProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }

    @Override
    protected Identifier getTexture(ProjectileEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public void updateRenderState(FeatherProjectileEntity entity, ProjectileEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
    }

    @Override
    public void render(ProjectileEntityRenderState state, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
        super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }
}
