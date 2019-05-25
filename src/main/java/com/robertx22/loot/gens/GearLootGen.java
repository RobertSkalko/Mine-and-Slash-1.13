package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.create.GearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GearLootGen extends BaseLootGen {

    GearBlueprint gearPrint;

    public GearLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                       EntityPlayer killer) {
        super(mob, player, victim, killer);

        gearPrint = new GearBlueprint(mob.getLevel());

    }

    public GearLootGen(World theworld, float multi, int level) {
        super(theworld, multi);
        gearPrint = new GearBlueprint(level);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.GEAR_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        ItemStack stack = GearGen.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
