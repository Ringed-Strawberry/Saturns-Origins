package rings_of_saturn.github.io.saturns_origins.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.block.custom.PortalBlock;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;
import rings_of_saturn.github.io.saturns_origins.entity.custom.FeatherProjectileEntity;
import rings_of_saturn.github.io.saturns_origins.networking.packet.*;
import rings_of_saturn.github.io.saturns_origins.util.*;

public class ServerPackets {

    public static void registerC2SPackets(){
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            SwarmUtil.onDisconnect(handler.player);
        });

        ServerPlayNetworking.registerGlobalReceiver(BackstabPayloadC2S.ID,
                (payload, context) -> {
                    Vec3d pos = payload.pos();
                    float yaw = payload.yaw();
                    context.server().execute(() -> {
                        if(ResourceUtil.canBackstab(context.player())) {
                            context.player().setInvulnerable(true);
                            CooldownUtil.resetInvulnerableFrames(context.player());
                            context.player().setPosition(pos);
                            context.player().setYaw(yaw);
                            PacketByteBuf buf = PacketByteBufs.create();
                            buf.writeString(pos.getX() + "," + pos.getY() + "," + pos.getZ());
                            buf.writeFloat(yaw);
                            ServerPlayNetworking.send(context.player(), new BackstabUpdatePosPayloadS2C(pos, yaw));
                            ResourceUtil.triggerBackstabCooldown(context.player());
                        }
                    });
        });

        ServerPlayNetworking.registerGlobalReceiver(SpawnPortalPayloadC2S.ID,
                (payload, context) -> context.server().execute(() -> {
                     if(CooldownUtil.isPortalCooldownOver(context.player())) {
                         boolean hasEyes = false;
                         for (int i = 0; i < context.player().getInventory().size(); ++i) {
                             ItemStack stack = context.player().getInventory().getStack(i);
                             if (stack.getItem() == Items.ENDER_EYE) {
                                 hasEyes = true;
                                 context.player().setPortalCooldown(10);
                                 stack.decrement(1);
                                 BlockState defaultState = BlockGen.CHORUSFRUITBORN_PORTAL.getDefaultState();
                                 BlockPos pos = context.player().getBlockPos().add(0, 1, 0);
                                 context.player().getEntityWorld().setBlockState(pos, defaultState);
                                 context.player().getEntityWorld().addBlockEntity(ModBlockEntities.PORTAL.instantiate(pos, defaultState));
                                 if (context.player().getEntityWorld().getBlockEntity(pos) instanceof PortalBlockEntity blockEntity) {
                                     blockEntity.setPlayerName(context.player().getName().getString());
                                     blockEntity.setDim(PortalPositionUtil.getPortalWorldAsString(context.player()));
                                 }
                                 BlockPos returnPortalPos = BlockPos.ofFloored(PortalPositionUtil.getPortalPos(context.player())).add(0, 1, 0);
                                 World TPWorld = PortalPositionUtil.getPortalWorld(context.player());
                                 TPWorld.setBlockState(returnPortalPos, defaultState.with(PortalBlock.RETURN_PORTAL, true));
                                 TPWorld.addBlockEntity(ModBlockEntities.PORTAL.instantiate(returnPortalPos, defaultState.with(PortalBlock.RETURN_PORTAL, true)));
                                 if (TPWorld.getBlockEntity(returnPortalPos) instanceof PortalBlockEntity blockEntity) {
                                     int[] returnPos = new int[3];
                                     returnPos[0] = context.player().getBlockPos().getX();
                                     returnPos[1] = context.player().getBlockPos().getY();
                                     returnPos[2] = context.player().getBlockPos().getZ();
                                     blockEntity.setPos(returnPos);
                                     blockEntity.setDim(context.player().getEntityWorld().getRegistryKey().getValue().toString());
                                     blockEntity.setPlayerName(context.player().getName().getString());
                                 }
                                 CooldownUtil.resetPortalCooldown(context.player());
                             }
                         }
                         if(!hasEyes){
                             context.player().sendMessage(Text.of("This ability Requires: 1 Eye Of Ender"), true);
                         }
                     }
                 }));

        ServerPlayNetworking.registerGlobalReceiver(SetPortalPayloadC2S.ID,
                (payload, context) -> context.server().execute(() -> {
                    context.player().sendMessage(Text.of("Set portal position to " + context.player().getBlockPos().toShortString()), true);
                    PortalPositionUtil.setPortalWorld(context.player());
                    PortalPositionUtil.setPortalPos(context.player());
                 }));

        ServerPlayNetworking.registerGlobalReceiver(SwarmAttackPayloadC2S.ID,
                (payload, context) -> context.server().execute(() -> {
                    if(OriginUtil.isOwlfolk(context.player()) && ResourceUtil.isSwarmActive(context.player()) && KeybindUtil.canAttack(context.player())){
                        KeybindUtil.setAttack(context.player(), false);
                        ResourceUtil.decrementSwarmCharge(context.player());
                        ServerWorld world = context.server().getWorld(context.player().getEntityWorld().getRegistryKey());
                        FeatherProjectileEntity entity = new FeatherProjectileEntity(world, context.player());
                        entity.setVelocity(context.player(), context.player().getPitch(), context.player().getYaw(), 0.0F, 1.5F, 1.0F);
                        world.spawnEntity(entity);


                        //actually like send out the feather
                    }
                }));

        ServerPlayNetworking.registerGlobalReceiver(SwarmResetPayloadC2S.ID,
                (payload, context) -> context.server().execute(() -> {
                    if(OriginUtil.isOwlfolk(context.player()) && ResourceUtil.isSwarmActive(context.player()) && !KeybindUtil.canAttack(context.player())){
                        KeybindUtil.setAttack(context.player(), true);
                    }
                }));
    }
}
