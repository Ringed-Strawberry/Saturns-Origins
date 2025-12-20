package rings_of_saturn.github.io.saturns_origins.util;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.apace100.apoli.power.VariableIntPower;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ResourceUtil {
    private static final PowerType<VariableIntPower> SWARM_CHARGE = PowerTypeRegistry.get(Identifier.of(MOD_ID, "swarm_charge"));
    public static int getSwarmCharge(PlayerEntity player){
        return PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).getValue();
    }

    public static void decrementSwarmCharge(PlayerEntity player){
        PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).decrement();
    }

    public static void incrementSwarmCharge(PlayerEntity player){
        PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).increment();
    }

    public static void resetSwarmCharge(PlayerEntity player){
        PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).setValue(0);
    }

    public static boolean isSwarmCharged(PlayerEntity player){
        return getSwarmCharge(player) == PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).getMax();
    }
}
