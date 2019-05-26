package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.SpellBlueprint;
import com.robertx22.loot.create.SpellItemGen;
import net.minecraft.item.ItemStack;

public class SpellLootGen extends BaseLootGen {

    public SpellLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.SPELL_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {
        SpellBlueprint spellPrint = new SpellBlueprint(info.level);

        return SpellItemGen.Create(spellPrint);

    }

}
