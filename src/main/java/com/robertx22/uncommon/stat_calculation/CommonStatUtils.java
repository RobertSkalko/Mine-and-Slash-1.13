package com.robertx22.uncommon.stat_calculation;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.capability.WorldUtils;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
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
                        System.out.println("Error! can't load a stat called: " + data.GetBaseMod()
                                .GetBaseStat()
                                .GUID());
                    } else {
                        stat.Add(data, level);
                    }
                }
            }
        }

    }

    public static Unit addMapAffixes(EntityPlayer entity, Unit unit, UnitData endata) {

        PlayerMapData.IPlayerMapData data = Load.playerMapData(entity);

        unit.mapAffixes = new HashMap<String, MapAffixData>();

        if (WorldUtils.isMapWorld(entity.world)) {

            AffectedEntities affected = null;

            if (entity instanceof EntityPlayer) {
                affected = AffectedEntities.Players;
            } else {
                affected = AffectedEntities.Mobs;
            }

            for (MapAffixData affix : data.getMap().getAllAffixesThatAffect(affected)) {
                unit.mapAffixes.put(affix.GUID, affix);
            }

            for (MapAffixData affix : data.getMap()
                    .getAllAffixesThatAffect(AffectedEntities.All)) {
                unit.mapAffixes.put(affix.GUID, affix);
            }

        }

        return unit;
    }

    public static void AddMapAffixStats(Unit unit, int level) {

        for (MapAffixData status : unit.mapAffixes.values()) {
            List<StatModData> datas = status.GetAllStats();
            for (StatModData data : datas) {
                StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().GUID());
                if (stat == null) {
                    System.out.println("Error! can't load a stat called: " + data.GetBaseMod()
                            .GetBaseStat()
                            .GUID());
                } else {
                    stat.Add(data, level);

                }
            }
        }

    }

}
