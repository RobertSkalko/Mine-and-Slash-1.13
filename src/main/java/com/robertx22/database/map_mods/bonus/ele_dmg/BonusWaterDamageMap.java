package com.robertx22.database.map_mods.bonus.ele_dmg;

import com.robertx22.database.map_mods.bases.BonusEleDmgBase;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.uncommon.enumclasses.Elements;

public class BonusWaterDamageMap extends BonusEleDmgBase {

    public BonusWaterDamageMap() {
    }

    @Override
    public String GUID() {
        return "BonusWaterDamageMap";
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellToAttackDMG(Elements.Water);
    }

}