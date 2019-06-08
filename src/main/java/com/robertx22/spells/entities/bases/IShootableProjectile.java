package com.robertx22.spells.entities.bases;

import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import net.minecraft.entity.LivingEntity;

public interface IShootableProjectile {

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              LivingEntity caster);

}
