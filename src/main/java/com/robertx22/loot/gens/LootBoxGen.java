package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.misc.ItemLootbox;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LootBoxGen extends BaseLootGen {

    public LootBoxGen(UnitData mob, UnitData player, EntityLivingBase victim,
                      EntityPlayer killer) {
        super(mob, player, victim, killer);

    }

    public LootBoxGen(World theworld, float multi) {
        super(theworld, multi);
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