package com.robertx22;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.datasaving.Spell;
import net.minecraft.item.ItemStack;

public class LootBagItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null) {
            return true;
        }

        SpellItemData spell = Spell.Load(stack);

        if (spell != null) {
            return true;
        }

        RuneItemData rune = Rune.Load(stack);
        if (rune != null) {
            return true;

        }
        return false;
    }
}
