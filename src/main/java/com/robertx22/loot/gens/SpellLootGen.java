package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.SpellBlueprint;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
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

        return Create(spellPrint);

    }

    public static ItemStack Create(SpellBlueprint blueprint) {

        BaseSpell spell = blueprint.GetSpell();
        ItemStack stack = new ItemStack(spell.SpellItem());
        SpellItemData data = new SpellItemData();
        data.rarity = blueprint.GetRarity();
        SpellRarity rarity = data.getRarity();

        data.rarity = rarity.Rank();

        data.spellGUID = spell.GUID();

        data.level = blueprint.GetLevel();
        data.baseEffectPercent = RandomUtils.RandomRange(rarity.SpellBasePercents().Min, rarity
                .SpellBasePercents().Max);
        data.scalingEffectPercent = RandomUtils.RandomRange(rarity.SpellScalingPercents().Min, rarity
                .SpellScalingPercents().Max);
        data.manaCostPercent = RandomUtils.RandomRange(SpellItemData.MIN_MANA_COST_PERCENT, SpellItemData.MAX_MANA_COST_PERCENT);

        Spell.Save(stack, data);

        return stack;

    }

}
