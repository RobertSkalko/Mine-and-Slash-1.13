package com.robertx22.advanced_blocks.gear_factory_station;

import com.robertx22.mmorpg.Ref;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class StartupGearFactory {
    public static Block blockInventoryAdvanced;
    public static ItemBlock itemBlockInventoryAdvanced;

    public static void preInitCommon() {
	blockInventoryAdvanced = new BlockGearFactory();
	blockInventoryAdvanced.setRegistryName(Ref.MODID + ":gear_factory_station");
	ForgeRegistries.BLOCKS.register(blockInventoryAdvanced);

	itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced, new Properties());
	itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
	ForgeRegistries.ITEMS.register(itemBlockInventoryAdvanced);

	TileEntityType.register(Ref.MODID + ":gear_factory_station_entity",
		TileEntityType.Builder.create(TileGearFactory::new));

	ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
		() -> GuiHandler::getClientGuiElement);

    }

    public static class GuiHandler {

	public static GuiGearFactory getClientGuiElement(FMLPlayMessages.OpenContainer msg) {
	    BlockPos pos = msg.getAdditionalData().readBlockPos();

	    if (Minecraft.getInstance().world.getTileEntity(pos) instanceof TileGearFactory) {
		return new GuiGearFactory((InventoryPlayer) Minecraft.getInstance().player.inventory,
			(TileGearFactory) Minecraft.getInstance().world.getTileEntity(pos));
	    }
	    return null;
	}

    }

}