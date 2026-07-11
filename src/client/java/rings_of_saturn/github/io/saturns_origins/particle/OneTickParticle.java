package rings_of_saturn.github.io.saturns_origins.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class OneTickParticle extends BillboardParticle {
    public OneTickParticle(ClientWorld clientWorld, double x, double y, double z,
                           SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, spriteProvider.getSprite(clientWorld.random));
        this.velocityMultiplier = 0.96F;
        this.velocityX = xSpeed;
        this.velocityY = ySpeed;
        this.velocityZ = zSpeed;
        this.red = MathHelper.nextFloat(this.random, 0.7176471F, 0.8745098F);
        this.green = MathHelper.nextFloat(this.random, 0.0F, 0.0F);
        this.blue = MathHelper.nextFloat(this.random, 0.8235294F, 0.9764706F);
        this.scale *= 0.75F;
        this.collidesWithWorld = false;
        this.maxAge = 1;
        this.setSprite(spriteProvider.getSprite(this.random));
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType>{
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;
        }


        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ,Random random) {
            return new OneTickParticle(world, x,y,z,this.spriteProvider,velocityX,velocityY,velocityZ);

        }
    }
}
