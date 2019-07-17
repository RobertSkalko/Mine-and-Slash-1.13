package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public interface ISalvagable {

    ItemStack getSalvageResult(float salvageBonus);

    int getSalvagedRarity();

    boolean isSalvagable();

    public default int tryIncreaseAmount(float salvageBonus, int amount) {

        if (RandomUtils.roll(salvageBonus)) {
            return amount + 1;
        }

        return amount;
    }
}
