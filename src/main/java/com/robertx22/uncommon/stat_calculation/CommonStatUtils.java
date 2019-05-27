package com.robertx22.uncommon.stat_calculation;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.EntityLivingBase;

import java.util.List;

public class CommonStatUtils {

    public static void AddStatusEffectStats(Unit unit, int level) {

        for (StatusEffectData status : unit.statusEffects.values()) {
            List<LevelAndStats> levelsandstats = status.GetAllStats(level);
            for (LevelAndStats levelstat : levelsandstats) {
                for (StatModData data : levelstat.mods) {

                    StatData stat = unit.MyStats.get(data.GetBaseMod()
                            .GetBaseStat()
                            .GUID());
                    if (stat == null) {

                    } else {
                        stat.Add(data, level);
                    }
                }
            }
        }

    }

    public static void AddMapAffixStats(PlayerMapData.IPlayerMapData mapdata, Unit unit,
                                        int level, EntityLivingBase entity) {

        for (MapAffixData status : WorldUtils.getAllAffixesThatAffect(mapdata, entity)) {
            List<StatModData> datas = status.GetAllStats();
            for (StatModData data : datas) {
                StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().GUID());
                if (stat == null) {
                } else {
                    stat.Add(data, level);

                }
            }
        }

    }

}
