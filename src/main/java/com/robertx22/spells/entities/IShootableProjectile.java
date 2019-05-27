package com.robertx22.spells.entities;

import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import net.minecraft.entity.EntityLivingBase;

public interface IShootableProjectile {

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              EntityLivingBase caster);

}
