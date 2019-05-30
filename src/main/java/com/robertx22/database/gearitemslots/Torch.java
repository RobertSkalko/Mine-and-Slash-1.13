package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.items.gearitems.offhands.MyTorch;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Torch extends BaseOffHand {

    @Override
    public String GUID() {
        return "Torch";
    }

    @Override
    public Item DefaultItem() {
        return MyTorch.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return MyTorch.Items;
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new ManaRegenFlat(), new EnergyRegenFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new ManaFlat(), new EnergyFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Torch";
    }

}
