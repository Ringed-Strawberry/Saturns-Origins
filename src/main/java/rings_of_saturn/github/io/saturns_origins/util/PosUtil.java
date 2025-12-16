package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.util.math.Vec3d;

public class PosUtil {
    public static Vec3d getVec3dFromString(String string){
        String[] splitValues = string.split(",");
        double x = Double.valueOf(splitValues[0]);
        double y = Double.valueOf(splitValues[1]);
        double z = Double.valueOf(splitValues[2]);
        Vec3d vec3d = new Vec3d(x,y,z);
        return vec3d;
    }
}
