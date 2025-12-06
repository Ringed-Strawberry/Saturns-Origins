package rings_of_saturn.github.io.saturns_origins.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

public class ModBlockEntities {

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<PortalBlockEntity> PORTAL = register(
            "chorusfruitborn_portal",
            FabricBlockEntityTypeBuilder.create(PortalBlockEntity::new, BlockGen.CHORUSFRUITBORN_PORTAL).build()
    );

    public static void registerModBlockEntities(){
    }
}
