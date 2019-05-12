package com.robertx22.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public class GiveExp {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giveexp")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("exp", IntegerArgumentType.integer())
                                .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"), IntegerArgumentType
                                        .getInteger(ctx, "exp"))))));
    }

    private static int run(@Nullable EntityPlayer player, int exp) {

        try {
            Load.Unit(player).PostGiveExpEvent(player, exp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
