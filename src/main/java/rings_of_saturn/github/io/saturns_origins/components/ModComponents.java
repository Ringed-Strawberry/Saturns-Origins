package rings_of_saturn.github.io.saturns_origins.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.components.types.BackstabCooldownComponent;
import rings_of_saturn.github.io.saturns_origins.components.types.PortalCooldownComponent;
import rings_of_saturn.github.io.saturns_origins.components.types.PortalPositionComponent;
import rings_of_saturn.github.io.saturns_origins.components.types.PortalWorldComponent;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<PortalPositionComponent> PORTAL_POSITION =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "portal_position"), PortalPositionComponent.class);
    public static final ComponentKey<PortalWorldComponent> PORTAL_WORLD =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "portal_dim"), PortalWorldComponent.class);
    public static final ComponentKey<BackstabCooldownComponent> BACKSTAB_COOLDOWN =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "backstab_cooldown"), BackstabCooldownComponent.class);
    public static final ComponentKey<PortalCooldownComponent> PORTAL_COOLDOWN =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "portal_cooldown"), PortalCooldownComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_POSITION, PortalPositionComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_WORLD, PortalWorldComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(BACKSTAB_COOLDOWN, BackstabCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_COOLDOWN, PortalCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);

    }
}
