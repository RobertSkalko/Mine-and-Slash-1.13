package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.mine_and_slash.db_lists.StatMods;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class GearItemSlot implements IWeighted, IAutoLocName {

    public enum GearSlotType {
        Weapon,
        Armor,
        Jewerly,
        OffHand
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Slots;
    }

    public abstract GearSlotType slotType();

    public List<StatMod> coreStatMods() {
        return Arrays.asList(new StrengthFlat(), new VitalityFlat(), new IntelligenceFlat(), new WisdomFlat(), new StaminaFlat(), new DexterityFlat());
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".gear_type." + formattedGUID();
    }

    public abstract List<StatMod> PrimaryStats();

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
            if (mod instanceof AllTraitMods) {
                list.add(mod);
            }
        }

        return list;
    }

}
