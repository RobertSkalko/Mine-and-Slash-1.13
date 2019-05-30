package com.robertx22.database.unique_items.necklaces;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class NecklaceFire extends BaseUniqueNecklace {

    public NecklaceFire() {

    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String GUID() {
        return "necklacefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellFireDamageFlat(), new FireSpellToAttackFlat(), new BaseTransferFlat(Elements.Water, Elements.Fire), new WaterResistFlat(), new LessHealthRegenFlat());

    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Burning Man Amulet";
    }

    @Override
    public String locNameForLangFile() {
        return "I will take down my enemies with me, in flames.";
    }
}
