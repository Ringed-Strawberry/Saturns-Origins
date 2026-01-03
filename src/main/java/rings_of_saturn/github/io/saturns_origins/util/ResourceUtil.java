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
    private static final PowerType<VariableIntPower> IS_ACTIVE = PowerTypeRegistry.get(Identifier.of(MOD_ID, "swarm_is_active"));

    public static int getSwarmCharge(PlayerEntity player){
        if(PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE) != null)
            return PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).getValue();
        return 0;
    }

    public static void decrementSwarmCharge(PlayerEntity player){
        if(PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE) != null)
            PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).decrement();
    }

    public static void incrementSwarmCharge(PlayerEntity player){
        if(canSwarmCharge(player))
            PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).increment();
    }

    public static void resetSwarmCharge(PlayerEntity player){
        if(PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE) != null)
            PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE).setValue(0);
    }

    public static boolean isSwarmActive(PlayerEntity player){
        if(PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE) != null)
            return PowerHolderComponent.KEY.get(player).getPower(IS_ACTIVE).getValue() == 2;
        else
            return false;
    }

    public static boolean canSwarmCharge(PlayerEntity player){
        if(PowerHolderComponent.KEY.get(player).getPower(SWARM_CHARGE) != null)
            return PowerHolderComponent.KEY.get(player).getPower(IS_ACTIVE).getValue() == 1;
        else
            return false;
    }
}
