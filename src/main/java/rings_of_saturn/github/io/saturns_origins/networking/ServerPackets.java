package rings_of_saturn.github.io.saturns_origins.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.block.custom.PortalBlock;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;
import rings_of_saturn.github.io.saturns_origins.util.CooldownUtil;
import rings_of_saturn.github.io.saturns_origins.util.PortalPositionUtil;
import rings_of_saturn.github.io.saturns_origins.networking.packet.PacketConstants;
import rings_of_saturn.github.io.saturns_origins.util.PosUtil;

public class ServerPackets {

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.BACKSTAB_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> {
                    Vec3d pos = PosUtil.getVec3dFromString(packetByteBuf.readString());
                    float yaw = packetByteBuf.readFloat();
                    minecraftServer.execute(() -> {
                        if(CooldownUtil.isBackstabCooldownOver(serverPlayerEntity)) {
                            serverPlayerEntity.setPosition(pos);
                            serverPlayerEntity.setYaw(yaw);
                            PacketByteBuf buf = PacketByteBufs.create();
                            buf.writeString(pos.getX() + "," + pos.getY() + "," + pos.getZ());
                            buf.writeFloat(yaw);
                            ServerPlayNetworking.send(serverPlayerEntity, PacketConstants.BACKSTAB_UPDATE_POS_ID, buf);
                            CooldownUtil.resetBackstabCooldown(serverPlayerEntity);
                        }
                    });
        });

        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.SPAWN_PORTAL_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> minecraftServer.execute(() -> {
                     if(CooldownUtil.isPortalCooldownOver(serverPlayerEntity)) {
                         boolean hasEyes = false;
                         for (int i = 0; i < serverPlayerEntity.getInventory().size(); ++i) {
                             ItemStack stack = serverPlayerEntity.getInventory().getStack(i);
                             if (stack.getItem() == Items.ENDER_EYE) {
                                 hasEyes = true;
                                 serverPlayerEntity.setPortalCooldown(10);
                                 stack.decrement(1);
                                 BlockState defaultState = BlockGen.CHORUSFRUITBORN_PORTAL.getDefaultState();
                                 BlockPos pos = serverPlayerEntity.getBlockPos().add(0, 1, 0);
                                 serverPlayerEntity.getWorld().setBlockState(pos, defaultState);
                                 serverPlayerEntity.getWorld().addBlockEntity(ModBlockEntities.PORTAL.instantiate(pos, defaultState));
                                 if (serverPlayerEntity.getWorld().getBlockEntity(pos) instanceof PortalBlockEntity blockEntity) {
                                     blockEntity.setPlayerName(serverPlayerEntity.getName().getString());
                                     blockEntity.setDim(PortalPositionUtil.getPortalWorldAsString(serverPlayerEntity));
                                 }
                                 BlockPos returnPortalPos = BlockPos.ofFloored(PortalPositionUtil.getPortalPos(serverPlayerEntity)).add(0, 1, 0);
                                 World TPWorld = PortalPositionUtil.getPortalWorld(serverPlayerEntity);
                                 TPWorld.setBlockState(returnPortalPos, defaultState.with(PortalBlock.RETURN_PORTAL, true));
                                 TPWorld.addBlockEntity(ModBlockEntities.PORTAL.instantiate(returnPortalPos, defaultState.with(PortalBlock.RETURN_PORTAL, true)));
                                 if (TPWorld.getBlockEntity(returnPortalPos) instanceof PortalBlockEntity blockEntity) {
                                     int[] returnPos = new int[3];
                                     returnPos[0] = serverPlayerEntity.getBlockPos().getX();
                                     returnPos[1] = serverPlayerEntity.getBlockPos().getY();
                                     returnPos[2] = serverPlayerEntity.getBlockPos().getZ();
                                     blockEntity.setPos(returnPos);
                                     blockEntity.setDim(serverPlayerEntity.getWorld().getRegistryKey().getValue().toString());
                                     blockEntity.setPlayerName(serverPlayerEntity.getName().getString());
                                 }
                                 CooldownUtil.resetPortalCooldown(serverPlayerEntity);
                             }
                         }
                         if(!hasEyes){
                             serverPlayerEntity.sendMessage(Text.of("This ability Requires: 1 Eye Of Ender"), true);
                         }
                     }
                 }));

        ServerPlayNetworking.registerGlobalReceiver(PacketConstants.SET_PORTAL_PACKET_ID,
                (minecraftServer, serverPlayerEntity,
                 serverPlayNetworkHandler, packetByteBuf,
                 packetSender) -> minecraftServer.execute(() -> {
                    serverPlayerEntity.sendMessage(Text.of("Set portal position to " + serverPlayerEntity.getBlockPos().toShortString()), true);
                    PortalPositionUtil.setPortalWorld(serverPlayerEntity);
                    PortalPositionUtil.setPortalPos(serverPlayerEntity);
                 }));
    }
}
