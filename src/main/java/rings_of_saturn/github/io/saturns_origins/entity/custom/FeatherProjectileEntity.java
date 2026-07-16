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

import static rings_of_saturn.github.io.saturns_origins.util.ValuesUtil.FEATHER_DAMAGE;

public class FeatherProjectileEntity extends PersistentProjectileEntity {


    public FeatherProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FeatherProjectileEntity(World world) {
        super(ModEntities.FEATHER_PROJECTILE, world);
    }

    public FeatherProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.FEATHER_PROJECTILE, world);
        this.setOwner(owner);
        this.setPosition(owner.getEyePos());
        this.setWorld(owner.getEntityWorld());
    }

    public FeatherProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.FEATHER_PROJECTILE, world);
        this.setPos(x,y,z);
    }

    @Override
    public boolean isOnFire() {
        return super.isOnFire();
    }

    @Override
    public void tick() {
        if (this.getOwner() == null && !this.getEntityWorld().isClient()){
            this.kill(this.getEntityWorld().getServer().getWorld(this.getEntityWorld().getRegistryKey()));
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
        if(!this.getEntityWorld().isClient())
            entity.damage(this.getEntityWorld().getServer().getWorld(this.getEntityWorld().getRegistryKey()), entity.getDamageSources().mobProjectile(this, (LivingEntity) this.getOwner()), FEATHER_DAMAGE);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModEntities.FEATHER_UP_PROJECTILE_ITEM);
    }

}
