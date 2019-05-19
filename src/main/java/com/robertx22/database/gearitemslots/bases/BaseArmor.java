package com.robertx22.database.gearitemslots.bases;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseArmor extends GearItemSlot {

    @Override
    public List<Suffix> PossibleSuffixes() {
        return new ArrayList<Suffix>(Suffixes.Armor);
    }

    @Override
    public List<Prefix> PossiblePrefixes() {
        return new ArrayList<Prefix>(Prefixes.Armor);
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new ArmorFlat(), new StrengthFlat(), new VitalityFlat(), new IntelligenceFlat(), new WisdomFlat(), new StaminaFlat(), new DexterityFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Armor;
    }
}
