package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;
import rings_of_saturn.github.io.saturns_origins.util.MathUtil;

@Mixin(value = PlayerEntity.class)
public class SwarmPlayerMixin {

    @Unique
    PlayerEntity thisAsPlayer = (PlayerEntity) (Object)this;
    @Inject(method = "tick", at=@At("HEAD"))
    private void circleProjectiles(CallbackInfo ci) {
        if (thisAsPlayer.getWorld().isClient()) {
            for (int i = 0; i < MathUtil.getPointsInCircle(thisAsPlayer.getPos(), 6, 1).length; i++) {
                thisAsPlayer.getWorld().addParticle(
                        new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(ModEntities.FEATHER_UP_PROJECTILE_ITEM)),
                        MathUtil.getPointsInCircle(thisAsPlayer.getPos(), 6, 1)[i].getX(),
                        MathUtil.getPointsInCircle(thisAsPlayer.getPos(), 6, 1)[i].getY(),
                        MathUtil.getPointsInCircle(thisAsPlayer.getPos(), 6, 1)[i].getZ(),
                        0,0,0);
            }
        }
    }
}
