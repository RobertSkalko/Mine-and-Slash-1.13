package com.robertx22.database.unique_items.pants;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniquePants;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PantsThunder extends BaseUniquePants {

    public PantsThunder() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "pantsthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new SpellFireDamageFlat(), new DodgeFlat(), new FireResistFlat(), new BaseTransferFlat(Elements.Fire, Elements.Thunder), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Lightning Coil Leggings";
    }

    @Override
    public String locNameForLangFile() {
        return "Swallow flames, harness Lightning.";
    }
}
