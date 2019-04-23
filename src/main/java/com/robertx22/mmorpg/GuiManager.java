package com.robertx22.mmorpg;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiManager {
    public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer) {
	BlockPos pos = openContainer.getAdditionalData().readBlockPos();

	for (GUIChest.GUI type : GUIChest.GUI.values()) {
	    if (type.getGuiId().equals(openContainer.getId())) {
		return new GUIChest(type, (IInventory) Minecraft.getInstance().player.inventory,
			(IInventory) Minecraft.getInstance().world.getTileEntity(pos));
	    }
	}

	return null;
    }
}
