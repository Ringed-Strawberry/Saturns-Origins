package rings_of_saturn.github.io.saturns_origins.client;

import net.fabricmc.api.ClientModInitializer;

import static rings_of_saturn.github.io.saturns_origins.event.KeyInputHandler.registerKeyInputs;

public class SaturnsOriginsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerKeyInputs();
    }
}
