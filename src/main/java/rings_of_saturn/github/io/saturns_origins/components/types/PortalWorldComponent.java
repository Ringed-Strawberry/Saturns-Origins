package rings_of_saturn.github.io.saturns_origins.components.types;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class PortalWorldComponent implements StringComponent, AutoSyncedComponent {
    private String value = "minecraft:overworld";
    private final Object provider;
    public PortalWorldComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
        ModComponents.PORTAL_WORLD.sync(this.provider);
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.value = nbtCompound.getString("portaL_dim");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putString("portaL_dim", this.value);
    }
}
