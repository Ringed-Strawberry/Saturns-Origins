package rings_of_saturn.github.io.saturns_origins.networking;

import io.github.apace100.origins.Origins;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.math.BlockPos;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;
import rings_of_saturn.github.io.saturns_origins.networking.packet.PacketConstants;

public class Packets {

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.BACKSTAB_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> {
                    BlockPos pos = packetByteBuf.readBlockPos();
                    float yaw = packetByteBuf.readFloat();
                    minecraftServer.execute(() -> {

                        Origins.LOGGER.info(pos);
                        Origins.LOGGER.info(yaw);
                        serverPlayerEntity.setPosition(pos.toCenterPos());
                        serverPlayerEntity.setYaw(yaw);
                        serverPlayerEntity.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), yaw, serverPlayerEntity.getPitch());

                    });
        });

        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.PORTAL_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> {
                    minecraftServer.execute(() -> {
                        BlockPos pos = serverPlayerEntity.getBlockPos();

                        serverPlayerEntity.getWorld().setBlockState(pos, BlockGen.CHORUSFRUITBORN_PORTAL.getDefaultState());
                        ModBlockEntities.PORTAL.instantiate(pos, BlockGen.CHORUSFRUITBORN_PORTAL.getDefaultState())
                                .setPlayerName(String.valueOf(serverPlayerEntity.getName()));
                    });
                });
    }
}
