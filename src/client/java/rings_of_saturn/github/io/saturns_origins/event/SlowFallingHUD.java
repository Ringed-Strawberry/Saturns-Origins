package rings_of_saturn.github.io.saturns_origins.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
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
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int x;
        int y;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && !client.options.hudHidden) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;

            y = height;
            if (client.player != null && OriginUtil.isOwlfolk(client.player)) {
                drawContext.drawTexture(RenderPipelines.GUI_TEXTURED, ResourceUtil.isSlowfalling(client.player) ? SLOWFALLING_ON : SLOWFALLING_OFF, x - 6, y - 54, 0, 0, textureSize, textureSize,
                        textureSize, textureSize);
            }
        }
    }
}
