package com.robertx22.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.loot.blueprints.RuneBlueprint;
import com.robertx22.loot.create.RuneGen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveRune {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giverune")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.singlePlayer())
                        .then(Commands.argument("level", IntegerArgumentType.integer())
                                .then(Commands.argument("rarity", IntegerArgumentType.integer(0, 5))
                                        .then(Commands.argument("amount", IntegerArgumentType
                                                .integer(1, 30000))

                                                .executes(e -> run(e.getSource(), EntityArgument
                                                        .getOnePlayer(e, "target"), IntegerArgumentType
                                                        .getInteger(e, "level"), IntegerArgumentType
                                                        .getInteger(e, "rarity"), IntegerArgumentType
                                                        .getInteger(e, "amount")

                                                )))))));
    }

    private static int run(CommandSource commandSource, @Nullable EntityPlayer player,
                           int lvl, int rarity, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        for (int i = 0; i < amount; i++) {
            RuneBlueprint blueprint = new RuneBlueprint(lvl);
            if (rarity > -1) {
                blueprint.SetSpecificRarity(rarity);
            }

            blueprint.LevelRange = false;

            player.addItemStackToInventory(RuneGen.Create(blueprint));
        }

        return 0;
    }
}
