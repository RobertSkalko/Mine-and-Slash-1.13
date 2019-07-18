package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.UnknownStat;
import com.robertx22.mine_and_slash.db_lists.StatMods;
import com.robertx22.mine_and_slash.loot.StatGen;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
        Add(unit.getUnit().getStat(this.getStatMod().GetBaseStat()), unit.getLevel());
    }

    public void useOnPlayer(UnitData unit, int level) {
        Add(unit.getUnit().getStat(this.getStatMod().GetBaseStat()), level);
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

    public static List<String> statmodsAlerted = new ArrayList<>();

    public StatMod getStatMod() {

        if (baseModName == null) {
            return new UnknownStatMod();
        }
        if (StatMods.All.containsKey(baseModName)) {
            return StatMods.All.get(baseModName).multi(multiplier);
        } else {
            if (statmodsAlerted.contains(baseModName) == false) {
                System.out.println("Mine and Slash: " + baseModName + " stat mod doesn't exist. This is either a removed mod, or robertx22 forgot to include it in the list.");
                statmodsAlerted.add(baseModName);
            }
            return new UnknownStatMod();
        }

    }

    static class UnknownStatMod extends StatMod {

        @Override
        public Stat GetBaseStat() {
            return new UnknownStat();
        }

        @Override
        public float Min() {
            return 0;
        }

        @Override
        public float Max() {
            return 0;
        }

        @Override
        public StatTypes Type() {
            return StatTypes.Flat;
        }
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