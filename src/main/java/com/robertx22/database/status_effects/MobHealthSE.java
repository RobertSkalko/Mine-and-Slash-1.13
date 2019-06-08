package com.robertx22.database.status_effects;

import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;
import net.minecraft.item.Items;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class MobHealthSE extends BaseStatusEffect {

    @Override
    public Item ItemModel() {
        return Items.APPLE;
    }

    @Override
    public String GUID() {
        return "HealthSE";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new HealthPercent(), 500));
    }

}
