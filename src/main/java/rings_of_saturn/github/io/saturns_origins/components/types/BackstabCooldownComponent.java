package rings_of_saturn.github.io.saturns_origins.components.types;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;

public class BackstabCooldownComponent implements IntComponent, AutoSyncedComponent {
    private int value = 0;
    private final Object provider;
    public BackstabCooldownComponent(Object provider) {
        this.provider = provider;
    }
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.value = nbtCompound.getInt("value");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("value", this.value);
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void decrement() {
        if(this.value-- >= 0)
            this.value--;
    }

    @Override
    public boolean isOver() {
        return this.value <= 0;
    }
}
