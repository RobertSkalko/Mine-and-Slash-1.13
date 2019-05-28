package com.robertx22.saveclasses.gearitem.gear_bases;

import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface ITooltipString {

    public abstract List<ITextComponent> GetTooltipString(TooltipInfo info);

}
