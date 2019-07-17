package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.saveclasses.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.mine_and_slash.saveclasses.rune.RunesData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class RunedGearLootGen extends BaseLootGen {

    public RunedGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.RunedItem;
    }

    @Override
    public ItemStack generateOne() {

        RunedGearBlueprint gearPrint = new RunedGearBlueprint(info.level);

        ItemStack stack = CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

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

        if (blueprint.canGetSet(data)) {
            data.set = blueprint.generateSet(data);
        }

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
