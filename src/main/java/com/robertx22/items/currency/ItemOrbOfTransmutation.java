package com.robertx22.items.currency;

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

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemOrbOfTransmutation extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
        return "orb_of_transmutation";
    }

    private static final String name = "orb_of_transmutation";

    @ObjectHolder(Ref.MODID + ":orb_of_transmutation")
    public static final Item ITEM = null;

    public ItemOrbOfTransmutation() {

        super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemOrbOfTransmutation());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.SetSpecificType(gear.gearTypeName);
        gearPrint.minRarity = 1;
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

        return result;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

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
    public int Rank() {
        return 0;
    }
}