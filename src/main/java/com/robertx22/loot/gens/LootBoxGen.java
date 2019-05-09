package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.misc.ItemLootbox;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LootBoxGen extends BaseLootGen {

    public LootBoxGen(UnitData mob, UnitData player, IWorldData world,
                      EntityLivingBase victim) {
        super(mob, player, world, victim);

    }

    public LootBoxGen(World theworld, float multi, IWorldData world) {
        super(theworld, multi, world);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.LOOTBOX_DROPRATE.get().floatValue();

    }

    @Override
    public ItemStack generateOne() {

        return new ItemStack((Item) RandomUtils.WeightedRandom(ListUtils.CollectionToList(ItemLootbox.Items
                .values())));

    }

}