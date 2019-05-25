package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.RunedGearBlueprint;
import com.robertx22.loot.create.RunedGearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RunedGearLootGen extends BaseLootGen {

    RunedGearBlueprint gearPrint;

    public RunedGearLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                            EntityPlayer killer) {
        super(mob, player, victim, killer);

        gearPrint = new RunedGearBlueprint(mob.getLevel());

    }

    public RunedGearLootGen(World theworld, float multi, int level) {
        super(theworld, multi);
        gearPrint = new RunedGearBlueprint(level);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        ItemStack stack = RunedGearGen.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
