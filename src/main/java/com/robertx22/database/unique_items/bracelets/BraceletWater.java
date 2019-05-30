package com.robertx22.database.unique_items.bracelets;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BraceletWater extends BaseUniqueBracelet {

    public BraceletWater() {

    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public String GUID() {
        return "braceletwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellWaterDamageFlat(), new BaseTransferFlat(Elements.Fire, Elements.Water), new EnergyRegenFlat(), new WaterResistFlat(), new FireResistFlat(), new CrippleDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Frostburn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Burn them all! With Ice of course.";
    }
}
