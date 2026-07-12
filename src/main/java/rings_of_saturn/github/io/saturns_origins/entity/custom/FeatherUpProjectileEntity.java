package rings_of_saturn.github.io.saturns_origins.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;

import java.util.List;

public class FeatherUpProjectileEntity extends ThrownItemEntity {


    public FeatherUpProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FeatherUpProjectileEntity(World world) {
        super(ModEntities.FEATHER_UP_PROJECTILE, world);
    }

    public FeatherUpProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.FEATHER_UP_PROJECTILE, world);
        this.setOwner(owner);
        this.setPosition(owner.getEyePos());
        this.setWorld(owner.getEntityWorld());
    }

    public FeatherUpProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.FEATHER_UP_PROJECTILE, world);
        this.setPos(x,y,z);
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void tick() {
        if (this.getOwner() == null && !this.getEntityWorld().isClient()){
            this.kill(this.getEntityWorld().getServer().getWorld(this.getEntityWorld().getRegistryKey()));
        }
        super.tick();
    }

    //region Collision

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
    }

    @Override
    protected void onCollision(HitResult hitResult) {
    }

    @Override
    protected void onBlockCollision(BlockState state) {
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {

    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }

    @Override
    public boolean collides(Vec3d oldPos, Vec3d newPos, List<Box> boxes) {
        return false;
    }

    @Override
    public void onBubbleColumnCollision(boolean drag) {

    }

    @Override
    protected boolean shouldTickBlockCollision() {
        return false;
    }

    @Override
    public boolean collidesWithStateAtPos(BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected boolean hasCollidedSoftly(Vec3d adjustedMovement) {
        return false;
    }

    @Override
    public boolean collidesWithFluid(FluidState state, BlockPos fluidPos, Vec3d oldPos, Vec3d newPos) {
        return false;
    }

    @Override
    protected void tickBlockCollision() {
    }

    @Override
    public boolean isCollidable(@Nullable Entity entity) {
        return false;
    }

    @Override
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return false;
    }

    @Override
    public void onBubbleColumnSurfaceCollision(boolean drag, BlockPos pos) {
    }

    @Override
    public void tickBlockCollision(Vec3d lastRenderPos, Vec3d pos) {

    }
    //endregion

    @Override
    protected Item getDefaultItem() {
        return ModEntities.FEATHER_UP_PROJECTILE_ITEM;
    }
}
