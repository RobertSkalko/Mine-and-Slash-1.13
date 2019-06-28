package com.robertx22.uncommon.datasaving;

import com.robertx22.items.bags.master_bag.MasterLootBagItemsData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class MasterLootBagSaving {
    private static final String LOC = "MASTER_LOOT_BAG_DATA";

    public static MasterLootBagItemsData Load(CompoundNBT stack) {

        if (stack == null) {
            return null;
        }

        return LoadSave.Load(MasterLootBagItemsData.class, new MasterLootBagItemsData(), stack, LOC);

    }

    public static void Save(CompoundNBT stack, MasterLootBagItemsData gear) {

        if (stack == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, stack, LOC);
        }

    }

}
