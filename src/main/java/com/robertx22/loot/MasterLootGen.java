package com.robertx22.loot;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.gens.*;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.LevelUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MasterLootGen {

    public static List<ItemStack> gen(UnitData mob, UnitData player, IWorldData world,
                                      EntityLivingBase victim) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (mob == null || player == null || world == null || victim == null) {
            return items;
        }

        if (mob.getLevel() >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get()) {
            items.addAll(new CurrencyLootGen(mob, player, world, victim).generate());
            items.addAll(new AwakenRuneWordLootGen(mob, player, world, victim).generate());
        }
        items.addAll(new GearLootGen(mob, player, world, victim).generate());
        items.addAll(new SpellLootGen(mob, player, world, victim).generate());
        items.addAll(new MapLootGen(mob, player, world, victim).generate());
        items.addAll(new RuneLootGen(mob, player, world, victim).generate());
        items.addAll(new RunedGearLootGen(mob, player, world, victim).generate());
        items.addAll(new LootBoxGen(mob, player, world, victim).generate());

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get()) {
            items.addAll(new CompatibleItemLootGen(mob, player, world, victim).generate());
        }

        if (world.dropsUniques(victim.world)) {
            items.addAll(new UniqueGearLootGen(mob, player, world, victim).generate());
        }

        return items;
    }

    public static List<ItemStack> gen(World theworld, float multi, IWorldData world,
                                      int level) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (world == null) {
            return items;
        }

        if (level >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get()) {
            items.addAll(new CurrencyLootGen(theworld, multi, world).generate());
            items.addAll(new AwakenRuneWordLootGen(theworld, multi, world).generate());
        }

        items.addAll(new GearLootGen(theworld, multi, world, level).generate());
        items.addAll(new SpellLootGen(theworld, multi, world, level).generate());
        items.addAll(new MapLootGen(theworld, multi, world, level).generate());
        items.addAll(new RuneLootGen(theworld, multi, world, level).generate());
        items.addAll(new RunedGearLootGen(theworld, multi, world, level).generate());
        items.addAll(new LootBoxGen(theworld, multi, world).generate());

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get()) {
            items.addAll(new CompatibleItemLootGen(theworld, multi, world, level).generate());
        }
        if (world.dropsUniques(theworld)) {
            items.addAll(new UniqueGearLootGen(theworld, multi, world, level).generate());

        }

        return items;
    }

    public static void genAndDrop(UnitData mob, UnitData player, IWorldData world,
                                  EntityLivingBase victim) {

        List<ItemStack> items = gen(mob, player, world, victim);

        for (ItemStack stack : items) {
            victim.entityDropItem(stack, 1F);
        }

    }

    public static List<ItemStack> genAmount(BlockPos pos, int amount, World theworld,
                                            IWorldData iworlddata) {

        int level = LevelUtils.determineLevel(iworlddata, theworld, pos);

        List<ItemStack> loot = new ArrayList<>();

        while (loot.size() < amount) {
            loot.addAll(MasterLootGen.gen(theworld, amount * 10F, iworlddata, level)
                    .stream()
                    .filter(x -> x.isEmpty() == false)
                    .collect(Collectors.toList()));
        }

        while (loot.size() > amount && loot.size() > 0) {
            loot.remove(0);
        }

        return loot;
    }

}
