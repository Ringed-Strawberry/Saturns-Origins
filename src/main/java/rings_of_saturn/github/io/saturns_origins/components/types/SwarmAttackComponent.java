package rings_of_saturn.github.io.saturns_origins.components.types;

import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
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
    public void readData(ReadView readView) {
        this.value = readView.getBoolean("value", false);
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putBoolean("value", this.value);
    }
}
