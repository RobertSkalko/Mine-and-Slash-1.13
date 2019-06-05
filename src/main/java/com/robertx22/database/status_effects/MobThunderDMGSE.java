package com.robertx22.database.status_effects;

import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.database.status_effects.bases.BaseMobEleDMG;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class MobThunderDMGSE extends BaseMobEleDMG {

    @Override
    public Item ItemModel() {
        return Items.GLOWSTONE_DUST;
    }

    @Override
    public String GUID() {
        return "MobThunderDMGSE";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.Load(new ElementalSpellToAttackDMGFlat(Elements.Thunder), percent));
    }

}
