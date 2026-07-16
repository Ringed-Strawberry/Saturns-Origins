package rings_of_saturn.github.io.saturns_origins.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.block.entity.ModBlockEntities;
import rings_of_saturn.github.io.saturns_origins.particle.ModParticles;

import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.PLATFORM_LIFETIME_TICKS;
import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.PLATFORM_PARTICLE_GRID;

public class MagicPlatformEntity extends BlockEntity {
    private int timer = PLATFORM_LIFETIME_TICKS;
    public MagicPlatformEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public MagicPlatformEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLATFORM, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, MagicPlatformEntity entity) {
        if (entity.timer <= 0){
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.removeBlockEntity(pos);
            return;
        }


        for (int i = 0; i < PLATFORM_PARTICLE_GRID; i++) {
            for (int j = 0; j < PLATFORM_PARTICLE_GRID; j++) {
                ((ServerWorld)world).spawnParticles(ModParticles.ONE_TICK_PARTICLE,pos.getX()+ (double) i /10,pos.getY()+1.0,pos.getZ()+ (double) j /10,1,0,0,0,0);

            }
        }
        entity.timer--;
    }

}
