package com.robertx22.database.gearitemslots.bases;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.db_lists.StatMods;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.ILocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class GearItemSlot implements IWeighted, ILocName {

    public enum GearSlotType {
        Weapon, Armor, Jewerly, OffHand
    }

    public abstract String GUID();

    public abstract GearSlotType slotType();

    @Override
    public ITextComponent locName() {
        return CLOC.geartype(GUID().toLowerCase());
    }

    public abstract List<Suffix> PossibleSuffixes();

    public abstract List<Prefix> PossiblePrefixes();

    public abstract List<StatMod> PrimaryStats();

    public List<StatMod> slotTypeStats() {
        return new ArrayList<StatMod>();
    }

    public abstract List<StatMod> PossibleSecondaryStats();

    public abstract Item DefaultItem();

    public abstract HashMap<Integer, Item> ItemsForRarities();

    public int Weight() {
        return 1000;
    }

    public ItemStack GetStackForRarity(int rarityNum) {

        if (ItemsForRarities().containsKey(rarityNum)) {
            return new ItemStack(ItemsForRarities().get(rarityNum));
        }

        return new ItemStack(DefaultItem());

    }

    public Item GetItemForRarity(int rarityNum) {

        if (ItemsForRarities().containsKey(rarityNum)) {
            return ItemsForRarities().get(rarityNum);
        }

        return DefaultItem();

    }

    public List<StatMod> ChaosStats() {

        List<StatMod> list = new ArrayList<StatMod>();

        for (StatMod mod : StatMods.All.values()) {
            if (mod instanceof BaseTraitMod) {
                list.add(mod);
            }
        }

        return list;
    }

}
