package rings_of_saturn.github.io.saturns_origins.components.types;

import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Vec3d;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class PortalPositionComponent implements Vec3dComponent, AutoSyncedComponent {
    private Vec3d value = new Vec3d(0,0,0);
    private final Object provider;
    public PortalPositionComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public void readData(ReadView readView) {
        this.value = new Vec3d(readView.getDouble("value1", 0), readView.getDouble("value2", 0), readView.getDouble("value3", 0));
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putDouble("value1", this.value.getX());
        writeView.putDouble("value2", this.value.getY());
        writeView.putDouble("value3", this.value.getZ());
    }

    @Override
    public Vec3d getValue() {
        return value;
    }

    @Override
    public void setValue(Vec3d value) {
        this.value = value;
        ModComponents.PORTAL_POSITION.sync(this.provider);
    }
}
