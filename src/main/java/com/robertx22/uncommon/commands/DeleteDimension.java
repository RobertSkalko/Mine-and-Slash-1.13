package com.robertx22.uncommon.commands;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.server.command.CommandSetDimension;

public class DeleteDimension extends CommandSetDimension {

    @Override
    public String getName() {
	return "delete";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/delete number_id";
    }

    public static DimensionType testDimensionType;

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	try {
	    int id = Integer.valueOf(args[0]);
	    // EntityPlayer player = (EntityPlayer) sender;

	    if (DimensionManager.isDimensionRegistered(id)) {

		World world = DimensionManager.getWorld(id);

		IWorldData data = world.getCapability(WorldData.Data, null);

		if (data != null && data.isMapWorld()) {
		    data.setDelete(true, world);
		    Main.Network.sendToAll(new WorldPackage(data));

		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}