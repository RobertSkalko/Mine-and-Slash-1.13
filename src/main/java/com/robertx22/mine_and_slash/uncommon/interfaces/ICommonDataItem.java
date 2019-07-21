package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface ICommonDataItem<R extends Rarity> extends IRarity<R>, ITiered, ISalvagable, ITooltip {
}
