package com.robertx22.mmorpg.gui;

import com.robertx22.blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.blocks.gear_factory_station.TileGearFactory;
import com.robertx22.blocks.item_modify_station.GuiInventoryModify;
import com.robertx22.blocks.item_modify_station.TileInventoryModify;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandlerClient {

    public static GuiScreen getClientGuiElement(FMLPlayMessages.OpenContainer msg) {

	EntityPlayer player = Minecraft.getInstance().player;

	switch (msg.getId().getPath()) {
	case "gear_factory_station": {
	    BlockPos pos = msg.getAdditionalData().readBlockPos();
	    TileEntity te = Minecraft.getInstance().world.getTileEntity(pos);
	    if (te instanceof TileGearFactory) {
		return new GuiGearFactory((InventoryPlayer) player.inventory, (TileGearFactory) te);
	    }
	    break;
	}
	case "modify_station": {
	    BlockPos pos = msg.getAdditionalData().readBlockPos();
	    TileEntity te = Minecraft.getInstance().world.getTileEntity(pos);
	    if (te instanceof TileInventoryModify) {
		return new GuiInventoryModify((InventoryPlayer) player.inventory, (TileInventoryModify) te);
	    }
	    break;
	}

	}

	return null;
    }

}