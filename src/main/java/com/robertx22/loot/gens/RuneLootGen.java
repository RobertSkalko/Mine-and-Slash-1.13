package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.RuneBlueprint;
import com.robertx22.loot.create.RuneGen;
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

        return RuneGen.Create(runePrint);

    }

}
