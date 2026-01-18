package rings_of_saturn.github.io.saturns_origins.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

@Environment(EnvType.CLIENT)
public class FeatherProjectileEntityRenderer extends ProjectileEntityRenderer<FeatherProjectileEntity> {
    public static final Identifier TEXTURE = Identifier.of(MOD_ID, "textures/entity/feather_projectile_entity.png");

    public FeatherProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(FeatherProjectileEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(FeatherProjectileEntity entity) {
        return TEXTURE;
    }
}