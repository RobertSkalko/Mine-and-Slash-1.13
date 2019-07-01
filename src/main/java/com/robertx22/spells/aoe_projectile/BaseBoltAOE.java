package com.robertx22.spells.aoe_projectile;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.projectile.BaseBolt;
import com.robertx22.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseBoltAOE extends BaseBolt {

    @Override
    public int ManaCost() {
        return 25;
    }

    @Override
    public int BaseValue() {
        return 4;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("aoe_spell_explosion");

    }

}
