package com.robertx22.commands;

import java.util.Objects;

import javax.annotation.Nullable;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

public class GiveExp {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
	commandDispatcher.register(Commands.literal("giveexp").requires(e -> e.hasPermissionLevel(2))
		.then(Commands.argument("target", EntityArgument.singlePlayer())
			.then(Commands.argument("exp", IntegerArgumentType.integer()))
			.executes(e -> execute(e.getSource(), EntityArgument.getOnePlayer(e, "target"),
				IntegerArgumentType.getInteger(e, "exp")))));
    }

    private static int execute(CommandSource commandSource, @Nullable EntityPlayer player, int exp) {
	if (Objects.isNull(player)) {
	    try {
		player = commandSource.asPlayer();
	    } catch (CommandSyntaxException e) {
		e.printStackTrace();
		return 1;
	    }

	    Load.Unit(player).GiveExp(player, exp);
	}

	return 0;
    }
}
