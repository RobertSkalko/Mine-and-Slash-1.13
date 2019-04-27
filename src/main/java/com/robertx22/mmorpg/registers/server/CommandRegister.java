package com.robertx22.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.commands.SetLevel;

import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class CommandRegister {
    public static void Register(final FMLDedicatedServerSetupEvent event) {
	net.minecraft.command.Commands commands = event.getServerSupplier().get().getCommandManager();
	CommandDispatcher<CommandSource> dispatcher = commands.getDispatcher();

	SetLevel.register(dispatcher);
    }
}