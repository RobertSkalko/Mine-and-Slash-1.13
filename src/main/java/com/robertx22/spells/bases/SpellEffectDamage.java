package com.robertx22.spells.bases;

import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityLivingBase;

public class SpellEffectDamage extends BaseSpellEffect {

    public SpellEffectDamage() {
        super();

    }

    public SpellEffectDamage(Elements el) {
        super();

        this.element = el;
    }

    Elements element = Elements.Physical;

    @Override
    public void Activate(DamageData dmgdata, EntityLivingBase target) {

        int num = dmgdata.spellItem.GetDamage(dmgdata.casterUnit.getUnit());

        SpellDamageEffect dmg = new SpellDamageEffect(dmgdata.caster, target, num, dmgdata.casterUnit, Load
                .Unit(target), dmgdata.spellItem.GetSpell());
        dmg.Element = this.element;
        dmg.Activate();

    }

    @Override
    public String Name() {
        return "Spell Effect Damage";
    }

}