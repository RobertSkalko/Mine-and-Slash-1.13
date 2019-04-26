package com.robertx22.loot.gens;

import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.loot.create.UniqueGearGen;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Gear;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UniqueGearLootGen extends BaseLootGen {
    UniqueBlueprint gearPrint;

    public UniqueGearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);
	gearPrint = new UniqueBlueprint(mob.getLevel(), this.world_tier, true);

    }

    public UniqueGearLootGen(World theworld, float multi, IWorldData world, int level) {
	super(theworld, multi, world);
	gearPrint = new UniqueBlueprint(level, this.world_tier, true);

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.UNIQUE_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	ItemStack stack = UniqueGearGen.CreateStack(gearPrint);

	GearItemData gear = Gear.Load(stack);

	return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
