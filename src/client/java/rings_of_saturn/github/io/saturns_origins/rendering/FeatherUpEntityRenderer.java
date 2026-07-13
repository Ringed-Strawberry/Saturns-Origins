package rings_of_saturn.github.io.saturns_origins.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.state.FlyingItemEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import rings_of_saturn.github.io.saturns_origins.client.SaturnsOriginsClient;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherUpProjectileEntity;

@Environment(EnvType.CLIENT)
public class FeatherUpEntityRenderer extends FlyingItemEntityRenderer<FeatherUpProjectileEntity> {
    private static final double SWARM_SPEED = 1.5;
    private static final double SWARM_RADIUS = 1.0;

    public FeatherUpEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, 1.0f, false);
    }

    @Override
    public void render(FlyingItemEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState camera) {
        matrices.push();
        matrices.scale(1.0f, 1.0f, 1.0f);
        Vec3d camPos = camera.pos;
        float yaw = (float)Math.atan2(camPos.x - state.x, camPos.z - state.z);
        matrices.multiply(new Quaternionf().rotationY(yaw));
        state.itemRenderState.render(matrices, queue, state.light, OverlayTexture.DEFAULT_UV, state.outlineColor);
        matrices.pop();
    }

    @Override
    public void updateRenderState(FeatherUpProjectileEntity entity, FlyingItemEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);

        PlayerEntity player = SaturnsOriginsClient.client.player;
        if (player == null || entity.isRemoved()) return;

        // Interpolate player position to this frame
        double px = MathHelper.lerp(tickDelta, player.lastRenderX, player.getX());
        double py = MathHelper.lerp(tickDelta, player.lastRenderY, player.getY());
        double pz = MathHelper.lerp(tickDelta, player.lastRenderZ, player.getZ());

        // Orbit center with sinusoidal bob (matches server formula)
        double age = player.age + tickDelta;
        double centerY = py + 1 + Math.sin(age / 4.0) / 6.0;

        // Compute the feather's position on the circular orbit
        int charge = entity.getSwarmCharge();
        int index = entity.getSwarmIndex();
        if (charge <= 0) return;

        double angularStep = 2 * Math.PI / charge;
        double offsetRad = Math.toRadians(age * SWARM_SPEED);
        double angle = offsetRad + index * angularStep;

        // Override interpolated position with computed orbit position
        state.x = px + SWARM_RADIUS * Math.cos(angle);
        state.y = centerY;
        state.z = pz + SWARM_RADIUS * Math.sin(angle);
    }

    @Override
    protected boolean canBeCulled(FeatherUpProjectileEntity entity) {
        return false;
    }
}