package com.robertx22.uncommon.stat_calculation;

import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_types.core_stats.ICoreStat;
import com.robertx22.database.stats.stat_types.core_stats.IPreCoreStat;
import com.robertx22.db_lists.Stats;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.interfaces.IStatConversion;
import com.robertx22.uncommon.interfaces.IStatTransfer;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;

import java.util.List;

public class CommonStatUtils {

    public static void addCustomStats(UnitData data, Unit unit, int level) {
        for (StatModData stat : data.getCustomStats().stats.values()) {
            stat.useOnPlayer(data);
        }
    }

    public static void AddStatusEffectStats(Unit unit, int level) {

        for (StatusEffectData status : unit.statusEffects.values()) {
            List<LevelAndStats> levelsandstats = status.GetAllStats(level);
            for (LevelAndStats levelstat : levelsandstats) {
                for (StatModData data : levelstat.mods) {

                    StatData stat = unit.MyStats.get(data.getStatMod()
                            .GetBaseStat()
                            .GUID());
                    if (stat == null) {

                    } else {
                        data.Add(stat, level);
                    }
                }
            }
        }

    }

    public static void CalcTraitsAndCoreStats(UnitData unit) {

        Unit theunit = unit.getUnit();

        for (IPreCoreStat core : Stats.allPreGenMapStatLists.get(IPreCoreStat.class)) {
            StatData statdata = theunit.MyStats.get(core.GUID());
            if (statdata.Value > 0) {
                core.addToCoreStats(unit, statdata);
            }
        }
        for (ICoreStat core : Stats.allPreGenMapStatLists.get(ICoreStat.class)) {
            StatData statdata = theunit.MyStats.get(core.GUID());
            if (statdata.Value > 0) {
                core.addToOtherStats(unit, statdata);
            }
        }
        for (Trait trait : Stats.allPreGenMapStatLists.get(Trait.class)) {
            StatData statdata = theunit.MyStats.get(trait.GUID());
            if (statdata.Value > 0) {
                trait.TryAffectOtherStats(unit, statdata);
            }
        }

    }

    /**
     * A unit copy is needed so there's no randomness to stat transfers and
     * conversions. All changes are based on old copy but applied to the unit that's
     * used
     */
    public static void CalcStatConversionsAndTransfers(Unit copy, Unit unit) {

        for (IStatConversion core : Stats.allPreGenMapStatLists.get(IStatConversion.class)) {
            StatData statdata = copy.MyStats.get(core.GUID());
            if (statdata.Value > 0) {
                core.convertStats(copy, unit, copy.MyStats.get(core.GUID()));
            }
        }
        for (IStatTransfer core : Stats.allPreGenMapStatLists.get(IStatTransfer.class)) {
            StatData statdata = copy.MyStats.get(core.GUID());
            if (statdata.Value > 0) {
                core.transferStats(copy, unit, copy.MyStats.get(core.GUID()));
            }
        }

    }

    public static void AddMapAffixStats(PlayerMapData.IPlayerMapData mapdata, Unit unit,
                                        int level, LivingEntity entity) {

        for (MapAffixData status : WorldUtils.getAllAffixesThatAffect(mapdata, entity)) {
            List<StatModData> datas = status.GetAllStats();
            for (StatModData data : datas) {
                StatData stat = unit.MyStats.get(data.getStatMod().GetBaseStat().GUID());
                if (stat == null) {
                } else {

                    data.Add(stat, level);

                }
            }
        }

    }

}
