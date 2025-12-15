package rings_of_saturn.github.io.saturns_origins.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;

import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.MagicPlatformEntity;

public class MagicPlatformBlock extends BlockWithEntity {
    public MagicPlatformBlock(Settings settings) {
        super(settings);

    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!world.isClient){
            return checkType(type,ModBlockEntities.PLATFORM,MagicPlatformEntity::tick);
        }
        return null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }


    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MagicPlatformEntity(pos,state);
    }


    @Override
    public @Nullable <T extends BlockEntity> GameEventListener getGameEventListener(ServerWorld world, T blockEntity) {
        return super.getGameEventListener(world, blockEntity);
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }
//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, ShapeContext context) {
//        return VoxelShapes.empty();
//    }
}
