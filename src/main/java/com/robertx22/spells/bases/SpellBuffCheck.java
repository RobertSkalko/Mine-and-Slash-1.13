package com.robertx22.spells.bases;

import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.uncommon.effectdatas.interfaces.IBuffableSpell;

public class SpellBuffCheck implements IBuffableSpell {

    public SpellBuffCheck(SpellType type) {
	this.type = type;
    }

    SpellBuffType buff = SpellBuffType.None;
    SpellType type;

    @Override
    public void setBuff(SpellBuffType buff) {
	this.buff = buff;

    }

    @Override
    public SpellBuffType getBuff() {
	return buff;
    }

    @Override
    public void setBuffType(SpellType type) {
	this.type = type;

    }

    @Override
    public SpellType getBuffType() {
	return this.type;
    }

}
