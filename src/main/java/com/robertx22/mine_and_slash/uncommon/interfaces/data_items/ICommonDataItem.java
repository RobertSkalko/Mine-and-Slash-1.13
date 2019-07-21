package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import net.minecraft.item.ItemStack;

public interface ICommonDataItem<R extends Rarity> extends IRarity<R>, ITiered, ISalvagable, ITooltip, IType, ILevel {

    @Override
    default boolean isSalvagable(SalvageContext context) {
        if (context == SalvageContext.AUTO_SALVAGE_BAG) {
            return this.isUnique() == false;

        }
        return true;
    }

    static ICommonDataItem load(ItemStack stack) {

        GearItemData gear = Gear.Load(stack);
        if (gear != null) {
            return gear;
        }

        SpellItemData spell = Spell.Load(stack);
        if (spell != null) {
            return spell;
        }

        MapItemData map = Map.Load(stack);
        if (map != null) {
            return map;
        }

        RuneItemData rune = Rune.Load(stack);
        if (rune != null) {
            return rune;
        }

        return null;
    }

}
