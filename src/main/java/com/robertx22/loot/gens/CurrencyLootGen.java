package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.interfaces.ITiered;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CurrencyLootGen extends BaseLootGen {

    public CurrencyLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                           EntityPlayer killer) {
        super(mob, player, victim, killer);

    }

    public CurrencyLootGen(World theworld, float multi) {
        super(theworld, multi);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.CURRENCY_DROPRATE.get().floatValue();

    }

    @Override
    public ItemStack generateOne() {

        return new ItemStack((Item) RandomUtils.WeightedRandom(ListUtils.SameTierOrLess(new ArrayList<ITiered>(CurrencyItem.ITEMS), this.tier)));

    }

}
