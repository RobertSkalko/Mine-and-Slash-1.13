package com.robertx22.loot.create;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.blueprints.RunedGearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.rune.RunesData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

public class RunedGearGen {

    public static GearItemData CreateData(RunedGearBlueprint blueprint) {
        GearItemSlot gearslot = blueprint.GetGearType();

        ItemRarity rarity = Rarities.RunedItems.get(blueprint.GetRarity());

        GearItemData data = new GearItemData();

        data.level = blueprint.GetLevel();
        data.gearTypeName = gearslot.GUID();
        data.Rarity = rarity.Rank();

        data.primaryStats = new PrimaryStatsData();
        data.primaryStats.RerollFully(data);

        data.runes = new RunesData();

        data.runes.capacity = rarity.runeSlots();

        data.set = blueprint.GenerateSet();

        return data;
    }

    public static ItemStack CreateStack(RunedGearBlueprint schema) {

        GearItemData data = CreateData(schema);

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

    public static ItemStack CreateStack(GearItemData data) {

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

}
