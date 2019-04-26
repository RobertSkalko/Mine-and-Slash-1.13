package com.robertx22.items.ores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.blocks.BlockOre;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemOre extends Item implements IWeighted {
    public static HashMap<Integer, Item> ItemOres = new HashMap<Integer, Item>();
    public static HashMap<Integer, ItemBlock> ItemBlocks = new HashMap<Integer, ItemBlock>();
    public static HashMap<Integer, Block> Blocks = new HashMap<Integer, Block>();

    int rarity;

    public List<Integer> RepairValues = Arrays.asList(20, 30, 75, 125, 300, 600);

    public int GetFuelValue() {

	return RepairValues.get(rarity);

    }

    @Override
    public int Weight() {
	return Rarities.Items.get(rarity).Weight();
    }

    public ItemOre(String name, int rarity) {

	super(new Properties().group(CreativeTabs.CurrencyTab));

	RegisterItemUtils.RegisterItemName(this, name);

	this.rarity = rarity;

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
	    ITooltipFlag flagIn) {

	Tooltip.add(CLOC.tooltip("ore"), tooltip);

    }

    public static void Register() {
	Rarities.Items.forEach((x) -> ItemOres.put(x.Rank(), new ItemOre("ore" + x.Rank(), x.Rank())));

	for (int i = 0; i < ItemOres.size(); i++) {
	    BlockOre block = new BlockOre("ore_block" + i, Material.ROCK, ItemOres.get(i), 1);
	    Blocks.put(i, block);
	    ItemBlock itemblock = (ItemBlock) new ItemBlock(block, new Properties()).setRegistryName("ore_block" + i);
	    ItemBlocks.put(i, itemblock);
	}

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	ItemOres.values().forEach((x) -> event.getRegistry().register(x));
	ItemBlocks.values().forEach((x) -> event.getRegistry().register(x));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
	Blocks.values().forEach((x) -> event.getRegistry().register(x));

    }

}
