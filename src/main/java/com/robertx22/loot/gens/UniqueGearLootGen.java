package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.loot.create.UniqueGearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
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

        ItemStack stack = UniqueGearGen.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
