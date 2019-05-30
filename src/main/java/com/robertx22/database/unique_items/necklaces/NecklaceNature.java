package com.robertx22.database.unique_items.necklaces;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class NecklaceNature extends BaseUniqueNecklace {

    public NecklaceNature() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "necklacenature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellNatureDamageFlat(), new HealthFlat(), new HealthRegenFlat(), new BaseTransferFlat(Elements.Fire, Elements.Nature), new FireResistFlat(), new HealthPercent(), new CrippleDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Oak";
    }

    @Override
    public String locDescForLangFile() {
        return "I seek strength only in nature.";
    }
}
