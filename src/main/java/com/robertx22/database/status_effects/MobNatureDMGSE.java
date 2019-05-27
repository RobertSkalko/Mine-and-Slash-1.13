package com.robertx22.database.status_effects;

import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;
import com.robertx22.database.status_effects.bases.BaseMobEleDMG;
import com.robertx22.saveclasses.gearitem.StatModData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class MobNatureDMGSE extends BaseMobEleDMG {

    @Override
    public Item ItemModel() {
        return Items.SLIME_BALL;
    }

    @Override
    public String GUID() {
        return "MobNatureDMGSE";
    }

    @Override
    public List<StatModData> Stats() {
        return Arrays.asList(StatModData.NewStatusEffect(this.percent, new NatureSpellToAttackFlat()));
    }

}