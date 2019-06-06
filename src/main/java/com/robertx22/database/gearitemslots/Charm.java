package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.items.gearitems.baubles.ItemCharm;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Charm extends GearItemSlot {

    @Override
    public String GUID() {
        return "Charm";
    }

    @Override
    public List<StatMod> PrimaryStats() {

        return ListUtils.newList(new ElementalPeneFlat(Elements.Physical).allSingleElementVariations(), new ArmorPeneFlat());

    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new StrengthFlat(), new VitalityFlat(), new IntelligenceFlat(), new WisdomFlat(), new StaminaFlat(), new DexterityFlat());
    }

    @Override
    public Item DefaultItem() {
        return ItemCharm.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemCharm.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Charm";
    }
}
