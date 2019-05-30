package com.robertx22.database.unique_items.bracelets;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BraceletThunderNature extends BaseUniqueBracelet {

    public BraceletThunderNature() {

    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "braceletthundernature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new BaseTransferFlat(Elements.Thunder, Elements.Nature), new NatureResistFlat(), new ThunderResistFlat(), new ManaFlat(), new LessCriticalHitPercent(), new LessDodgePercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Rooted Thunder Bracers";
    }

    @Override
    public String locNameForLangFile() {
        return "Heavenly Lightning? I call it mana.";
    }
}