package com.robertx22.database.unique_items.charms;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.stats.stat_mods.percent.pene.ThunderPenePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueCharm;

import java.util.Arrays;
import java.util.List;

public class CharmThunder extends BaseUniqueCharm {

    public CharmThunder() {

    }

    @Override
    public int Tier() {
        return 19;
    }

    @Override
    public String GUID() {
        return "charmthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellThunderDamagePercent(), new ManaRegenPercent(), new ThunderPenePercent(), new CriticalHitPercent(), new ThunderResistFlat(), new CrippleLifeOnHitPercent());
    }

}
