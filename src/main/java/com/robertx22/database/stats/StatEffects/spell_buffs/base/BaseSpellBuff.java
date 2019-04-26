package com.robertx22.database.stats.StatEffects.spell_buffs.base;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.uncommon.interfaces.IStatEffect;

public abstract class BaseSpellBuff implements IStatEffect {

    @Override
    public int GetPriority() {
	return 0;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Source;
    }

    public abstract SpellType typeOfSpellAffected();

    public abstract SpellBuffType buffType();

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof SpellBuffEffect) {

		SpellBuffEffect spell = (SpellBuffEffect) Effect;

		if (spell.buffable.getBuffType().equals(this.typeOfSpellAffected())) {

		    spell.setBuff(this.buffType());
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
