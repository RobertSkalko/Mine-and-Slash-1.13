package com.robertx22.database.unique_items.swords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class SwordWater extends BaseUniqueSword {
    public SwordWater() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "swordwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackWaterDamageFlat(), new BaseTransferFlat(Elements.Thunder, Elements.Water), new ManaRegenFlat(), new ManaOnHitFlat(), new EnergyRegenFlat(), new CrippleLifestealPercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Ice Elemental Sword";
    }

    @Override
    public String locNameForLangFile() {
        return "Energy is everywhere, it just begs to be grasped";
    }
}
