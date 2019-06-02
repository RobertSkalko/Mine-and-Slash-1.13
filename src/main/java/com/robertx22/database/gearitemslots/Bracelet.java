package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.items.gearitems.baubles.ItemBracelet;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class Bracelet extends GearItemSlot {

    @Override
    public String GUID() {
        return "Bracelet";
    }

    ListUtils
    @Override

    public List<StatMod> PrimaryStats() {
        return ListUtils.newList(new ElementalSpellDamageFlat().allSingleElements(), new SpellDamageFlat());

    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return new ElementalResistFlat().allSingleElements();
    }

    @Override
    public Item DefaultItem() {
        return ItemBracelet.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemBracelet.Items;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Bracelet";
    }
}