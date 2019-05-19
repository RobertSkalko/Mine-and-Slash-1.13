package com.robertx22.database.gearitemslots;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.items.gearitems.baubles.ItemCharm;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Charm extends GearItemSlot {

    @Override
    public String GUID() {
        return "Charm";
    }

    @Override
    public List<Suffix> PossibleSuffixes() {
        return new ArrayList<Suffix>(Suffixes.Weapon);
    }

    @Override
    public List<Prefix> PossiblePrefixes() {
        return new ArrayList<Prefix>(Prefixes.Weapon);
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new FirePeneFlat(), new WaterPeneFlat(), new NaturePeneFlat(), new ThunderPeneFlat(), new ArmorPeneFlat());
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
}
