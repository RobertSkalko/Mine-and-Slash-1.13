package com.robertx22.spells.bases;

import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
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

    Elements element = Elements.None;

    @Override
    public void Activate(DamageData dmgdata, EntityLivingBase target) {

        int num = dmgdata.spellItem.GetDamage(dmgdata.casterUnit.getUnit());

        DamageEffect dmg = new DamageEffect(dmgdata.caster, target, num, dmgdata.casterUnit, Load
                .Unit(target), EffectTypes.SPELL, WeaponTypes.None);
        dmg.Element = this.element;
        dmg.Activate();

    }

    @Override
    public String Name() {
        return "Spell Effect Damage";
    }

}