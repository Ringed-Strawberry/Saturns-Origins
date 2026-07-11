package rings_of_saturn.github.io.saturns_origins.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;
import rings_of_saturn.github.io.saturns_origins.util.ResourceUtil;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class SlowFallingHUD implements HudRenderCallback {
    private static final Identifier SLOWFALLING_ON = Identifier.of(MOD_ID,
            "textures/hud/slowfalling_on.png");
    private static final Identifier SLOWFALLING_OFF = Identifier.of(MOD_ID,
            "textures/hud/slowfalling_off.png");
    private static final int textureSize = 14;

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        int x;
        int y;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;

            y = height;
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            if (client.player != null && OriginUtil.isOwlfolk(client.player)) {
                RenderSystem.setShaderTexture(0, ResourceUtil.isSlowfalling(client.player) ? SLOWFALLING_ON : SLOWFALLING_OFF);
                drawContext.drawTexture(ResourceUtil.isSlowfalling(client.player) ? SLOWFALLING_ON : SLOWFALLING_OFF, x - 6, y - 54, 0, 0, textureSize, textureSize,
                        textureSize, textureSize);
            }
        }
    }
}
