package com.robertx22.items.infusions.upgrade;

import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class NormalUpgradeInfusion extends BaseUpgradeInfusion {

    private static final String name = "normal_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    public NormalUpgradeInfusion() {
        super(name);

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public float critOnSuccessChance() {
        return 5F;
    }

    @Override
    public float bonusSuccessChance() {
        return 0;
    }

    @Override
    public float majorFailureChance() {
        return 5F;
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public int Rank() {
        return 1;
    }

}
