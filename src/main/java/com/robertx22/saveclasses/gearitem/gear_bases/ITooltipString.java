package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.database.MinMax;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface ITooltipString {

    public abstract List<ITextComponent> GetTooltipString(MinMax minmax, int level,
                                                          boolean addPrefix);

}
