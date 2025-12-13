package rings_of_saturn.github.io.saturns_origins.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.components.types.PortalPositionComponent;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<PortalPositionComponent> PORTAL_POSITION =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "portal_position"), PortalPositionComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_POSITION, PortalPositionComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
