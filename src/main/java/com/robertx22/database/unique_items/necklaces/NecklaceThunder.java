package com.robertx22.database.unique_items.necklaces;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class NecklaceThunder extends BaseUniqueNecklace {

    public NecklaceThunder() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "necklacethunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellThunderDamageFlat(), new ThunderSpellToAttackFlat(), new BaseTransferFlat(Elements.Nature, Elements.Thunder), new NatureResistFlat(), new EnergyRegenFlat(), new LessHealthRegenFlat());

    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Amulet of the Thunderstorm";
    }

    @Override
    public String locNameForLangFile() {
        return "Command Thunder, command Energy.";
    }
}
