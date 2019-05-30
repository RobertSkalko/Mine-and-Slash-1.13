package com.robertx22.database.unique_items.chest;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ChestFire extends BaseUniqueChest {

    public ChestFire() {

    }

    @Override
    public int Tier() {
        return 6;

    }

    @Override
    public String GUID() {
        return "chestfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new FireResistFlat(), new SpellFireDamageFlat(), new BaseTransferFlat(Elements.Nature, Elements.Fire), new CrippleLifeOnHitPercent(), new CrippleLifestealPercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Burning Man Chestplate";
    }

    @Override
    public String locNameForLangFile() {
        return "What can't kill me only makes me glow brighter.";
    }
}