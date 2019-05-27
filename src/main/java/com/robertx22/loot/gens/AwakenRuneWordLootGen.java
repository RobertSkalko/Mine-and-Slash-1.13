package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.items.misc.ItemAwakenRuneWord;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.AwakenRuneWordBlueprint;
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
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get();
    }

    @Override
    public ItemStack generateOne() {

        return Create(new AwakenRuneWordBlueprint());

    }

    public static ItemStack Create(AwakenRuneWordBlueprint blueprint) {

        ItemStack stack = new ItemStack(ItemAwakenRuneWord.ITEM);

        ItemAwakenRuneWord item = (ItemAwakenRuneWord) stack.getItem();

        RuneWord word = blueprint.getWord();

        item.setWord(stack, word);

        return stack;

    }

}
