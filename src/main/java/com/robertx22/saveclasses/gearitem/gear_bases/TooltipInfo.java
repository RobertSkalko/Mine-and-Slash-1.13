package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.database.MinMax;
import com.robertx22.uncommon.capability.EntityData;

public class TooltipInfo {

    public TooltipInfo(EntityData.UnitData unitdata, MinMax minmax, int level) {
        this.minmax = minmax;
        this.level = level;
        this.unitdata = unitdata;
    }

    public TooltipInfo setIsSet() {
        this.isSet = true;
        return this;
    }

    public EntityData.UnitData unitdata;
    public MinMax minmax;
    public int level;
    public boolean isSet = false;

    public TooltipInfo withLevel(int level) {

        TooltipInfo info = null;
        try {
            info = (TooltipInfo) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        info.level = level;

        return info;

    }

}
