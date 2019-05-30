package com.robertx22.database.unique_items.helmet;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.MajorManaRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.flat.weapon_damages.StaffDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetMana extends BaseUniqueHelmet {

    public HelmetMana() {

    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public String GUID() {
        return "helmetmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new ManaFlat(), new MajorManaRegenFlat(), new ManaRegenPercent(), new MajorDodgeFlat(), new ArmorFlat(), new StaffDamageFlat());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Helmet of Mana";
    }

    @Override
    public String locNameForLangFile() {
        return "Flow to me.";
    }

}