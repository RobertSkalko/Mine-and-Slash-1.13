package com.robertx22.loot;

import com.robertx22.loot.gens.*;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MasterLootGen {

    public static List<ItemStack> generateWithInfo(LootInfo info) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (info == null) {
            return items;
        }

        items = generateWithInfoNoWhile(info);

        while (items.size() < info.minItems) {

            List<ItemStack> extra = generateWithInfoNoWhile(info);

            int missing = info.minItems - items.size();

            for (int i = 0; i < extra.size() && i < missing; i++) {
                items.add(extra.get(i));
            }

        }

        while (items.size() > info.maxItems) {
            items.remove(0);
        }

        return items;
    }

    private static List<ItemStack> generateWithInfoNoWhile(LootInfo info) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        if (info == null) {
            return items;
        }

        items.addAll(new CurrencyLootGen(info).tryGenerate());
        items.addAll(new AwakenRuneWordLootGen(info).tryGenerate());
        items.addAll(new GearLootGen(info).tryGenerate());
        items.addAll(new SpellLootGen(info).tryGenerate());
        items.addAll(new MapLootGen(info).tryGenerate());
        items.addAll(new RuneLootGen(info).tryGenerate());
        items.addAll(new RunedGearLootGen(info).tryGenerate());
        items.addAll(new LootBoxGen(info).tryGenerate());
        items.addAll(new CompatibleItemLootGen(info).tryGenerate());
        items.addAll(new UniqueGearLootGen(info).tryGenerate());

        return items.stream()
                .filter(x -> x.isEmpty() == false)
                .collect(Collectors.toList());
    }

    public static List<ItemStack> gen(UnitData mob, UnitData player,
                                      EntityLivingBase victim, EntityPlayer killer) {

        LootInfo info = new LootInfo(mob, player, victim, killer);
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

}
