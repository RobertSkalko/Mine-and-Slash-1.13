package com.robertx22.items.currency;

import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemOrbOfTransmutation extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "orb_of_transmutation";
    }

    public static final String ID = Ref.MODID + ":currency/orb_of_transmutation";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":orb_of_transmutation");
    }

    public ItemOrbOfTransmutation() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.SetSpecificType(gear.gearTypeName);
        gearPrint.minRarity = 1;
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

        return result;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null && gear.Rarity == 0) {
            return true;
        }

        return false;
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int rarity() {
        return 0;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Turn trash into treasure!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Transmutation";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform Common Item";
    }
}