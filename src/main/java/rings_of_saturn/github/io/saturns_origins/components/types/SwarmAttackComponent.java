package rings_of_saturn.github.io.saturns_origins.components.types;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class SwarmAttackComponent implements BooleanComponent, AutoSyncedComponent {
    private boolean value = false;

    private final Object provider;
    public SwarmAttackComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public boolean getValue() {
        return this.value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
        ModComponents.SWARM_ATTACK.sync(this.provider);
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.value = nbtCompound.getBoolean("value");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putBoolean("value", this.value);
    }
}
