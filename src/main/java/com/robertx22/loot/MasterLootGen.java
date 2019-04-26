package com.robertx22.loot;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.loot.gens.AwakenRuneWordLootGen;
import com.robertx22.loot.gens.CurrencyLootGen;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.loot.gens.MapLootGen;
import com.robertx22.loot.gens.RuneLootGen;
import com.robertx22.loot.gens.RunedGearLootGen;
import com.robertx22.loot.gens.SpellLootGen;
import com.robertx22.loot.gens.UniqueGearLootGen;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MasterLootGen {

    public static List<ItemStack> gen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	List<ItemStack> items = new ArrayList<ItemStack>();

	if (mob == null || player == null || world == null || victim == null) {
	    return items;
	}

	if (mob.getLevel() >= ModConfig.Server.CURRENCY_DROP_AFTER_LEVEL) {
	    items.addAll(new CurrencyLootGen(mob, player, world, victim).generate());
	    items.addAll(new AwakenRuneWordLootGen(mob, player, world, victim).generate());
	}
	items.addAll(new GearLootGen(mob, player, world, victim).generate());
	items.addAll(new SpellLootGen(mob, player, world, victim).generate());
	items.addAll(new MapLootGen(mob, player, world, victim).generate());
	items.addAll(new RuneLootGen(mob, player, world, victim).generate());
	items.addAll(new RunedGearLootGen(mob, player, world, victim).generate());

	if (world.dropsUniques(victim.world)) {
	    items.addAll(new UniqueGearLootGen(mob, player, world, victim).generate());
	}

	return items;
    }

    public static List<ItemStack> gen(World theworld, float multi, IWorldData world, int level) {
	List<ItemStack> items = new ArrayList<ItemStack>();

	if (world == null) {
	    return items;
	}

	if (level >= ModConfig.Server.CURRENCY_DROP_AFTER_LEVEL) {
	    items.addAll(new CurrencyLootGen(theworld, multi, world).generate());
	    items.addAll(new AwakenRuneWordLootGen(theworld, multi, world).generate());
	}

	items.addAll(new GearLootGen(theworld, multi, world, level).generate());
	items.addAll(new SpellLootGen(theworld, multi, world, level).generate());
	items.addAll(new MapLootGen(theworld, multi, world, level).generate());
	items.addAll(new RuneLootGen(theworld, multi, world, level).generate());
	items.addAll(new RunedGearLootGen(theworld, multi, world, level).generate());

	if (world.dropsUniques(theworld)) {
	    items.addAll(new UniqueGearLootGen(theworld, multi, world, level).generate());

	}

	return items;
    }

    public static void genAndDrop(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {

	List<ItemStack> items = gen(mob, player, world, victim);

	for (ItemStack stack : items) {
	    victim.entityDropItem(stack, 1F);
	}

    }

}
