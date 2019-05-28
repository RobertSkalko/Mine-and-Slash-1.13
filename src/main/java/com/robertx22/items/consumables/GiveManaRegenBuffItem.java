package com.robertx22.items.consumables;

import com.robertx22.items.consumables.bases.BaseGiveBuffItem;
import com.robertx22.mmorpg.registers.common.ConsumableRegister;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.potion_effects.all.ManaRegenPotion;

public class GiveManaRegenBuffItem extends BaseGiveBuffItem {

    @Override
    public SpellPotionBase potion() {
        return ManaRegenPotion.INSTANCE;
    }

    @Override
    public int seconds() {
        return 20;
    }

    @Override
    public String GUID() {
        return ConsumableRegister.GIVE_MANA_REGEN_BUFF_ID;
    }

}
