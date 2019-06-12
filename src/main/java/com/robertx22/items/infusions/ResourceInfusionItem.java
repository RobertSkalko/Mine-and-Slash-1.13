package com.robertx22.items.infusions;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.*;
import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

public class ResourceInfusionItem extends BaseInfusionItem {

    public ResourceInfusionItem() {
        super(name);

    }

    private static final String name = "resource_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public List<StatMod> weaponInfusions() {
        return Arrays.asList(new ManaOnHitFlat(), new LifeOnHitFlat(), new LifestealFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
        return Arrays.asList(new ManaRegenFlat(), new EnergyRegenFlat(), new HealthRegenFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
        return Arrays.asList(new ManaFlat(), new HealthRegenFlat());
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Infuses an item with Resource Modifiers");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Resource Infusion";
    }
}
