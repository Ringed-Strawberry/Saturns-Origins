package rings_of_saturn.github.io.saturns_origins.components;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
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
    public static final ComponentKey<SwarmAttackComponent> SWARM_ATTACK =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "swarm_attack"), SwarmAttackComponent.class);
    public static final ComponentKey<IsInvisibleComponent> IS_INVIS =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "is_invis"), IsInvisibleComponent.class);
    public static final ComponentKey<InvulnerableFramesComponent> INVULNERABLE_FRAMES =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "invulnerable_frames"), InvulnerableFramesComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PORTAL_POSITION, PortalPositionComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(PORTAL_WORLD, PortalWorldComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(BACKSTAB_COOLDOWN, BackstabCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(PORTAL_COOLDOWN, PortalCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(INVIS_COOLDOWN, InvisibilityCooldownComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(SWARM_ATTACK, SwarmAttackComponent::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(INVULNERABLE_FRAMES, InvulnerableFramesComponent::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerFor(LivingEntity.class, BLOODLUST_COOLDOWN, BloodlustCooldownComponent::new);
        registry.registerFor(ProjectileEntity.class, PROJECTILE_HAS_TP, ProjectileAutoAimComponent::new);
        registry.registerForPlayers(IS_INVIS, IsInvisibleComponent::new,RespawnCopyStrategy.ALWAYS_COPY);
    }
}
