package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.mine_and_slash.database.particle_gens.NovaParticleGen;
import com.robertx22.mine_and_slash.database.particle_gens.ParticleGen;
import com.robertx22.mine_and_slash.db_lists.newregistry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistryType;

import java.util.HashMap;

public class ParticleGens implements ISlashRegistryInit {
    public static HashMap<String, ParticleGen> All = new HashMap<String, ParticleGen>() {
        {
            {
                put(new AoeProjectileParticleGen().Name(), new AoeProjectileParticleGen());
                put(new NovaParticleGen().Name(), new NovaParticleGen());

            }

        }
    };

    @Override
    public void registerAll() {
        All.values()
                .forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.PARTICLE_GEN)
                        .register(x));

    }
}
