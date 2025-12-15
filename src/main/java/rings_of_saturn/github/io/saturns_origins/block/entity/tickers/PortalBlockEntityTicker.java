package rings_of_saturn.github.io.saturns_origins.block.entity.tickers;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.block.entity.custom.PortalBlockEntity;

public class PortalBlockEntityTicker implements BlockEntityTicker {
    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if(!world.isClient() && blockEntity instanceof PortalBlockEntity portalBlockEntity){
            if(portalBlockEntity.getTimer()-1 >= 0)
                portalBlockEntity.decrementTimer();
            else{
                world.removeBlock(pos, false);
                world.removeBlockEntity(pos);
            }
            if(world.getServer().getPlayerManager().getPlayer(portalBlockEntity.getPlayerName()) == null){
                world.removeBlock(pos, false);
                world.removeBlockEntity(pos);
            }
        }
    }
}
