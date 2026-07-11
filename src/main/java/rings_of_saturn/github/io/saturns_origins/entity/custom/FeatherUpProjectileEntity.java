package rings_of_saturn.github.io.saturns_origins.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import rings_of_saturn.github.io.saturns_origins.entity.ModEntities;

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
        if (this.getOwner() == null){
            this.kill(this.getEntityWorld().getServer().getWorld(this.getEntityWorld().getRegistryKey()));
        }
        super.tick();
    }

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
    protected Item getDefaultItem() {
        return ModEntities.FEATHER_UP_PROJECTILE_ITEM;
    }
}
