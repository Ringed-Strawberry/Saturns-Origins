package rings_of_saturn.github.io.saturns_origins.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.block.custom.MagicPlatformBlock;
import rings_of_saturn.github.io.saturns_origins.block.custom.PortalBlock;

import static rings_of_saturn.github.io.saturns_origins.SaturnsOrigins.MOD_ID;

import java.util.function.Function;

public class BlockGen {
    public static RegistryKey<Block> keyOf(Identifier id) {
        return RegistryKey.of(RegistryKeys.BLOCK, id);
    }

    public static RegistryKey<Item> itemKeyOf(Identifier id) {
        return RegistryKey.of(RegistryKeys.ITEM, id);
    }


    public static Block createBlock(Function<AbstractBlock.Settings, Block> factory, Identifier id, AbstractBlock.Settings settings) {
        return register(keyOf(id), itemKeyOf(id), factory, settings);
    }

    public static Block register(RegistryKey<Block> blockKey, RegistryKey<Item> itemKey, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static final Block CHORUSFRUITBORN_PORTAL = createBlock(PortalBlock::new, Identifier.of(MOD_ID, "chorusfruitborn_portal"), AbstractBlock.Settings.copy(Blocks.NETHER_PORTAL));

    public static final Block MAGICPLATFORMBLOCK = createBlock(MagicPlatformBlock::new, Identifier.of(MOD_ID, "magicplatformblock"),AbstractBlock.Settings.create().strength(-1f).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never));


    public static void registerModBlocks(){

    }
}
