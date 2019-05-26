package com.robertx22.blocks.conditions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class NoCondition extends LootCrateCondition {

    @Override
    public boolean canOpenCrate(EntityPlayer player) {
        return true;
    }

    @Override
    public ITextComponent tellCondition() {
        return new TextComponentString("");
    }
}
