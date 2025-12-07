package rings_of_saturn.github.io.saturns_origins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

@Mixin(value = ChorusFruitItem.class)
public class ChorusFruitMixin {

    @Unique
    ChorusFruitItem thisAsItem = (ChorusFruitItem)(Object)this;

    @Inject(method = "finishUsing", at=@At("HEAD"),  cancellable = true)
    void eat(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = thisAsItem.isFood() ? user.eatFood(world, stack) : stack;
        if (!world.isClient) {
            double d = user.getX();
            double e = user.getY();
            double f = user.getZ();

            for (int i = 0; i < 16; ++i) {
                double g = user.getX() + (user.getRandom().nextDouble() - (double) 0.5F) * (double) 16.0F;
                double h = MathHelper.clamp(user.getY() + (double) (user.getRandom().nextInt(16) - 8), (double) world.getBottomY(), (double) (world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1));
                double j = user.getZ() + (user.getRandom().nextDouble() - (double) 0.5F) * (double) 16.0F;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }

                Vec3d vec3d = user.getPos();
                if (OriginUtil.isChorusfruitborn(user) && user.isSneaking()) {
                    if (user.teleport(g, h, j, true)) {
                        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                        SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        world.playSound((PlayerEntity) null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        user.playSound(soundEvent, 1.0F, 1.0F);
                        break;
                    }

                    if (user instanceof PlayerEntity) {
                        ((PlayerEntity) user).getItemCooldownManager().set(thisAsItem, 20);
                    }
                }
                if(!OriginUtil.isChorusfruitborn(user)){
                    if (user.teleport(g, h, j, true)) {
                        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                        SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        world.playSound((PlayerEntity) null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        user.playSound(soundEvent, 1.0F, 1.0F);
                        break;
                    }

                    if (user instanceof PlayerEntity) {
                        ((PlayerEntity) user).getItemCooldownManager().set(thisAsItem, 20);
                    }
                }
            }
        }
        cir.setReturnValue(itemStack);
    }
}
