package rings_of_saturn.github.io.saturns_origins.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rings_of_saturn.github.io.saturns_origins.SaturnsOrigins;

public class ModParticles {
    public static final SimpleParticleType ONE_TICK_PARTICLE = registerParticle("one_tick_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType){
        return Registry.register(Registries.PARTICLE_TYPE,Identifier.of(SaturnsOrigins.MOD_ID, name), particleType);
    }

    public static void registerParticles(){
    }
}
