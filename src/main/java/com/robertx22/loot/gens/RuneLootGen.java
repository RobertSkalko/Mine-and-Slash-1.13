package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.RuneBlueprint;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class RuneLootGen extends BaseLootGen {

    public RuneLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNE_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        RuneBlueprint runePrint = new RuneBlueprint(info.level);

        return Create(runePrint);

    }

    public static ItemStack Create(RuneBlueprint blueprint) {

        int rarity = blueprint.GetRarity();

        BaseRuneItem item = blueprint.getRuneItem().byRarity(rarity);

        ItemStack stack = new ItemStack(item);

        RuneItemData data = new RuneItemData();

        data.rarity = item.rarity;
        data.name = item.name();
        data.level = blueprint.level;

        data.armor = StatModData.NewRandom(data.GetRarity(), RandomUtils.weightedRandom(item
                .armorStat()));

        data.weapon = StatModData.NewRandom(data.GetRarity(), RandomUtils.weightedRandom(item
                .weaponStat()));

        data.jewerly = StatModData.NewRandom(data.GetRarity(), RandomUtils.weightedRandom(item
                .jewerlyStat()));

        Rune.Save(stack, data);

        return stack;

    }

}
