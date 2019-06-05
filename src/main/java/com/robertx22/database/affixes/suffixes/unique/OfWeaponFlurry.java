package com.robertx22.database.affixes.suffixes.unique;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.WeaponSuffix;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.requirements.UniqueTierRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.StaminaFlat;
import com.robertx22.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public class OfWeaponFlurry extends WeaponSuffix {

    public OfWeaponFlurry(WeaponTypes type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "of_godhood";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new WeaponDamageFlat(this.type), new StaminaFlat());
    }

    @Override
    public int Weight() {
        return MythicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Bracelet()), LevelRequirement.fromLVL50(), new UniqueTierRequirement(10));
    }

    @Override
    public String locNameForLangFile() {
        return "Of " + type.name() + " Flurry";
    }

    @Override
    public Suffix newGeneratedInstance(WeaponTypes type) {
        return new OfWeaponFlurry(type);
    }
}
