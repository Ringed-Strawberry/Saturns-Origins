package rings_of_saturn.github.io.saturns_origins.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPayloads {
    public static void registerPayloads(){
        PayloadTypeRegistry.configurationC2S().register(SetPortalPayloadC2S.ID, SetPortalPayloadC2S.CODEC);
        PayloadTypeRegistry.configurationC2S().register(SpawnPortalPayloadC2S.ID, SpawnPortalPayloadC2S.CODEC);

        PayloadTypeRegistry.configurationC2S().register(BackstabPayloadC2S.ID, BackstabPayloadC2S.CODEC);
        PayloadTypeRegistry.configurationS2C().register(BackstabUpdatePosPayloadS2C.ID, BackstabUpdatePosPayloadS2C.CODEC);

        PayloadTypeRegistry.configurationC2S().register(SwarmAttackPayloadC2S.ID, SwarmAttackPayloadC2S.CODEC);
        PayloadTypeRegistry.configurationC2S().register(SwarmResetPayloadC2S.ID, SwarmResetPayloadC2S.CODEC);
    }

}
