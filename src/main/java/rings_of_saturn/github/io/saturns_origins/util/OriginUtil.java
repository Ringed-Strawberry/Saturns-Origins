package rings_of_saturn.github.io.saturns_origins.util;

import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class OriginUtil {
    public static final Identifier OWLFOLK_ID = Identifier.of("saturns_origins", "owlfolk");
    public static final Identifier CHORUSFRUITBORN_ID = Identifier.of("saturns_origins", "chorusfruitborn");

    public static boolean hasOrigin(Entity entity, Identifier originId){
        OriginLayer layer = OriginLayers.getLayer(Identifier.of("origins", "origin"));
        Origin origin = Origin.get(entity).get(layer);
        return origin != null && origin.getIdentifier().equals(originId);
    }

    public static boolean isOwlfolk(Entity entity){
        return hasOrigin(entity, OWLFOLK_ID);
    }

    public static boolean isChorusfruitborn(Entity entity){
        return hasOrigin(entity, CHORUSFRUITBORN_ID);
    }
}
