package com.robertx22.potion_effects;

import com.robertx22.saveclasses.gearitem.StatModData;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public interface IStatGivingPotion {
    List<StatModData> getStats(EffectInstance instance);
}
