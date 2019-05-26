package com.robertx22.loot;

import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.loot.gens.BaseLootGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LootInfo {

    public int amount = 0;
    public int tier = 0;
    public int level = 1;

    public UnitData mobData;
    public UnitData playerData;
    public EntityLivingBase victim;
    public EntityPlayer killer;
    public World world;
    public float multi = 1;

    public LootInfo(UnitData mob, UnitData player, EntityLivingBase victim,
                    EntityPlayer killer) {
        this.tier = Load.playerMapData(killer).getTier();
        this.world = victim.world;
        this.mobData = mob;
        this.playerData = player;
        this.victim = victim;
        this.killer = killer;
        this.level = mob.getLevel();

    }

    public LootInfo(World theworld, int level, float multi) {
        this.multi = multi;
        this.world = theworld;
        this.level = level;
    }

    public LootInfo(EntityPlayer player, float multi) {
        this.multi = multi;
        this.world = player.world;
        this.playerData = Load.Unit(player);
        this.level = Load.playerMapData(player).getLevel();
        this.tier = Load.playerMapData(player).getTier();
    }

    public void setup(BaseLootGen gen) {

        float chance = gen.BaseChance();

        chance *= multi;

        if (victim != null) {
            chance *= EntityTypeUtils.getLootMulti(victim);
        }

        if (world != null) {
            chance *= DimensionsContainer.INSTANCE.getConfig(world).DROP_MULTIPLIER;
        }

        if (mobData != null && playerData != null) {
            if (gen.hasLevelDistancePunishment()) {
                chance = LootUtils.ApplyLevelDistancePunishment(mobData, playerData, chance);
            }
        }

        if (mobData != null && playerData != null && victim != null) {
            chance = LootUtils.applyLootMultipliers(chance, mobData, playerData, victim);
        }

        amount = LootUtils.WhileRoll(chance);
    }

}
