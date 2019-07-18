package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.UnknownStat;
import com.robertx22.mine_and_slash.db_lists.initializers.Stats;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.text.DecimalFormat;

@Storable
public class StatData {

    public StatData() {

    }

    public StatData(Stat stat) {
        this.Name = stat.GUID();
    }

    public Stat GetStat() {
        if (Stats.All.containsKey(Name)) {
            return Stats.All.get(Name);
        }
        /*
         * System.out.println(
         * "No such stat, this is probably a legacy item and the stat has been renamed or removed: "
         * + Name);
         *
         */
        return Stats.All.get(UnknownStat.GUID);
    }

    @Store
    public String Name;

    public float Flat = 0;

    public float Percent = 0;

    public float Multi = 0;

    @Store
    public float Value = 0;

    public void Clear() {
        Flat = 0;
        Percent = 0;
        Multi = 0;

    }

    public String formattedValue() {

        float val = Value;

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

    }

    public float getMultiplier() {
        return 1 + Value / 100;
    }

}
