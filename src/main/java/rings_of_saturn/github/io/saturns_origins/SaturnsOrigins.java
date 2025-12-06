package rings_of_saturn.github.io.saturns_origins;

import net.fabricmc.api.ModInitializer;

import static rings_of_saturn.github.io.saturns_origins.block.BlockGen.registerModBlocks;
import static rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities.registerModBlockEntities;

public class SaturnsOrigins implements ModInitializer {
    public static final String MOD_ID = "saturns_origins";

    @Override
    public void onInitialize() {
        registerModBlocks();
        registerModBlockEntities();
    }
}
