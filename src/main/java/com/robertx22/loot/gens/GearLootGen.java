package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.create.GearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
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
    public ItemStack generateOne() {

        GearBlueprint gearPrint = new GearBlueprint(info.level);

        ItemStack stack = GearGen.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
