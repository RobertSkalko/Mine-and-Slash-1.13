package com.robertx22.blocks.conditions;

import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class NoMobAroundCondition extends LootCrateCondition {

    int radius = 1;

    public NoMobAroundCondition(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean canOpenCrate(PlayerEntity player) {

        long entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getBoundingBox()
                .grow(radius))
                .stream()
                .filter(x -> x instanceof LivingEntity && x instanceof PlayerEntity == false && EntityTypeUtils
                        .isMob(x))
                .count();

        return entities < 1;
    }

    @Override
    public ITextComponent tellCondition() {
        return new StringTextComponent("You cannot open this crate while mobs are around.");
    }
}
