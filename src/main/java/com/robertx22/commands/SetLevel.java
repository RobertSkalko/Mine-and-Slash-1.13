package com.robertx22.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.Objects;

public class SetLevel {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("setlevel")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.singlePlayer())
                        .then(Commands.argument("level", IntegerArgumentType.integer())
                                .executes(e -> execute(e.getSource(), EntityArgument.getOnePlayer(e, "target"), IntegerArgumentType
                                        .getInteger(e, "level"))))));
    }

    private static int execute(CommandSource commandSource, @Nullable EntityPlayer player,
                               int lvl) {
        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }
        
        EntityData.UnitData data = Load.Unit(player);

        data.setLevel(lvl, player);

        return 0;
    }
}
