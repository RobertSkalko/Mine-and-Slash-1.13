package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public interface ISalvagable {

    public enum SalvageContext {
        SALVAGE_STATION,
        AUTO_SALVAGE_BAG

    }

    ItemStack getSalvageResult(float salvageBonus);

    int getSalvagedRarity();

    boolean isSalvagable(SalvageContext context);

    public default int tryIncreaseAmount(float salvageBonus, int amount) {

        if (RandomUtils.roll(salvageBonus)) {
            return amount + 1;
        }

        return amount;
    }
}
