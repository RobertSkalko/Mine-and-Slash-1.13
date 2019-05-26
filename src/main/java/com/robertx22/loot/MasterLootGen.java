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

    private static List<ItemStack> generateWithInfo(LootInfo info) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (info == null) {
            return items;
        }

        if (info.level >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get()) {
            items.addAll(new CurrencyLootGen(info).generate());
            items.addAll(new AwakenRuneWordLootGen(info).generate());
        }
        items.addAll(new GearLootGen(info).generate());
        items.addAll(new SpellLootGen(info).generate());
        items.addAll(new MapLootGen(info).generate());
        items.addAll(new RuneLootGen(info).generate());
        items.addAll(new RunedGearLootGen(info).generate());
        items.addAll(new LootBoxGen(info).generate());

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get()) {
            items.addAll(new CompatibleItemLootGen(info).generate());
        }

        if (WorldUtils.dropsUniques(info.world)) {
            items.addAll(new UniqueGearLootGen(info).generate());
        }

        return items;
    }

    public static List<ItemStack> gen(EntityPlayer player, int level) {
        LootInfo info = new LootInfo(player, 1);
        List<ItemStack> items = generateWithInfo(info);

        return items;
    }

    public static List<ItemStack> gen(UnitData mob, UnitData player,
                                      EntityLivingBase victim, EntityPlayer killer) {

        LootInfo info = new LootInfo(mob, player, victim, killer);
        List<ItemStack> items = generateWithInfo(info);

        return items;
    }

    public static List<ItemStack> gen(World theworld, int level, float multi) {
        LootInfo info = new LootInfo(theworld, level, multi);
        List<ItemStack> items = generateWithInfo(info);

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
            loot.addAll(MasterLootGen.gen(theworld, level, amount * 10F)
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
