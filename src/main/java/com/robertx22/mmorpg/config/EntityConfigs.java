package com.robertx22.mmorpg.config;

public class EntityConfigs {

    public EntityConfig NPC_CONFIG = new EntityConfig(0.3F, 0.3F);

    public EntityConfig MOB_CONFIG = new EntityConfig(1, 1);

    public EntityConfig ANIMAL_CONFIG = new EntityConfig(0.01F, 0.05F);

    public EntityConfig OTHER_CONFIG = new EntityConfig(0, 0);

    public static class EntityConfig {

	public EntityConfig(float loot, float exp) {
	    this.LOOT_MULTI = loot;
	    this.EXP_MULTI = exp;
	}

	public float LOOT_MULTI = 0;

	public float EXP_MULTI = 0;

    }
}
