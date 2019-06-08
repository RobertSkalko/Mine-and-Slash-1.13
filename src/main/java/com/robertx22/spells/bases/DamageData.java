package com.robertx22.spells.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class DamageData {

    public DamageData(LivingEntity entity, SpellItemData spellItem) {
        // this.effect = e;
        this.caster = entity;
        this.spellItem = spellItem;
        this.casterUnit = Load.Unit(entity);

    }

    public EntityData.UnitData casterUnit;
    // public BaseSpellEffect effect;
    public LivingEntity caster;
    public SpellItemData spellItem;

}
