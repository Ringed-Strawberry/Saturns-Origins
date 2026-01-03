package rings_of_saturn.github.io.saturns_origins.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ModEntities {
    public static final EntityType<FeatherProjectileEntity> FEATHER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "feather_projectile"),
            FabricEntityTypeBuilder.<FeatherProjectileEntity>create(SpawnGroup.MISC, FeatherProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(1)
                    .build()
    );

    public static final Item FEATHER_PROJECTILE_ITEM =  Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "feather_projectile"),
            new Item(new Item.Settings()));

    public static final Item FEATHER_UP_PROJECTILE_ITEM =  Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "feather_up_projectile"),
            new Item(new Item.Settings()));



    public static void registerModEntities(){

    }
}
