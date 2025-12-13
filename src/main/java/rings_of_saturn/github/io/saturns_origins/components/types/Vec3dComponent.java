package rings_of_saturn.github.io.saturns_origins.components.types;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.util.math.Vec3d;

public interface Vec3dComponent extends Component {
    Vec3d getValue();
    void setValue(Vec3d value);
}
