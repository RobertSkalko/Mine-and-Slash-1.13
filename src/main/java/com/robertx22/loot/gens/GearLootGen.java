package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.*;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class GearLootGen extends BaseLootGen {

    public GearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.NormalItem;
    }

    @Override
    public ItemStack generateOne() {

        GearBlueprint gearPrint = new GearBlueprint(info.level);

        ItemStack stack = CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

    public static GearItemData CreateData(GearBlueprint blueprint) {
        GearItemSlot gearslot = blueprint.GetGearType();

        ItemRarity rarity = Rarities.Items.get(blueprint.GetRarity());

        GearItemData data = new GearItemData();

        data.level = blueprint.GetLevel();
        data.gearTypeName = gearslot.GUID();
        data.Rarity = rarity.Rank();

        data.primaryStats = new PrimaryStatsData();
        data.primaryStats.RerollFully(data);

        data.secondaryStats = new SecondaryStatsData();
        data.secondaryStats.RerollFully(data);

        if (blueprint.getsChaosStats()) {
            data.chaosStats = new ChaosStatsData();
            data.chaosStats.RerollFully(data);
        }

        if (RandomUtils.roll(rarity.AffixChance())) {

            data.suffix = new SuffixData();
            data.suffix.RerollFully(data);

        }
        if (RandomUtils.roll(rarity.AffixChance())) {

            data.prefix = new PrefixData();
            data.prefix.RerollFully(data);

        }

        data.set = blueprint.tryGenerateSet(data);

        return data;
    }

    public static ItemStack CreateStack(GearBlueprint schema) {

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
