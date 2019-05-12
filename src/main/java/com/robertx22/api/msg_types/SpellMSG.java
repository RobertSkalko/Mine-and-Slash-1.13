package com.robertx22.api.msg_types;

import com.robertx22.db_lists.Spells;
import com.robertx22.spells.bases.BaseSpell;

public class SpellMSG implements MineAndSlashMSG {

    public BaseSpell spell;

    public SpellMSG(BaseSpell spell) {
        this.spell = spell;
    }

    @Override
    public final void register() {
        Spells.All.put(spell.GUID(), spell);
    }

}
