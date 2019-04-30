package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.saveclasses.GearItemData;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface ITooltipList {

    public abstract List<ITextComponent> GetTooltipString(GearItemData gear);
}


