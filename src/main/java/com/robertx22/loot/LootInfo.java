package com.robertx22.loot;

import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.database.world_providers.IWP;
import com.robertx22.loot.gens.BaseLootGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
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
    public int minItems = 0;
    public int maxItems = 50;

    public LootInfo setMaximum(int max) {
        this.maxItems = max;
        return this;
    }

    public LootInfo setMinimum(int min) {
        this.minItems = min;
        return this;
    }

    public LootInfo setLevel(int level) {
        this.level = level;
        return this;
    }

    public LootInfo setMulti(float multi) {
        this.multi = multi;
        return this;
    }

    public LootInfo setTier() {

        if (this.mobData != null) {
            this.tier = mobData.getTier();
        } else {
            if (killer != null) {
                if (WorldUtils.isMapWorld(killer.world)) {
                    this.tier = Load.playerMapData(killer).getTier();
                }
            }
        }
        return this;

    }

    public LootInfo(UnitData mob, UnitData player, EntityLivingBase victim,
                    EntityPlayer killer) {
        this.world = victim.world;
        this.mobData = mob;
        this.playerData = player;
        this.victim = victim;
        this.killer = killer;
        this.level = mob.getLevel();
        setTier();

    }

    public LootInfo(World theworld) {
        this.world = theworld;
    }

    public LootInfo(EntityPlayer player) {
        this.world = player.world;
        this.playerData = Load.Unit(player);
        this.level = Load.playerMapData(player).getLevel();
        setTier();

    }

    public void setup(BaseLootGen gen) {

        float chance = gen.BaseChance();

        chance *= multi;

        if (victim != null) {
            chance *= EntityTypeUtils.getLootMulti(victim);
        }

        if (killer != null) {
            chance *= Load.playerMapData(killer).getBonusLootAmount(killer);
        }

        if (world != null) {
            chance *= DimensionsContainer.INSTANCE.getConfig(world).DROP_MULTIPLIER;

            if (world.getDimension() instanceof IWP) {
                IWP iwp = (IWP) world.getDimension();
                chance *= iwp.getBonusLootMulti();
            }
        }

        if (mobData != null && playerData != null && victim != null) {
            chance = LootUtils.applyLootMultipliers(chance, mobData, playerData, victim);
        }

        if (mobData != null && playerData != null) {
            if (gen.hasLevelDistancePunishment()) {
                chance = LootUtils.ApplyLevelDistancePunishment(mobData, playerData, chance);
            }
        }

        amount = LootUtils.WhileRoll(chance);
    }

}
