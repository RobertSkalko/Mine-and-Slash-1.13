package com.robertx22.uncommon.effectdatas;

import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.LivingEntity;

public class SpellDamageEffect extends DamageEffect {

    public BaseSpell spell;

    public SpellDamageEffect(LivingEntity source, LivingEntity target, int dmg,
                             EntityData.UnitData sourceData,
                             EntityData.UnitData targetData, BaseSpell spell) {
        super(source, target, dmg, sourceData, targetData, EffectTypes.SPELL, WeaponTypes.None);

        this.spell = spell;
    }

}
