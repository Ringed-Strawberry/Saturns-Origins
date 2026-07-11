package rings_of_saturn.github.io.saturns_origins.components.types;


import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
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
    public void readData(ReadView readView) {
        this.value = readView.getString("value", "minecraft:overworld");
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putString("value", this.value);
    }
}
