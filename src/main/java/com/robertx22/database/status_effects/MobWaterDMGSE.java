package com.robertx22.database.status_effects;

import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.status_effects.bases.BaseMobEleDMG;
import com.robertx22.saveclasses.gearitem.StatModData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class MobWaterDMGSE extends BaseMobEleDMG {

    @Override
    public Item ItemModel() {
        return Items.WATER_BUCKET;
    }

    @Override
    public String GUID() {
        return "MobWaterDMGSE";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.NewStatusEffect(this.percent, new WaterSpellToAttackFlat()));
    }

}
