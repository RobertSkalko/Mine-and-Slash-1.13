package com.robertx22.blocks.conditions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

public abstract class LootCrateCondition {

    public abstract boolean canOpenCrate(PlayerEntity player);

    public abstract ITextComponent tellCondition();
}
