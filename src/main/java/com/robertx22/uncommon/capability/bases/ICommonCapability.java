package com.robertx22.uncommon.capability.bases;

import net.minecraft.nbt.NBTTagCompound;

public interface ICommonCapability {

    NBTTagCompound getNBT();

    void setNBT(NBTTagCompound value);
}
