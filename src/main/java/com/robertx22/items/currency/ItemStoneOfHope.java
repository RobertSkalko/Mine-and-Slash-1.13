package com.robertx22.items.currency;

import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.uncommon.interfaces.IRenamed;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemStoneOfHope extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "stone_of_hope";
    }

    private static final String name = Ref.MODID + ":currency/stone_of_hope";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":stone_of_hope");
    }

    public ItemStoneOfHope() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.SetSpecificType(gear.gearTypeName);
        gearPrint.minRarity = gear.Rarity + 1;
        gearPrint.LevelRange = false;

        GearItemData newgear = GearLootGen.CreateData(gearPrint);
        gear.WriteOverDataThatShouldStay(newgear);

        ItemStack result = ItemStack.EMPTY;

        if (gear.changesItemStack()) {
            result = GearLootGen.CreateStack(newgear);
        } else {
            result = stack;
            Gear.Save(result, newgear);
        }

        return stack;

    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null && gear.Rarity < Rarities.MAXIMUM_ITEM_RARITY) {
            return true;
        }

        return false;
    }

    @Override
    public int Rank() {
        return 4;
    }

    @Override
    public int Tier() {
        return 2;
    }

}