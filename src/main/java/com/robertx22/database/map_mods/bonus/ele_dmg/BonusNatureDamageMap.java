package com.robertx22.database.map_mods.bonus.ele_dmg;

import com.robertx22.database.map_mods.bases.BonusEleDmgBase;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.uncommon.enumclasses.Elements;

public class BonusNatureDamageMap extends BonusEleDmgBase {

    public BonusNatureDamageMap() {
    }

    @Override
    public String GUID() {
        return "BonusNatureDamageMap";
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellToAttackDMG(Elements.Nature);
    }

}