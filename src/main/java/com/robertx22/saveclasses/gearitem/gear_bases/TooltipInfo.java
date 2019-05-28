package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.database.MinMax;

public class TooltipInfo {

    public TooltipInfo(MinMax minmax, int level) {
        this.minmax = minmax;
        this.level = level;
    }
    
    public TooltipInfo setIsSet() {
        this.isSet = true;
        return this;
    }

    public MinMax minmax;
    public int level;
    public boolean isSet = false;

}
