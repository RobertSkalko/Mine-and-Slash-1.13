package com.robertx22.blocks.bases;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class BaseTileContainer extends Container {

    public BlockPos pos;

    protected BaseTileContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
}
