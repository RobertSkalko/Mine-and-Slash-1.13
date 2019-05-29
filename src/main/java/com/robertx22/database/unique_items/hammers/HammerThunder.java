package com.robertx22.database.unique_items.hammers;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueHammer;

import java.util.Arrays;
import java.util.List;

public class HammerThunder extends BaseUniqueHammer {
    public HammerThunder() {

    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public String GUID() {
        return "hammerthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackThunderDamageFlat(), new ThunderSpellToAttackFlat(), new CriticalHitPercent(), new CriticalDamagePercent(), new CriticalHitFlat(), new CrippleLifeOnHitPercent(), new CrippleManaOnHitPercent());
    }

}
