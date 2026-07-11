package rings_of_saturn.github.io.saturns_origins.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import rings_of_saturn.github.io.saturns_origins.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

public class BackstabUltrakillReferenceHUD implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            if (client.player != null && OriginUtil.isChorusfruitborn(client.player) && !CooldownUtil.isInvulnerableFramesOver(client.player)) {
                int opacity = 35+((CooldownUtil.getInvulnerableFrames(client.player)-7)*5);
                client.player.sendMessage(Text.of(String.valueOf(opacity)));
                drawContext.fill(0, 0, width, height, ColorHelper.Argb.getArgb(opacity, 255,255,255));
            }
        }
    }
}
