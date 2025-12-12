package rings_of_saturn.github.io.saturns_origins.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class OneTickParticle extends SpriteBillboardParticle {
    public OneTickParticle(ClientWorld clientWorld, double x, double y, double z,
                           SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
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
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType>{
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;
        }


        @Override
        public @Nullable Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new OneTickParticle(world, x,y,z,this.spriteProvider,velocityX,velocityY,velocityZ);

        }
    }
}
