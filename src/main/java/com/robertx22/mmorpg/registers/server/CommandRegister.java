package com.robertx22.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.commands.GiveExp;
import com.robertx22.commands.SetLevel;
import com.robertx22.commands.giveitems.*;
import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class CommandRegister {
    public static void Register(final FMLServerStartingEvent event) {
        net.minecraft.command.Commands commands = event.getServer().getCommandManager();
        CommandDispatcher<CommandSource> dispatcher = commands.getDispatcher();

        SetLevel.register(dispatcher);
        GiveExp.register(dispatcher);
        GiveAwakenRuneword.register(dispatcher);
        GiveExactUnique.register(dispatcher);
        GiveGear.register(dispatcher);
        GiveMap.register(dispatcher);
        GiveRune.register(dispatcher);
        GiveRunedGear.register(dispatcher);
        GiveSpell.register(dispatcher);
        GiveUnique.register(dispatcher);

    }
}