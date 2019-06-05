package com.robertx22.database.status_effects;

import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class MobLifestealSE extends BaseStatusEffect {

    @Override
    public Item ItemModel() {
        return Items.GLISTERING_MELON_SLICE;
    }

    @Override
    public String GUID() {
        return "MobLifestealSE";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new LifestealFlat(), 1000));
    }

}
