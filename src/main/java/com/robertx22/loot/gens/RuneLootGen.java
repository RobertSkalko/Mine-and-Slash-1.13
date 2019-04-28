package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.RuneBlueprint;
import com.robertx22.loot.create.RuneGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RuneLootGen extends BaseLootGen {

    RuneBlueprint runePrint;

    public RuneLootGen(UnitData mob, UnitData player, IWorldData world,
                       EntityLivingBase victim) {
        super(mob, player, world, victim);

        runePrint = new RuneBlueprint(mob.getLevel());

    }

    public RuneLootGen(World theworld, float multi, IWorldData world, int level) {
        super(theworld, multi, world);

        runePrint = new RuneBlueprint(level);

    }

    @Override
    public float BaseChance() {
        return ModConfig.DropRates.RUNE_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

        return RuneGen.Create(runePrint);

    }

}
