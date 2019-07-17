package com.robertx22.mine_and_slash.database.affixes.prefixes.offense.imbued;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class LightningImbued extends BaseImbuedPrefix {

    public LightningImbued() {
    }

    @Override
    public String GUID() {
        return "Lightning Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalSpellToAttackDMGFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "Lightning Imbued";
    }
}