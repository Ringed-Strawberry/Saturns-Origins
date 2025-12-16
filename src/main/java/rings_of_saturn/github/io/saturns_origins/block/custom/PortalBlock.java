package rings_of_saturn.github.io.saturns_origins.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;
import rings_of_saturn.github.io.saturns_origins.block.entity.tickers.PortalBlockEntityTicker;
import rings_of_saturn.github.io.saturns_origins.components.util.PortalPositionUtil;

public class PortalBlock extends BlockWithEntity {
    public static BooleanProperty RETURN_PORTAL = BooleanProperty.of("is_return_portal");

    public PortalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(RETURN_PORTAL, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RETURN_PORTAL);
        super.appendProperties(builder);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!world.isClient() && world.getBlockEntity(pos) instanceof PortalBlockEntity blockEntity && entity.getPortalCooldown() == 0) {
            entity.setPortalCooldown(40);
            Vec3d TPPos;

            ServerWorld TPWorld = world.getServer().getWorld(RegistryKey.of(RegistryKeys.WORLD, Identifier.tryParse(blockEntity.getDim())));
            if (!state.get(RETURN_PORTAL)) {
                PlayerEntity player = world.getServer().getPlayerManager().getPlayer(blockEntity.getPlayerName());
                TPPos = PortalPositionUtil.getPortalPos(player);
            } else {
                TPPos = new Vec3d(blockEntity.getTPPos()[0], blockEntity.getTPPos()[1], blockEntity.getTPPos()[2]);
            }

            Vec3d intialVelocity = entity.getVelocity();
            entity.teleport(TPWorld, TPPos.getX(), TPPos.getY(), TPPos.getZ(), PositionFlag.ROT, entity.getYaw(), entity.getPitch());

            entity.setVelocity(intialVelocity);

            if (entity instanceof ServerPlayerEntity serverPlayer){
                serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayer));
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return new PortalBlockEntityTicker();
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
