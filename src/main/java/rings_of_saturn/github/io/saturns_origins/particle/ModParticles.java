package rings_of_saturn.github.io.saturns_origins.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.SaturnsOrigins;

public class ModParticles {
    public static final DefaultParticleType ONE_TICK_PARTICLE = registerParticle("one_tick_particle", FabricParticleTypes.simple());

    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType){
        return Registry.register(Registries.PARTICLE_TYPE,Identifier.of(SaturnsOrigins.MOD_ID, name), particleType);
    }

    public static void resgisterParticles(){
    }
}
