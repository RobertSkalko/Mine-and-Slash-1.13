package com.robertx22.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.commands.SetLevel;
import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class CommandRegister {
    public static void Register(final FMLServerStartingEvent event) {
        net.minecraft.command.Commands commands = event.getServer().getCommandManager();
        CommandDispatcher<CommandSource> dispatcher = commands.getDispatcher();

        SetLevel.register(dispatcher);
        AddExp.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);
        SetLevel.register(dispatcher);

    }
}