package com.robertx22.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.commands.bases.SpellSuggestions;
import com.robertx22.loot.blueprints.SpellBlueprint;
import com.robertx22.loot.create.SpellItemGen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveSpell {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("givespell")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.singlePlayer())
                        .then(Commands.argument("type", StringArgumentType.word())
                                .suggests(new SpellSuggestions())
                                .then(Commands.argument("level", IntegerArgumentType.integer())
                                        .then(Commands.argument("rarity", IntegerArgumentType
                                                .integer(0, 5))
                                                .then(Commands.argument("amount", IntegerArgumentType
                                                        .integer(1, 5000))
                                                        .executes(e -> execute(e.getSource(), EntityArgument
                                                                .getOnePlayer(e, "target"), StringArgumentType
                                                                .getString(e, "type"), IntegerArgumentType
                                                                .getInteger(e, "rarity"), IntegerArgumentType
                                                                .getInteger(e, "level"), IntegerArgumentType
                                                                .getInteger(e, "amount")

                                                        ))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable EntityPlayer player,
                               String type, int lvl, int rarity, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }

        }

        SpellBlueprint blueprint = new SpellBlueprint(lvl);
        if (rarity > -1) {
            blueprint.SetSpecificRarity(rarity);
        }
        if (!type.equals("random")) {
            blueprint.SetSpecificType(type);
        }

        for (int i = 0; i < amount; i++) {
            player.addItemStackToInventory(SpellItemGen.Create(blueprint));
        }

        return 0;
    }
}