package com.robertx22.blocks.egg_loot_crate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;

public abstract class LootCrateCondition {

    public abstract boolean canOpenCrate(EntityPlayer player);

    public abstract ITextComponent tellCondition();
}
