package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.StatMods;
import com.robertx22.loot.StatGen;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.StatTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Storable
public class StatModData implements ITooltipString {

    public StatModData() {

    }

    public static StatModData NewRandom(ItemRarity rar, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = StatGen.GenPercent(rar);
        data.multiplier = mod.multiplier;

        return data;
    }

    public static StatModData Load(StatMod mod, int percent) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = percent;
        data.multiplier = mod.multiplier;

        return data;
    }

    public void useOnPlayer(UnitData unit) {
        String guid = this.getStatMod().GetBaseStat().GUID();
        if (unit.getUnit().MyStats.containsKey(guid)) {
            Add(unit.getUnit().MyStats.get(guid), unit.getLevel());
        }
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int perc) {
        this.percent = perc;
    }

    @Store
    private float multiplier = 1F;

    @Store
    private StatTypes type;

    @Store
    private int percent;

    @Store
    private String baseModName;

    public StatMod getStatMod() {
        return StatMods.All.get(baseModName).multi(multiplier);
    }

    public float GetActualVal(int level) {

        StatMod mod = getStatMod();

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

        if (Math.abs(val) < 10) {
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
            return getStatMod().GetBaseStat().getTooltipList(info, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(new StringTextComponent(""));
    }

    public void Add(StatData data, int level) {

        if (type == StatTypes.Flat) {
            data.Flat += GetActualVal(level);
        } else if (type == StatTypes.Percent) {
            data.Percent += GetActualVal(level);
        } else if (type == StatTypes.Multi) {
            data.Multi += GetActualVal(level);

        }

    }
}