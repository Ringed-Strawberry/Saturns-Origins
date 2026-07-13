package rings_of_saturn.github.io.saturns_origins.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;


public class FeatherUpProjectileEntity extends Entity implements FlyingItemEntity {

    private final ItemStack stack;

    private static final TrackedData<Integer> SWARM_INDEX =
            DataTracker.registerData(FeatherUpProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> SWARM_CHARGE =
            DataTracker.registerData(FeatherUpProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public FeatherUpProjectileEntity(EntityType<?> type, World world) {
        super(type, world);
        this.stack = new ItemStack(ModEntities.FEATHER_UP_PROJECTILE_ITEM);
        this.noClip = true;
        setNoGravity(true);
        setInvulnerable(true);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(SWARM_INDEX, 0);
        builder.add(SWARM_CHARGE, 0);
    }

    public int getSwarmIndex() {
        return this.getDataTracker().get(SWARM_INDEX);
    }

    public int getSwarmCharge() {
        return this.getDataTracker().get(SWARM_CHARGE);
    }

    public void setSwarmIndex(int index) {
        this.getDataTracker().set(SWARM_INDEX, index);
    }

    public void setSwarmCharge(int charge) {
        this.getDataTracker().set(SWARM_CHARGE, charge);
    }



    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public void updatePositionAndAngles(double x, double y, double z, float yaw, float pitch) {
        this.updateLastPosition();
        super.updatePositionAndAngles(x, y, z, yaw, pitch);
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }


    @Override
    protected void readCustomData(ReadView view) {

    }

    @Override
    protected void writeCustomData(WriteView view) {

    }

    @Override
    public ItemStack getStack() {
        return stack;
    }
}
