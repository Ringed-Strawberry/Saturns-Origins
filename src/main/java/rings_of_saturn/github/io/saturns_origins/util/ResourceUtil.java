package rings_of_saturn.github.io.saturns_origins.util;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.apace100.apoli.power.VariableIntPower;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ResourceUtil {
    public static int getSwarmCharge(PlayerEntity player){
        if(getPowerComponent(player) != null && getPowerComponent(player).getPower(SWARM_CHARGE()) != null)
            return getPowerComponent(player).getPower(SWARM_CHARGE()).getValue();
        return 0;
    }

    public static PowerType<VariableIntPower> SWARM_CHARGE(){
        if(PowerTypeRegistry.contains(Identifier.of(MOD_ID, "swarm_charge")))
            return PowerTypeRegistry.get(Identifier.of(MOD_ID, "swarm_charge"));
        else
            return null;
    }

    public static PowerType<VariableIntPower> IS_ACTIVE(){
        if(PowerTypeRegistry.contains(Identifier.of(MOD_ID, "swarm_is_active")))
            return PowerTypeRegistry.get(Identifier.of(MOD_ID, "swarm_is_active"));
        else
            return null;
    }

    public static void decrementSwarmCharge(PlayerEntity player){
        setSwarmCharge(player, getSwarmCharge(player)-1);
    }

    public static void setSwarmCharge(PlayerEntity player, int newValue){
        if(getPowerComponent(player) != null){
            getPowerComponent(player).getPower(SWARM_CHARGE()).setValue(newValue);
            PowerHolderComponent.syncPower(player, SWARM_CHARGE());
        }
    }

    public static PowerHolderComponent getPowerComponent(PlayerEntity player) {
        if (PowerHolderComponent.KEY.maybeGet(player).isPresent())
            return PowerHolderComponent.KEY.get(player);
        return null;
    }

    public static void incrementSwarmCharge(PlayerEntity player){
        if(canSwarmCharge(player) && getPowerComponent(player) != null)
            getPowerComponent(player).getPower(SWARM_CHARGE()).increment();
    }

    public static void resetSwarmCharge(PlayerEntity player){
        if(getPowerComponent(player) != null && getPowerComponent(player).getPower(SWARM_CHARGE()) != null)
            getPowerComponent(player).getPower(SWARM_CHARGE()).setValue(0);
    }

    public static boolean isSwarmActive(PlayerEntity player){
        if(getPowerComponent(player) != null && getPowerComponent(player).getPower(SWARM_CHARGE()) != null)
            return getPowerComponent(player).getPower(IS_ACTIVE()).getValue() == 2;
        else
            return false;
    }

    public static boolean canSwarmCharge(PlayerEntity player){
        if(getPowerComponent(player) != null && getPowerComponent(player).getPower(SWARM_CHARGE()) != null)
            return getPowerComponent(player).getPower(IS_ACTIVE()).getValue() == 1;
        else
            return false;
    }
}
