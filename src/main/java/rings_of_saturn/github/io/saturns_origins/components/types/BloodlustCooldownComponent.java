package rings_of_saturn.github.io.saturns_origins.components.types;

import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class BloodlustCooldownComponent implements IntComponent, AutoSyncedComponent {
    private int value = 0;
    private final Object provider;
    public BloodlustCooldownComponent(Object provider) {
        this.provider = provider;
    }
    @Override
    public void readData(ReadView readView) {
        this.value = readView.getInt("value", 0);
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putInt("value", this.value);
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
        ModComponents.BLOODLUST_COOLDOWN.sync(this.provider);
    }

    @Override
    public void decrement() {
        if(this.getValue()-1 >= 0) {
            this.setValue(getValue()-1);
        }
    }

    @Override
    public boolean isOver() {
        return this.getValue() <= 0;
    }
}
