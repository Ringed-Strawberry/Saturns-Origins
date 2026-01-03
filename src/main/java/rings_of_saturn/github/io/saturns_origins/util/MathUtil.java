package rings_of_saturn.github.io.saturns_origins.util;

import net.minecraft.util.math.Vec3d;

public class MathUtil {
    public static Vec3d[] getPointsInCircle(Vec3d center, int points, double radius){
        Vec3d[] array = new Vec3d[points];
        for (int i = 0; i < points; i++) {
            array[i] = new Vec3d(center.getX()+(radius * Math.cos((Math.PI *2 / points) * i)), center.getY(), center.getZ()+(radius * Math.sin((Math.PI *2 / points) * i)));
        }
        return array;
    }

    public static Vec3d[] getOffsetPointsInCircle(Vec3d center, int points, double radius,double offset){
        Vec3d[] array = new Vec3d[points];
        double offsetRad = Math.toRadians(offset);
        for (int i = 0; i < points; i++) {
            array[i] = new Vec3d(center.getX()+(radius * Math.cos(((Math.PI *2 / points) * i)+ offsetRad)), center.getY(), center.getZ()+(radius * Math.sin(((Math.PI *2 / points) * i)+ offsetRad)));
        }
        return array;
    }
}
