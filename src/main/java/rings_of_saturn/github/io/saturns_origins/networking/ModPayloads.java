package rings_of_saturn.github.io.saturns_origins.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import rings_of_saturn.github.io.saturns_origins.networking.packet.*;

public class ModPayloads {
    public static void registerPayloads(){
        PayloadTypeRegistry.playC2S().register(SetPortalPayloadC2S.ID, SetPortalPayloadC2S.CODEC);
        PayloadTypeRegistry.playC2S().register(SpawnPortalPayloadC2S.ID, SpawnPortalPayloadC2S.CODEC);

        PayloadTypeRegistry.playC2S().register(BackstabPayloadC2S.ID, BackstabPayloadC2S.CODEC);
        PayloadTypeRegistry.playS2C().register(BackstabUpdatePosPayloadS2C.ID, BackstabUpdatePosPayloadS2C.CODEC);

        PayloadTypeRegistry.playC2S().register(SwarmAttackPayloadC2S.ID, SwarmAttackPayloadC2S.CODEC);
        PayloadTypeRegistry.playC2S().register(SwarmResetPayloadC2S.ID, SwarmResetPayloadC2S.CODEC);
    }

}
