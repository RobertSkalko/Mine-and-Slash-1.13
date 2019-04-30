package com.robertx22.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.commands.GiveExp;
import com.robertx22.commands.SetLevel;
import com.robertx22.commands.giveitems.*;
import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class CommandRegister {
    public static void Register(FMLServerStartingEvent event) {
        System.out.println("Registering Mine and Slash Commands.");

        CommandDispatcher<CommandSource> dispatcher = event.getServer()
                .getCommandManager()
                .getDispatcher();

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