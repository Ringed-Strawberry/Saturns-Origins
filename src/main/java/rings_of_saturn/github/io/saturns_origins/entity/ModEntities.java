package rings_of_saturn.github.io.saturns_origins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherUpProjectileEntity;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ModEntities {
    public static final EntityType<FeatherProjectileEntity> FEATHER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "feather_projectile"),
            EntityType.Builder.<FeatherProjectileEntity>create(FeatherProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4).trackingTickInterval(1)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, "feather_projectile")))
    );

    public static final EntityType<FeatherUpProjectileEntity> FEATHER_UP_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "feather_up_projectile"),
            EntityType.Builder.<FeatherUpProjectileEntity>create(FeatherUpProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4).trackingTickInterval(1)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, "feather_up_projectile")))
    );

    public static final Item FEATHER_UP_PROJECTILE_ITEM =  Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "feather_up_projectile"),
            new Item(new Item.Settings()));



    public static void registerModEntities(){

    }
}
