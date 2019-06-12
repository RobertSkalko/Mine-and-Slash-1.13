package com.robertx22.items.infusions.upgrade;

import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class WondrousUpgradeInfusion extends BaseUpgradeInfusion {

    public WondrousUpgradeInfusion() {
        super(name);
    }

    private static final String name = "wondrous_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public float critOnSuccessChance() {
        return 20F;
    }

    @Override
    public float bonusSuccessChance() {
        return 15F;
    }

    @Override
    public float majorFailureChance() {
        return 3F;
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public int rarity() {
        return 3;
    }

}
