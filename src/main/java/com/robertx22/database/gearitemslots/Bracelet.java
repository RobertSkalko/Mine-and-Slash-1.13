package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.items.gearitems.baubles.ItemBracelet;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Bracelet extends GearItemSlot {

    @Override
    public String GUID() {
        return "Bracelet";
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new SpellDamageFlat(), new SpellFireDamageFlat(), new SpellThunderDamageFlat(), new SpellWaterDamageFlat(), new SpellNatureDamageFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new FireResistFlat(), new ThunderResistFlat(), new WaterResistFlat(), new NatureResistFlat());
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