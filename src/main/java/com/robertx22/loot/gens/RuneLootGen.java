package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.RuneBlueprint;
import com.robertx22.loot.create.RuneGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RuneLootGen extends BaseLootGen {

    RuneBlueprint runePrint;

    public RuneLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                       EntityPlayer killer) {
        super(mob, player, victim, killer);

        runePrint = new RuneBlueprint(mob.getLevel());

    }

    public RuneLootGen(World theworld, float multi, int level) {
        super(theworld, multi);

        runePrint = new RuneBlueprint(level);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNE_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        return RuneGen.Create(runePrint);

    }

}
