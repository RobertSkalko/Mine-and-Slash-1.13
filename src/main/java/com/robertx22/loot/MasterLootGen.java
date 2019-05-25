package com.robertx22.loot;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.gens.*;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldUtils;
import com.robertx22.uncommon.utilityclasses.LevelUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MasterLootGen {

    public static List<ItemStack> gen(UnitData mob, UnitData player,
                                      EntityLivingBase victim, EntityPlayer killer) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (mob == null || player == null || victim == null) {
            return items;
        }

        if (mob.getLevel() >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get()) {
            items.addAll(new CurrencyLootGen(mob, player, victim, killer).generate());
            items.addAll(new AwakenRuneWordLootGen(mob, player, victim, killer).generate());
        }
        items.addAll(new GearLootGen(mob, player, victim, killer).generate());
        items.addAll(new SpellLootGen(mob, player, victim, killer).generate());
        items.addAll(new MapLootGen(mob, player, victim, killer).generate());
        items.addAll(new RuneLootGen(mob, player, victim, killer).generate());
        items.addAll(new RunedGearLootGen(mob, player, victim, killer).generate());
        items.addAll(new LootBoxGen(mob, player, victim, killer).generate());

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get()) {
            items.addAll(new CompatibleItemLootGen(mob, player, victim, killer).generate());
        }

        if (WorldUtils.dropsUniques(victim.world)) {
            items.addAll(new UniqueGearLootGen(mob, player, victim, killer).generate());
        }

        return items;
    }

    public static List<ItemStack> gen(World theworld, float multi, int level) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (level >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get()) {
            items.addAll(new CurrencyLootGen(theworld, multi).generate());
            items.addAll(new AwakenRuneWordLootGen(theworld, multi).generate());
        }

        items.addAll(new GearLootGen(theworld, multi, level).generate());
        items.addAll(new SpellLootGen(theworld, multi, level).generate());
        items.addAll(new MapLootGen(theworld, multi, level).generate());
        items.addAll(new RuneLootGen(theworld, multi, level).generate());
        items.addAll(new RunedGearLootGen(theworld, multi, level).generate());
        items.addAll(new LootBoxGen(theworld, multi).generate());

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get()) {
            items.addAll(new CompatibleItemLootGen(theworld, multi, level).generate());
        }
        if (WorldUtils.dropsUniques(theworld)) {
            items.addAll(new UniqueGearLootGen(theworld, multi, level).generate());

        }

        return items;
    }

    public static void genAndDrop(UnitData mob, UnitData player, EntityLivingBase victim,
                                  EntityPlayer killer) {

        List<ItemStack> items = gen(mob, player, victim, killer);

        for (ItemStack stack : items) {
            victim.entityDropItem(stack, 1F);
        }

    }

    public static List<ItemStack> genAmount(BlockPos pos, int amount, World theworld) {

        int level = LevelUtils.determineLevel(theworld, pos);

        List<ItemStack> loot = new ArrayList<>();

        while (loot.size() < amount) {
            loot.addAll(MasterLootGen.gen(theworld, amount * 10F, level)
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
