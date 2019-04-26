package com.robertx22.items.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.db_lists.Rarities;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemCapacitor extends Item {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemCapacitor(int rarity) {

	super(new Properties().group(CreativeTabs.CurrencyTab));

	this.rarity = rarity;

	RegisterItemUtils.RegisterItemName(this, "capacitor" + rarity);
    }

    int rarity;

    public List<Float> RepairValues = Arrays.asList(0.95F, 0.9F, 0.8F, 0.7F, 0.6F, 0.5F);

    public Float GetFuelMultiplier() {

	return RepairValues.get(rarity);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
	    ITooltipFlag flagIn) {

	Tooltip.add(CLOC.tooltip("capacitor"), tooltip);

	Tooltip.add(CLOC.tooltip("capacitor2") + ": " + this.GetFuelMultiplier() + "x", tooltip);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemCapacitor(x.Rank())));
	Items.values().forEach((x) -> event.getRegistry().register(x));
    }

}
