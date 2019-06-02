package com.robertx22.uncommon.stat_calculation;

import com.mmorpg_libraries.curios.MyCurioUtils;
import com.robertx22.config.ModConfig;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {

        int lvl = data.getLevel();

        unit.MyStats.get("Physical Damage").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.physical_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.physical_damage_per_level
                .get());

        unit.MyStats.get("Mana").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.mana.get() + data
                .getLevel() * ModConfig.INSTANCE.PlayerBaseStats.mana_per_level.get());

        unit.MyStats.get("Energy").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.energy
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.energy_per_level.get());

        unit.MyStats.get("Mana Regen").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.mana_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.mana_regen_per_level.get());

        unit.MyStats.get("Energy Regen").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.energy_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.energy_regen_per_level.get());

        unit.MyStats.get("Health Regen").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.health_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.health_regen_per_level.get());

        unit.MyStats.get("Armor").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.armor.get() + data
                .getLevel() * ModConfig.INSTANCE.PlayerBaseStats.armor_per_level.get());

        unit.MyStats.get("Health").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.health
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.health_per_level.get());

        unit.MyStats.get("Critical Hit").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.critical_hit
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.critical_hit_per_level.get());

        unit.MyStats.get("Critical Damage").Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.critical_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.critical_damage_per_level
                .get());

    }

    public static List<GearItemData> getEquipsExcludingWeapon(EntityLivingBase entity) {

        List<ItemStack> list = new ArrayList<ItemStack>();

        for (ItemStack stack : entity.getArmorInventoryList()) {
            if (stack != null) {
                list.add(stack);
            }
        }

        if (entity instanceof EntityPlayer) {

            list.addAll(MyCurioUtils.getAllSlots((EntityPlayer) entity));

        }
        List<GearItemData> gearitems = new ArrayList<GearItemData>();

        for (ItemStack stack : list) {
            GearItemData gear = Gear.Load(stack);
            if (gear != null) {
                gearitems.add(gear);
            }

        }

        return gearitems;

    }

    public static void CountWornSets(Entity entity, List<GearItemData> gears, Unit unit) {

        unit.WornSets = new HashMap<String, Integer>();

        for (GearItemData gear : gears) {
            if (gear.set != null) {
                String set = gear.set.baseSet;

                if (unit.WornSets.containsKey(set)) {
                    unit.WornSets.put(set, unit.WornSets.get(set) + 1);
                } else {
                    unit.WornSets.put(set, 1);
                }

            }

        }

    }

    public static void AddAllSetStats(Entity entity, Unit unit, int level) {

        for (Entry<String, Integer> entry : unit.WornSets.entrySet()) {

            Set set = Sets.All.get(entry.getKey());

            for (StatMod mod : set.GetObtainedMods(unit)) {

                StatModData data = StatModData.Load(mod, set.StatPercent);

                String name = mod.GetBaseStat().GUID();
                if (unit.MyStats.containsKey(name)) {
                    unit.MyStats.get(name).Add(data, level);
                }
            }

        }

    }

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears, Unit unit,
                                       int level) {

        for (GearItemData gear : gears) {
            if (gear.level > level) {
                if (entity instanceof EntityPlayer) {
                    entity.sendMessage(SLOC.chat("too_high_level"));
                }
            } else {

                List<LevelAndStats> levelstats = gear.GetAllStats(gear.level);
                for (LevelAndStats datas : levelstats) {
                    for (StatModData data : datas.mods) {

                        StatMod mod = data.GetBaseMod();

                        if (mod == null) {
                            System.out.println(data.baseModName + " is null");
                        } else {
                            Stat stat = data.GetBaseMod().GetBaseStat();

                            if (stat != null) {
                                StatData statdata = unit.MyStats.get(stat.GUID());
                                if (statdata != null) {
                                    statdata.Add(data, datas.level);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
