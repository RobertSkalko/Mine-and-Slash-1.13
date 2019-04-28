package com.robertx22.commands;

import java.util.Objects;
import javax.annotation.Nullable;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.commands.bases.GearTypeSuggestions;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.create.GearGen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;

public class GiveGear {

  public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
    commandDispatcher.register(Commands.literal("givegear").requires(e -> e.hasPermissionLevel(2))
        .then(Commands.argument("target", EntityArgument.singlePlayer()).then(Commands
            .argument("level", IntegerArgumentType.integer())
            .then(Commands.argument("rarity", IntegerArgumentType.integer(0, 5)).then(Commands
                .argument("type", StringArgumentType.word()).suggests(new GearTypeSuggestions())
                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 5000))

                    .executes(e -> execute(e.getSource(), EntityArgument.getOnePlayer(e, "target"),
                        IntegerArgumentType.getInteger(e, "rarity"),
                        IntegerArgumentType.getInteger(e, "level"),
                        StringArgumentType.getString(e, "type"),
                        IntegerArgumentType.getInteger(e, "amount")

                    ))))))));
  }

  private static int execute(CommandSource commandSource, @Nullable EntityPlayer player, int lvl,
      int rarity, String type, int amount) {

    if (Objects.isNull(player)) {
      try {
        player = commandSource.asPlayer();
      } catch (CommandSyntaxException e) {
        e.printStackTrace();
        return 1;
      }

      GearBlueprint blueprint = new GearBlueprint(lvl);
      if (rarity > -1) {
        blueprint.SetSpecificRarity(rarity);
      }
      if (!type.equals("random")) {
        blueprint.SetSpecificType(type);
      }
      blueprint.LevelRange = false;

      for (int i = 0; i < amount; i++) {
        player.addItemStackToInventory(GearGen.CreateStack(blueprint));
      }

    }

    return 0;
  }
}
