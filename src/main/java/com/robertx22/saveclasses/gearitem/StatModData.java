package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.StatMods;
import com.robertx22.loot.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.StatTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Storable
public class StatModData implements ITooltipString {

    public StatModData() {

    }

    public static StatModData NewRandom(GearItemData gear, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = StatGen.GenPercent(gear.GetRarity());

        return data;
    }

    public static StatModData NewRandom(RuneRarity rar, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = StatGen.GenPercent(rar);

        return data;
    }

    public static StatModData NewStatusEffect(int percent, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = percent;

        return data;
    }

    public static StatModData Load(StatMod mod, int percent) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = percent;

        return data;
    }

    public void useOnPlayer(UnitData unit) {
        String guid = this.GetBaseMod().GetBaseStat().GUID();
        if (unit.getUnit().MyStats.containsKey(guid)) {
            unit.getUnit().MyStats.get(guid).Add(this, unit.getLevel());
        }
    }

    @Store
    public StatTypes type;

    @Store
    public int percent;

    @Store
    public String baseModName;

    public StatMod GetBaseMod() {
        return StatMods.All.get(baseModName);
    }

    public float GetActualVal(int level) {

        StatMod mod = GetBaseMod();

        Stat stat = mod.GetBaseStat();

        float val = mod.GetFloatByPercent(percent);

        if (stat.ScalesToLevel() && mod.Type().equals(StatTypes.Flat)) {
            val *= level;
        }

        return val;

    }

    public String printValue(int level) {

        float val = GetActualVal(level);

        DecimalFormat format = new DecimalFormat();

        if (val < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {

            int intval = (int) val;
            return intval + "";

        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        try {
            return GetBaseMod().GetBaseStat().getTooltipList(info, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(new TextComponentString(""));
    }

}