package com.robertx22.database.status_effects;

import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Items;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class MobElementResistSE extends BaseStatusEffect {

    @Override
    public Item ItemModel() {
        return Items.GOLDEN_CHESTPLATE;
    }

    @Override
    public String GUID() {
        return "MobElementResistSE";
    }

    int percent = 300;

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new ElementalResistFlat(Elements.Fire), percent), StatModData
                .Load(new ElementalResistFlat(Elements.Water), percent), StatModData.Load(new ElementalResistFlat(Elements.Thunder), percent), StatModData
                .Load(new ElementalResistFlat(Elements.Nature), percent));
    }

}
