package com.robertx22.uncommon.capability.bases;

import net.minecraft.nbt.CompoundNBT;

public interface ICommonCapability {

    CompoundNBT getNBT();

    void setNBT(CompoundNBT value);
}
