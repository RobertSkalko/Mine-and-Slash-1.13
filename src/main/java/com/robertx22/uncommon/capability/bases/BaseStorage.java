package com.robertx22.uncommon.capability.bases;

import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class BaseStorage<TYPE extends ICommonCapability> implements Capability.IStorage<TYPE> {

    @Override
    public INBTBase writeNBT(Capability<TYPE> capability, TYPE instance,
                             EnumFacing side) {

        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<TYPE> capability, TYPE instance, EnumFacing side,
                        INBTBase nbt) {

        instance.setNBT((NBTTagCompound) nbt);

    }

}