package rings_of_saturn.github.io.saturns_origins.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.components.types.*;

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
    public static final ComponentKey<InvisibilityCooldownComponent> INVIS_COOLDOWN =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "invis_cooldown"), InvisibilityCooldownComponent.class);
    public static final ComponentKey<BloodlustCooldownComponent> BLOODLUST_COOLDOWN =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "bloodlust_cooldown"), BloodlustCooldownComponent.class);
    public static final ComponentKey<ProjectileAutoAimComponent> PROJECTILE_HAS_TP =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "has_tp"), ProjectileAutoAimComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_POSITION, PortalPositionComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_WORLD, PortalWorldComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(BACKSTAB_COOLDOWN, BackstabCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(PORTAL_COOLDOWN, PortalCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerForPlayers(INVIS_COOLDOWN, InvisibilityCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        entityComponentFactoryRegistry.registerFor(LivingEntity.class, BLOODLUST_COOLDOWN, BloodlustCooldownComponent::new);
        entityComponentFactoryRegistry.registerFor(ProjectileEntity.class, PROJECTILE_HAS_TP, ProjectileAutoAimComponent::new);
    }
}
