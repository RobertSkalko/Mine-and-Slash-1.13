package com.robertx22.items.currency;

import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.SetData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddSet extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return "add_set";
    }

    private static final String name = Ref.MODID + ":currency/add_set";

    public ItemAddSet() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.set = new SetData();

        GearBlueprint blueprint = new GearBlueprint(gear.level);
        gear.set = blueprint.tryGenerateSet(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        GearBlueprint blueprint = new GearBlueprint(gear.level);
        blueprint.SetCustomSetChance(100);

        if (blueprint.tryGenerateSet(gear) == null) {
            return false;
        }

        return gear.canGetSet() && gear.set == null;
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public int rarity() {
        return 5;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Become one.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Key of Unity";
    }

    @Override
    public String locDescForLangFile() {
        return "Adds a set to an item";
    }
}
