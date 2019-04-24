package com.robertx22.mmorpg.gui;

import com.robertx22.advanced_blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.advanced_blocks.gear_factory_station.TileGearFactory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandlerAll {

    public static GuiGearFactory getClientGuiElement(FMLPlayMessages.OpenContainer msg) {
	BlockPos pos = msg.getAdditionalData().readBlockPos();

	if (Minecraft.getInstance().world.getTileEntity(pos) instanceof TileGearFactory) {
	    return new GuiGearFactory((InventoryPlayer) Minecraft.getInstance().player.inventory,
		    (TileGearFactory) Minecraft.getInstance().world.getTileEntity(pos));
	}
	return null;
    }

}