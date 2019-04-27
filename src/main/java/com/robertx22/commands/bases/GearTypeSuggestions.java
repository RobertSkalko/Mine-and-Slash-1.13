package com.robertx22.commands.bases;

import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;

import net.minecraft.command.CommandSource;

public class GearTypeSuggestions implements SuggestionProvider<CommandSource> {

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSource> context,
	    SuggestionsBuilder builder) throws CommandSyntaxException {

	for (GearItemSlot slot : GearTypes.All.values()) {
	    builder.suggest(slot.GUID());
	}

	return builder.buildFuture();
    }

}
