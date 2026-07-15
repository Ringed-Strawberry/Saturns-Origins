package rings_of_saturn.github.io.saturns_origins.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.ColorHelper;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static rings_of_saturn.github.io.saturns_origins.client.SaturnsOriginsClient.client;

public class BloodlustClientManager {

    private static final Map<UUID, MarkEntry> entries = new ConcurrentHashMap<>();

    private record MarkEntry(long expiryTick, MarkType type) {}

    public enum MarkType { BLOODLUST, SCAN }

    public static void addTarget(UUID uuid, int remainingTicks,MarkType type) {
        if (client.world == null) return;
        if (type == MarkType.SCAN) {
            MarkEntry existing = entries.get(uuid);
            if (existing != null && existing.type == MarkType.BLOODLUST){
                return;
            }
        }
        long expiryTick = client.world.getTime() + remainingTicks;
        entries.put(uuid, new MarkEntry(expiryTick, type));
    }

    public static boolean isMarked(Entity entity) {
        MarkEntry entry = entries.get(entity.getUuid());
        if (entry == null) return false;
        if (client.world == null || client.world.getTime() >= entry.expiryTick) {
            entries.remove(entity.getUuid());
            return false;
        }
        return true;
    }


    public static int getColor(Entity entity) {
        MarkEntry entry = entries.get(entity.getUuid());
        if (entry == null) return 0;
        if (client.world == null || client.world.getTime() >= entry.expiryTick) {
            entries.remove(entity.getUuid());
            return 0;
        }
        return switch (entry.type) {
            case BLOODLUST -> ColorHelper.getArgb(255, 255, 0, 0);    // red
            case SCAN      -> ColorHelper.getArgb(255, 223,70 , 97);  // lighter red
        };
    }

    public static void clearByType(MarkType type) {
        entries.values().removeIf(e -> e.type == type);
    }
}
