package rings_of_saturn.github.io.saturns_origins.util;

import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class OriginUtil {
    public static boolean hasOrigin(Entity entity, String name){
        OriginLayer layers = OriginLayers.getLayer(Identifier.of("origins", "origin"));
        return Origin.get(entity).get(layers).getName().getString().equals(name);
    }


    public static boolean isChorusfruitborn(Entity entity){
        OriginLayer layers = OriginLayers.getLayer(Identifier.of("origins", "origin"));
        return Origin.get(entity).get(layers).getName().getString().equals("Chorusfruitborn");
    }
}
