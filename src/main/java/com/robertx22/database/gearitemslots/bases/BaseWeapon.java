package com.robertx22.database.gearitemslots.bases;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.PhysicalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.items.gearitems.bases.IWeapon;

import java.util.Arrays;
import java.util.List;

public abstract class BaseWeapon extends GearItemSlot implements IWeapon {
    @Override
    public List<Suffix> PossibleSuffixes() {
        return Suffixes.Weapon;
    }

    @Override
    public List<Prefix> PossiblePrefixes() {
        return Prefixes.Weapon;
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new PhysicalDamageFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new LifestealFlat(), new LifeOnHitFlat());
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Weapon;
    }

}
