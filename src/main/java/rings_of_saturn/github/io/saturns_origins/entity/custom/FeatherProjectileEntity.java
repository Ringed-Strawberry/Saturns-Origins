package rings_of_saturn.github.io.saturns_origins.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;
import rings_of_saturn.github.io.saturns_origins.util.ProjectileUtil;

public class FeatherProjectileEntity extends PersistentProjectileEntity {


    public FeatherProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FeatherProjectileEntity(World world) {
        super(ModEntities.FEATHER_PROJECTILE, world);
    }

    public FeatherProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.FEATHER_PROJECTILE, owner, world);
    }

    public FeatherProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.FEATHER_PROJECTILE, x, y, z, world);
    }

    @Override
    public boolean isOnFire() {
        if(ProjectileUtil.getIsUp(this)){
            return false;
        }
        return super.isOnFire();
    }

    @Override
    public void tick() {
        if (this.getOwner() == null){
            this.kill();
        }
        super.tick();
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(!ProjectileUtil.getIsUp(this))
            entity.damage(entity.getDamageSources().mobProjectile(this, (LivingEntity) this.getOwner()), 2);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if(!ProjectileUtil.getIsUp(this))
            super.onCollision(hitResult);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        if(!ProjectileUtil.getIsUp(this))
            super.onBlockCollision(state);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!ProjectileUtil.getIsUp(this))
            super.onBlockHit(blockHitResult);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
}
