package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface ICommonDataItem<R extends Rarity> extends IRarity<R>, ITiered, ISalvagable, ITooltip, IType, ILevel {

    @Override
    default boolean isSalvagable(SalvageContext context) {
        if (context == SalvageContext.AUTO_SALVAGE_BAG) {
            return this.isUnique() == false;

        }
        return true;
    }

}
