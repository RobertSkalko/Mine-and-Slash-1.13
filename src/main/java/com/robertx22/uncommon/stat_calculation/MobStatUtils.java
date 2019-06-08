package com.robertx22.uncommon.stat_calculation;

import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stats.stat_types.defense.Armor;
import com.robertx22.database.stats.stat_types.generated.ElementalResist;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.StatusEffects;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class MobStatUtils {

    static int spelldmg = 8;
    static int spellresist = 4;

    public static void increaseMobStatsPerTier(UnitData mobdata, Unit unit) {

        for (StatData data : unit.MyStats.values()) {
            data.Flat = data.Flat * mobdata.getStatMultiplierIncreaseByTier();
        }

    }

    public static void worldMultiplierStats(World world, Unit unit) {

        for (StatData stat : unit.MyStats.values()) {
            stat.Flat *= DimensionsContainer.INSTANCE.getConfig(world).MOB_STRENGTH_MULTIPLIER;
        }

    }

    public static void AddMobcStats(UnitData unitdata, int level) {

        MobRarity rar = Rarities.Mobs.get(unitdata.getRarity());
        Unit unit = unitdata.getUnit();

        unit.MyStats.get(Armor.GUID).Flat += 10 * level * rar.StatMultiplier();
        unit.MyStats.get(CriticalHit.GUID).Flat += 5 * rar.StatMultiplier();
        unit.MyStats.get(CriticalDamage.GUID).Flat += 5 * rar.StatMultiplier();

        for (Elements element : Elements.getAllSingleElements()) {

            unit.MyStats.get(new ElementalResist(element).GUID()).Flat += spellresist * level * rar
                    .StatMultiplier();
            unit.MyStats.get(new ElementalSpellDamage(element).GUID()).Flat += spelldmg * level * rar
                    .StatMultiplier();

        }

    }

    // this apparently takes 60 ms
    public static void AddRandomMobStatusEffects(LivingEntity entity, Unit unit,
                                                 MobRarity rarity) {

        int max = rarity.MaxMobEffects();

        if (max > 0) {
            if (max > StatusEffects.All.values().size()) {
                System.out.println("ERROR! Can't have more unique effects than there are effects!");
                max = StatusEffects.All.values().size() - 1;
            }

            int amount = RandomUtils.RandomRange(0, max);

            while (amount > 0) {

                BaseStatusEffect effect = null;

                while (effect == null || unit.statusEffects.containsKey(effect.GUID())) {
                    effect = RandomUtils.weightedRandom(StatusEffects.All.values());
                }
                amount--;
                unit.statusEffects.put(effect.GUID(), new StatusEffectData(effect));

            }
        }
    }
}
