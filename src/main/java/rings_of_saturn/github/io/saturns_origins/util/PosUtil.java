package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.util.math.Vec3d;

public class PosUtil {
    public static Vec3d getVec3dFromString(String string){
        String[] splitValues = string.split(",");
        double x = Double.parseDouble(splitValues[0]);
        double y = Double.parseDouble(splitValues[1]);
        double z = Double.parseDouble(splitValues[2]);
        return new Vec3d(x,y,z);
    }
}
