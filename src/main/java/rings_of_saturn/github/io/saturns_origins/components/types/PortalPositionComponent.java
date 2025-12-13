package rings_of_saturn.github.io.saturns_origins.components.types;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import rings_of_saturn.github.io.saturns_origins.components.ModComponents;

public class PortalPositionComponent implements Vec3dComponent, AutoSyncedComponent {
    private Vec3d value = new Vec3d(0,0,0);
    private final Object provider;
    public PortalPositionComponent(Object provider) {
        this.provider = provider;
    }
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.value = new Vec3d(nbtCompound.getDouble("value1"),nbtCompound.getDouble("value2"),nbtCompound.getDouble("value3"));
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putDouble("value1", this.value.getX());
        nbtCompound.putDouble("value2", this.value.getY());
        nbtCompound.putDouble("value3", this.value.getZ());
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
