package rings_of_saturn.github.io.saturns_origins.networking;

import io.github.apace100.origins.Origins;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;
import rings_of_saturn.github.io.saturns_origins.components.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.components.util.PortalPositionUtil;
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
                        if(CooldownUtil.isBackstabCooldownOver(serverPlayerEntity)) {
                            Origins.LOGGER.info(pos);
                            Origins.LOGGER.info(yaw);
                            serverPlayerEntity.setPosition(pos.toCenterPos());
                            serverPlayerEntity.setYaw(yaw);
                            serverPlayerEntity.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), yaw, serverPlayerEntity.getPitch());
                            CooldownUtil.resetBackstabCooldown(serverPlayerEntity);
                        }
                    });
        });

        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.SPAWN_PORTAL_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> {
                    minecraftServer.execute(() -> {
                        if(CooldownUtil.isPortalCooldownOver(serverPlayerEntity)) {
                            for (int i = 0; i < serverPlayerEntity.getInventory().size(); ++i) {
                                ItemStack stack = serverPlayerEntity.getInventory().getStack(i);
                                if (stack.getItem() == Items.ENDER_EYE) {
                                    stack.decrement(1);
                                    BlockPos pos = serverPlayerEntity.getBlockPos().add(0, 1, 0);
                                    serverPlayerEntity.getWorld().setBlockState(pos, BlockGen.CHORUSFRUITBORN_PORTAL.getDefaultState());
                                    serverPlayerEntity.getWorld().addBlockEntity(ModBlockEntities.PORTAL.instantiate(pos, BlockGen.CHORUSFRUITBORN_PORTAL.getDefaultState()));
                                    if (serverPlayerEntity.getWorld().getBlockEntity(pos) instanceof PortalBlockEntity blockEntity) {
                                        blockEntity.setPlayerName(serverPlayerEntity.getName().getString());
                                    }
                                    CooldownUtil.resetPortalCooldown(serverPlayerEntity);
                                }
                            }
                        }
                    });
                });

        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.SET_PORTAL_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> {
                    minecraftServer.execute(() -> {
                        PortalPositionUtil.setPortalPos(serverPlayerEntity, serverPlayerEntity.getPos());
                    });
                });
    }
}
