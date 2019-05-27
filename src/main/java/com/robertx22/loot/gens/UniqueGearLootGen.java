package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen {

    public UniqueGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        UniqueBlueprint gearPrint = new UniqueBlueprint(info.level, info.tier, true);

        ItemStack stack = CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

    public static GearItemData CreateData(UniqueBlueprint blueprint) {

        IUnique unique = blueprint.getUnique();
        GearItemData data = new GearItemData();

        if (unique != null) {
            ItemRarity rarity = new UniqueItem();
            GearItemSlot gearslot = GearTypes.All.get(unique.slot());

            data.isUnique = true;

            data.uniqueGUID = unique.GUID();
            data.uniqueStats = new UniqueStatsData(unique.GUID());
            data.uniqueStats.RerollFully(data);

            data.level = blueprint.GetLevel();
            data.gearTypeName = gearslot.GUID();
            data.Rarity = rarity.Rank();

            if (RandomUtils.roll(rarity.AffixChance())) {

                data.suffix = new SuffixData();
                data.suffix.RerollFully(data);

            }
            if (RandomUtils.roll(rarity.AffixChance())) {

                data.prefix = new PrefixData();
                data.prefix.RerollFully(data);

            }

        }

        return data;
    }

    public static ItemStack CreateStack(UniqueBlueprint schema) {

        GearItemData data = CreateData(schema);

        if (data != null && data.getItem() != null) {
            ItemStack stack = new ItemStack(data.getItem());

            Gear.Save(stack, data);

            return stack;
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack CreateStack(GearItemData data) {

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

}
