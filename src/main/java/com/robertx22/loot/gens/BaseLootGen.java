package com.robertx22.loot.gens;

import com.robertx22.loot.LootInfo;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLootGen {

    public abstract float BaseChance();

    public boolean hasLevelDistancePunishment() {
        return true;
    }

    protected abstract ItemStack generateOne();

    public List<ItemStack> generate() {

        List<ItemStack> list = new ArrayList<ItemStack>();

        for (int i = 0; i < info.amount; i++) {
            try {
                list.add(generateOne());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public LootInfo info;

    public BaseLootGen(LootInfo info) {
        this.info = info;
        this.info.setup(this);
    }

}
