package com.robertx22.uncommon.effectdatas;

import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.capability.EntityCap;
import com.robertx22.uncommon.effectdatas.interfaces.IHasSpellEffect;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.LivingEntity;

public class SpellDamageEffect extends DamageEffect implements IHasSpellEffect {

    public BaseSpell spell;

    public SpellDamageEffect(LivingEntity source, LivingEntity target, int dmg,
                             EntityCap.UnitData sourceData, EntityCap.UnitData targetData,
                             BaseSpell spell) {
        super(source, target, dmg, sourceData, targetData, EffectTypes.SPELL, WeaponTypes.None);

        this.spell = spell;
    }

    @Override
    public BaseSpell getSpell() {
        return spell;
    }
}
