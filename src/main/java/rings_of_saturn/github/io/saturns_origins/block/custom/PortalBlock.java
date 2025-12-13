package rings_of_saturn.github.io.saturns_origins.block.custom;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;
import rings_of_saturn.github.io.saturns_origins.components.util.PortalPositionUtil;

public class PortalBlock extends BlockWithEntity {
    public PortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(world.getBlockEntity(pos) instanceof PortalBlockEntity blockEntity){
            blockEntity.setPlayerName(placer.getName().getString());
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!world.isClient() && world.getBlockEntity(pos) instanceof PortalBlockEntity blockEntity){
            PlayerEntity player = world.getServer().getPlayerManager().getPlayer(blockEntity.getPlayerName());
            Vec3d TPPos = PortalPositionUtil.getPortalPos(player);
            entity.teleport(TPPos.getX(), TPPos.getY(), TPPos.getZ());
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PortalBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
