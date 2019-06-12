package com.robertx22.items.currency;

import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.loot.gens.UniqueGearLootGen;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CreateNewUnique extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IAutoLocMultiLore {

    private static final String GUID = Ref.MODID + ":currency/create_new_unique";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":create_new_unique");
    }

    @Override
    public String GUID() {
        return "create_new_unique";
    }

    public CreateNewUnique() {

        super(GUID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        UniqueBlueprint gearPrint = new UniqueBlueprint(gear.level, gear.uniqueStats.getUniqueItem()
                .Tier(), false);
        gearPrint.SetSpecificRarity(new UniqueItem().Rank());
        gearPrint.LevelRange = false;

        GearItemData newgear = UniqueGearLootGen.CreateData(gearPrint);

        int tries = 0; // in case there's only 1 unique in a tier
        while (newgear.gearTypeName.equals(gear.gearTypeName) && tries < 10) {
            newgear = UniqueGearLootGen.CreateData(gearPrint);
            tries++;
        }

        gear.WriteOverDataThatShouldStay(newgear);

        return UniqueGearLootGen.CreateStack(newgear);
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null && gear.isUnique) {
            return true;
        }

        return false;
    }

    @Override
    public int Tier() {
        return 13;
    }

    @Override
    public int rarity() {
        return 4;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Don't want it? Transform it!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Gem Of Unique Heaven";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform unique (same tier)";
    }
}