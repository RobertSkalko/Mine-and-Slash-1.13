package com.robertx22.database.unique_items.necklaces;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceWater extends BaseUniqueNecklace {

    public NecklaceWater() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "necklacewater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellWaterDamageFlat(), new SpellWaterDamagePercent(), new ArmorFlat(), new ArmorPercent(), new WaterResistFlat(), new CrippleDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "Crystal clear and yet so incredibly tough.";
    }
}
