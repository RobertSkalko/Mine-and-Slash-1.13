package com.robertx22.blocks.bases;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public abstract class BaseTileContainer extends Container {

    protected BaseTileContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
}
