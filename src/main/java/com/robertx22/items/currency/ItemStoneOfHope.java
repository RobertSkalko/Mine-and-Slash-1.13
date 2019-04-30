package com.robertx22.items.currency;

import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.create.GearGen;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemStoneOfHope extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
        return "stone_of_hope";
    }

    private static final String name = "stone_of_hope";

    @ObjectHolder(Ref.MODID + ":stone_of_hope")
    public static final Item ITEM = null;

    public ItemStoneOfHope() {

        super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemStoneOfHope());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.SetSpecificType(gear.gearTypeName);
        gearPrint.minRarity = gear.Rarity + 1;
        gearPrint.LevelRange = false;

        GearItemData newgear = GearGen.CreateData(gearPrint);
        gear.WriteOverDataThatShouldStay(newgear);

        ItemStack result = ItemStack.EMPTY;

        if (gear.changesItemStack()) {
            result = GearGen.CreateStack(newgear);
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