package com.robertx22.advanced_blocks.repair_station;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.gui.GuiHandler;
import com.robertx22.mmorpg.gui.GuiHandlerRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * User: brandon3055 Date: 06/01/2015
 *
 * The Startup class for this example is called during startup, in the following
 * order: preInitCommon preInitClientOnly initCommon initClientOnly
 * postInitCommon postInitClientOnly See MinecraftByExample class for more
 * information
 */
public class StartupRepair {
    public static Block blockInventoryAdvanced; // this holds the unique instance of your block
    public static ItemBlock itemBlockInventoryAdvanced; // this holds the unique instance of the ItemBlock corresponding
							// to your block

    public static void preInitCommon() {
	blockInventoryAdvanced = new BlockInventoryRepair();
	blockInventoryAdvanced.setRegistryName(Ref.MODID + ":repair_station");
	ForgeRegistries.BLOCKS.register(blockInventoryAdvanced);

	// We also need to create and register an ItemBlock for this block otherwise it
	// won't appear in the inventory
	itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced);
	itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
	ForgeRegistries.ITEMS.register(itemBlockInventoryAdvanced);

	// Each of your tile entities needs to be registered with a name that is unique
	// to your mod.
	GameRegistry.registerTileEntity(TileInventoryRepair.class, Ref.MODID + ":repair_station_entity");

	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

    }

}