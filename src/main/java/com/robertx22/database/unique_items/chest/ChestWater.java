package com.robertx22.database.unique_items.chest;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ChestWater extends BaseUniqueChest {

    public ChestWater() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "chestwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new WaterPeneFlat(), new MajorArmorFlat(), new ArmorPercent(), new BaseTransferFlat(Elements.Nature, Elements.Water), new LessDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Armor of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "I am a Fortress of pure Ice.";
    }
}