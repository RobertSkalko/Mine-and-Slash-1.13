package com.robertx22.uncommon.stat_calculation;

import com.mmorpg_libraries.curios.MyCurioUtils;
import com.robertx22.config.ModConfig;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.defense.Armor;
import com.robertx22.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stats.stat_types.offense.SpellDamage;
import com.robertx22.database.stats.stat_types.resources.*;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.WornSetsContainerData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.localization.Chats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {

        int lvl = data.getLevel();

        unit.MyStats.get(PhysicalDamage.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.physical_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.physical_damage_per_level
                .get());

        unit.MyStats.get(Mana.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.mana
                .get() + data.getLevel() * ModConfig.INSTANCE.PlayerBaseStats.mana_per_level
                .get());

        unit.MyStats.get(Energy.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.energy
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.energy_per_level.get());

        unit.MyStats.get(ManaRegen.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.mana_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.mana_regen_per_level.get());

        unit.MyStats.get(EnergyRegen.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.energy_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.energy_regen_per_level.get());

        unit.MyStats.get(HealthRegen.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.health_regen
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.health_regen_per_level.get());

        unit.MyStats.get(Armor.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.armor
                .get() + data.getLevel() * ModConfig.INSTANCE.PlayerBaseStats.armor_per_level
                .get());

        unit.MyStats.get(Health.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.health
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.health_per_level.get());

        unit.MyStats.get(CriticalHit.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.critical_hit
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.critical_hit_per_level.get());

        unit.MyStats.get(CriticalDamage.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.critical_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.critical_damage_per_level
                .get());

        unit.MyStats.get(SpellDamage.GUID).Flat += (int) (ModConfig.INSTANCE.PlayerBaseStats.spell_damage
                .get() + lvl * ModConfig.INSTANCE.PlayerBaseStats.spell_damage_per_level.get());

    }

    public static List<GearItemData> getEquipsExcludingWeapon(LivingEntity entity) {

        List<ItemStack> list = new ArrayList<ItemStack>();

        for (ItemStack stack : entity.getArmorInventoryList()) {
            if (stack != null) {
                list.add(stack);
            }
        }

        if (entity instanceof PlayerEntity) {

            list.addAll(MyCurioUtils.getAllSlots((PlayerEntity) entity));

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

        unit.wornSets = new WornSetsContainerData();

        for (GearItemData gear : gears) {
            unit.wornSets.addSet(gear);
        }

    }

    public static void AddAllSetStats(Entity entity, UnitData data, Unit unit,
                                      int level) {

        unit.wornSets.AddAllSetStats(data);

    }

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears, Unit unit,
                                       int level) {

        boolean gearTooHighLevel = false;

        for (GearItemData gear : gears) {
            if (gear.level > level) {
                gearTooHighLevel = true;

            } else {

                List<LevelAndStats> levelstats = gear.GetAllStats(gear.level);
                for (LevelAndStats datas : levelstats) {
                    for (StatModData data : datas.mods) {

                        StatMod mod = data.getStatMod();

                        if (mod == null) {
                            //  System.out.println(data.baseModName + " is null");
                        } else {
                            Stat stat = data.getStatMod().GetBaseStat();

                            if (stat != null) {
                                StatData statdata = unit.MyStats.get(stat.GUID());
                                if (statdata != null) {
                                    data.Add(statdata, datas.level);
                                }
                            }
                        }
                    }

                }
            }
        }

        if (gearTooHighLevel) {
            if (entity instanceof PlayerEntity) {
                entity.sendMessage(Chats.A_Piece_of_gear_is_too_high_level_for_you.locName());
            }
        }

    }

}
