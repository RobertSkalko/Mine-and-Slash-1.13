package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.items.gearitems.baubles.ItemRing;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Ring extends GearItemSlot {

    @Override
    public String GUID() {
        return "Ring";
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new SpellDamageFlat(), new SpellFireDamageFlat(), new SpellThunderDamageFlat(), new SpellWaterDamageFlat(), new SpellNatureDamageFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new EnergyRegenFlat(), new ManaRegenFlat(), new ManaFlat());
    }

    @Override
    public Item DefaultItem() {
        return ItemRing.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemRing.Items;
    }

    @Override
    public int Weight() {
        return 1500;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Ring";
    }
}
