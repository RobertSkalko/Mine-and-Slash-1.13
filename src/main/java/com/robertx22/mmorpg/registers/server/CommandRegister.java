package com.robertx22.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.commands.entity.*;
import com.robertx22.commands.giveitems.*;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;

public class CommandRegister {

    public static void Register(MinecraftServer server) {
        System.out.println("Registering Mine and Slash Commands.");

        CommandDispatcher<CommandSource> dispatcher = server.getCommandManager()
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
        SetEntityLevel.register(dispatcher);
        SetEntityRarity.register(dispatcher);
        GiveStat.register(dispatcher);
        RemoveStat.register(dispatcher);

    }
}