package com.robertx22.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.commands.bases.GearTypeSuggestions;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.loot.create.UniqueGearGen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveUnique {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giveunique")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("curioTypeName", StringArgumentType.word())
                                .suggests(new GearTypeSuggestions())
                                .then(Commands.argument("level", IntegerArgumentType.integer())
                                        .then(Commands.argument("tier", IntegerArgumentType
                                                .integer(0, 30))
                                                .then(Commands.argument("amount", IntegerArgumentType
                                                        .integer(1, 5000))
                                                        .executes(e -> execute(e.getSource(), EntityArgument
                                                                .getPlayer(e, "target"), StringArgumentType
                                                                .getString(e, "curioTypeName"), IntegerArgumentType
                                                                .getInteger(e, "level"), IntegerArgumentType
                                                                .getInteger(e, "tier"), IntegerArgumentType
                                                                .getInteger(e, "amount")

                                                        ))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable EntityPlayer player,
                               String type, int lvl, int tier, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        UniqueBlueprint blueprint = new UniqueBlueprint(lvl, tier, true);
        blueprint.SetSpecificRarity(new UniqueItem().Rank());
        blueprint.SetSpecificType(type);

        for (int i = 0; i < amount; i++) {
            player.addItemStackToInventory(UniqueGearGen.CreateStack(blueprint));
        }

        return 0;
    }
}
