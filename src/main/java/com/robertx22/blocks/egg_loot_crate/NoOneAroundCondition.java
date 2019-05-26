package com.robertx22.blocks.egg_loot_crate;

import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class NoOneAroundCondition extends LootCrateCondition {

    @Override
    public boolean canOpenCrate(EntityPlayer player) {

        long entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getBoundingBox()
                .grow(5))
                .stream()
                .filter(x -> x instanceof EntityLivingBase && x instanceof EntityPlayer == false && EntityTypeUtils
                        .isMob(x))
                .count();

        return entities < 1;
    }

    @Override
    public ITextComponent tellCondition() {
        return new TextComponentString("You cannot open this crate while mobs are around.");
    }
}
