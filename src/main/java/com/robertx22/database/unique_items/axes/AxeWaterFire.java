package com.robertx22.database.unique_items.axes;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueAxe;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class AxeWaterFire extends BaseUniqueAxe {
    public AxeWaterFire() {

    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "axewaterfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackFireDamageFlat(), new AttackWaterDamageFlat(), new CriticalHitPercent(), new CrippleLifeOnHitPercent(), new CrippleManaOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Axe of Frostfire";
    }

    @Override
    public String locDescForLangFile() {
        return "My efforts to merge elements shall not be in vain.";
    }
}




