package com.robertx22.loot;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.gens.*;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
