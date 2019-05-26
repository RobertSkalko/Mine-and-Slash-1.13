package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.loot.create.AwakenRuneWordGen;
import net.minecraft.item.ItemStack;

public class AwakenRuneWordLootGen extends BaseLootGen {

    public AwakenRuneWordLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.AWAKEN_RUNEWORD_DROPRATE.get().floatValue();

    }

    @Override
    public ItemStack generateOne() {

        return AwakenRuneWordGen.Create(new AwakenRuneWordBlueprint());

    }

}
