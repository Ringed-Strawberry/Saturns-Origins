package rings_of_saturn.github.io.saturns_origins.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import rings_of_saturn.github.io.saturns_origins.util.OriginUtil;

public class PlayerLogOffEvent {
    public static void registerLogOffEvent(){
        ServerPlayConnectionEvents.DISCONNECT.register(Event.DEFAULT_PHASE,
                (handler, server) -> {
            if(OriginUtil.isChorusfruitborn(handler.getPlayer())){

            }
        });
    }
}
