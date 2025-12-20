package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.util.ResourceUtil;

@Mixin(value = PlayerEntity.class)
public class SwarmPlayerMixin {

    @Unique
    PlayerEntity thisAsPlayer = (PlayerEntity) (Object)this;
    @Inject(method = "tick", at=@At("HEAD"))
    private void circleProjectiles(CallbackInfo ci){

    }

    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/ItemEntity;", at=@At("HEAD"))
    private void test(ItemStack stack, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir){
        ResourceUtil.incrementSwarmCharge(thisAsPlayer);
    }

}
