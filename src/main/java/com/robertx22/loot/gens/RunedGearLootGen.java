package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.RunedGearBlueprint;
import com.robertx22.loot.create.RunedGearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

public class RunedGearLootGen extends BaseLootGen {

    public RunedGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        RunedGearBlueprint gearPrint = new RunedGearBlueprint(info.level);

        ItemStack stack = RunedGearGen.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
