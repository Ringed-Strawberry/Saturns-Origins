package rings_of_saturn.github.io.saturns_origins.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rings_of_saturn.github.io.saturns_origins.block.BlockGen;
import rings_of_saturn.github.io.saturns_origins.particle.ModParticles;

public class MagicPlatformBlock extends Block {
    public static final IntProperty TIMER = IntProperty.of("timer",0,100);
    public MagicPlatformBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(TIMER, 100));

    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TIMER);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos, state.with(TIMER,state.get(TIMER) -1 ));
        if (!world.isClient){
            ((ServerWorld) world).spawnParticles(ModParticles.ONE_TICK_PARTICLE,pos.getX()+0.5,pos.getY()+1.0,pos.getZ()+0.5,1,0,0,0,0);
            world.scheduleBlockTick(pos, BlockGen.MAGICPLATFORMBLOCK, 1);
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random){
        world.scheduleBlockTick(pos, BlockGen.MAGICPLATFORMBLOCK,1);
        if (state.get(TIMER) <= 0){
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            return;
        }


        world.spawnParticles(ModParticles.ONE_TICK_PARTICLE, pos.getX()+0.5,pos.getY()+1.0,pos.getZ()+0.5,1,0,0,0,0);
        world.setBlockState(pos, state.with(TIMER,state.get(TIMER) -1 ));
        super.scheduledTick(state,world,pos,random);
    }
//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, ShapeContext context) {
//        return VoxelShapes.empty();
//    }
}
