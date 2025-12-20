package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;


@Mixin(Entity.class)
public abstract class NoFootstepMixin {

    @Shadow
    public abstract boolean isPlayer();
    @Unique
    Entity thisAsEntity = (Entity) (Object) this;
    @Inject(method = "playStepSound", at=@At("HEAD"),cancellable = true)
    private void noStepSound(BlockPos pos, BlockState state, CallbackInfo ci){
        if (isPlayer()){
            PlayerEntity thisAsPlayer = (PlayerEntity) thisAsEntity;
            if (OriginUtil.isOwlfolk(thisAsPlayer)){
                ci.cancel();
            }
        }
    }

    @Inject(method = "spawnSprintingParticles", at=@At("HEAD"), cancellable = true)
    private void NoSprintParticles(CallbackInfo ci){
        if (isPlayer()){
            PlayerEntity thisAsPlayer = (PlayerEntity) thisAsEntity;
            if (OriginUtil.isOwlfolk(thisAsPlayer)){
                ci.cancel();
            }
        }
    }
}
