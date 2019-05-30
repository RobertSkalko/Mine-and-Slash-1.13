package com.robertx22.database.unique_items.pants;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniquePants;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PantsWater extends BaseUniquePants {

    public PantsWater() {

    }

    @Override
    public int Tier() {
        return 13;
    }

    @Override
    public String GUID() {
        return "pantswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new MajorArmorFlat(), new ArmorPercent(), new WaterResistFlat(), new BaseTransferFlat(Elements.Fire, Elements.Water), new SpellWaterDamagePercent(), new LessDodgePercent());

    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Leggings of the Glacier";
    }

    @Override
    public String locNameForLangFile() {
        return "Try to move me, I dare you.";
    }
}
