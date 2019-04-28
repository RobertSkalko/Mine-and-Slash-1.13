package com.robertx22.commands;

import javax.annotation.Nullable;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

public class GiveExp {


  public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
    commandDispatcher.register(Commands.literal("giveexp").requires(e -> e.hasPermissionLevel(0))
        .then(Commands.argument("target", EntityArgument.singlePlayer())
            .then(Commands.argument("exp", IntegerArgumentType.integer())
                .executes(ctx -> run(EntityArgument.getOnePlayer(ctx, "target"),
                    IntegerArgumentType.getInteger(ctx, "exp"))))));
  }

  private static int run(@Nullable EntityPlayer player, int exp) {

    try {
      Load.Unit(player).GiveExp(player, exp);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return 1;
  }
}
