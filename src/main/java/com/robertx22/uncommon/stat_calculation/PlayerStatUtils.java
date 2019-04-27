package com.robertx22.uncommon.stat_calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.db_lists.Sets;
import com.robertx22.mmorpg.config.StatConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IStatConversion;
import com.robertx22.uncommon.interfaces.IStatTransfer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.CuriosAPI.FinderData;
import top.theillusivec4.curios.api.inventory.CurioStackHandler;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {

	unit.MyStats.get("Physical Damage").Flat += (int) (StatConfig.INSTANCE.physical_damage.get()
		+ data.getLevel() * StatConfig.INSTANCE.physical_damage_per_level.get());

	unit.MyStats.get("Mana").Flat += (int) (StatConfig.INSTANCE.mana.get()
		+ data.getLevel() * StatConfig.INSTANCE.mana_per_level.get());

	unit.MyStats.get("Energy").Flat += (int) (StatConfig.INSTANCE.energy.get()
		+ data.getLevel() * StatConfig.INSTANCE.energy_per_level.get());

	unit.MyStats.get("Mana Regen").Flat += (int) (StatConfig.INSTANCE.mana_regen.get()
		+ data.getLevel() * StatConfig.INSTANCE.mana_regen_per_level.get());

	unit.MyStats.get("Energy Regen").Flat += (int) (StatConfig.INSTANCE.energy_regen.get()
		+ data.getLevel() * StatConfig.INSTANCE.energy_regen_per_level.get());

	unit.MyStats.get("Health Regen").Flat += (int) (StatConfig.INSTANCE.health_regen.get()
		+ data.getLevel() * StatConfig.INSTANCE.health_regen_per_level.get());

	unit.MyStats.get("Armor").Flat += (int) (StatConfig.INSTANCE.armor.get()
		+ data.getLevel() * StatConfig.INSTANCE.armor_per_level.get());

	unit.MyStats.get("Health").Flat += (int) (StatConfig.INSTANCE.health.get()
		+ data.getLevel() * StatConfig.INSTANCE.health_per_level.get());

	unit.MyStats.get("Critical Hit").Flat += (int) (StatConfig.INSTANCE.critical_hit.get()
		+ data.getLevel() * StatConfig.INSTANCE.critical_hit_per_level.get());

	unit.MyStats.get("Critical Damage").Flat += (int) (StatConfig.INSTANCE.critical_damage.get()
		+ data.getLevel() * StatConfig.INSTANCE.critical_damage_per_level.get());

    }

    public static void CalcTraits(UnitData unit) {

	for (StatData statdata : unit.getUnit().MyStats.values()) {
	    Stat stat = statdata.GetStat();
	    if (statdata.Value > 0) {
		if (stat instanceof Trait) {
		    Trait affects = (Trait) stat;
		    affects.TryAffectOtherStats(unit);

		}
	    }
	}

    }

    /**
     * A unit copy is needed so there's no randomness to stat transfers and
     * conversions. All changes are based on old copy but applied to the unit that's
     * used
     */
    public static void CalcStatConversionsAndTransfers(Unit copy, Unit unit) {

	for (StatData statdata : copy.MyStats.values()) {

	    Stat stat = statdata.GetStat();
	    if (statdata.Value > 0) {
		if (stat instanceof IStatConversion) {
		    IStatConversion affects = (IStatConversion) stat;
		    affects.convertStats(copy, unit, statdata);
		}
		if (stat instanceof IStatTransfer) {
		    IStatTransfer affects = (IStatTransfer) stat;
		    affects.transferStats(copy, unit, statdata);
		}
	    }

	}

    }

    public static List<GearItemData> getEquipsExcludingWeapon(EntityLivingBase entity) {

	List<ItemStack> list = new ArrayList<ItemStack>();

	for (ItemStack stack : entity.getArmorInventoryList()) {
	    if (stack != null) {
		list.add(stack);
	    }
	}

	if (entity instanceof EntityPlayer) {

	    @SuppressWarnings("unused")
	    FinderData found = CuriosAPI.getCuriosHandler(entity).map(handler -> {
		for (String id : handler.getCurioMap().keySet()) {
		    CurioStackHandler stackHandler = handler.getStackHandler(id);

		    if (stackHandler != null) {

			for (int i = 0; i < stackHandler.getSlots(); i++) {
			    ItemStack stack = stackHandler.getStackInSlot(i);

			    if (!stack.isEmpty()) {
				list.add(stack);
			    }
			}
		    }
		}
		return new FinderData("", 0, ItemStack.EMPTY);
	    }).orElse(new FinderData("", 0, ItemStack.EMPTY));

	    //

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

		if (gear.set != null) {
		    if (unit.WornSets.containsKey(set)) {
			unit.WornSets.put(set, unit.WornSets.get(set) + 1);
		    } else {
			unit.WornSets.put(set, 1);
		    }
		}
	    }

	}

    }

    public static void AddAllSetStats(Entity entity, Unit unit, int level) {

	for (Entry<String, Integer> entry : unit.WornSets.entrySet()) {

	    Set set = Sets.All.get(entry.getKey());

	    for (StatMod mod : set.GetObtainedMods(unit)) {

		StatModData data = StatModData.Load(mod, set.StatPercent);

		String name = mod.GetBaseStat().Guid();
		if (unit.MyStats.containsKey(name)) {
		    unit.MyStats.get(name).Add(data, level);
		}
	    }

	}

    }

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears, Unit unit, int level) {

	for (GearItemData gear : gears) {
	    if (gear.level > level) {
		if (entity instanceof EntityPlayer) {
		    entity.sendMessage(SLOC.chat("too_high_level"));
		}
	    } else {

		List<LevelAndStats> levelstats = gear.GetAllStats(gear.level);
		for (LevelAndStats datas : levelstats) {
		    for (StatModData data : datas.mods) {
			StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Guid());
			if (stat == null) {
			    System.out.println(
				    "Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Guid());
			} else {
			    stat.Add(data, datas.level);

			}
		    }
		}
	    }
	}
    }

}