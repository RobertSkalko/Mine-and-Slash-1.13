package com.robertx22.database.unique_items.chest;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestThunder extends BaseUniqueChest {

    public ChestThunder() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "chestthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new ThunderResistFlat(), new MajorArmorFlat(), new SpellThunderDamagePercent(), new ThunderSpellToAttackFlat(), new CrippleLifestealPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Armor of the Thunderstorm";
    }

    @Override
    public String locDescForLangFile() {
        return "Those who dared to follow had long since died.";
    }
}