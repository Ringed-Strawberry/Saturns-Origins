package rings_of_saturn.github.io.saturns_origins.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(value = EndermanEntity.class)
public abstract class NeutralEndermanMixin extends LivingEntity {
    protected NeutralEndermanMixin(EntityType<? extends LivingEntity> entityType, World world){
        super(entityType, world);
    }

    @ModifyReturnValue(method = "isPlayerStaring", at=@At("RETURN"))
    protected boolean cancelStaring(boolean base, PlayerEntity player){
        if (OriginUtil.isChorusfruitborn(player)){
            return false;
        }
        return base;
    }
}
