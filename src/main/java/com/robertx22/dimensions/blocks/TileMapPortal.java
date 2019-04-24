package com.robertx22.dimensions.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileMapPortal extends TileEntity {

    public int id;

    public TileMapPortal(int id) {
	super(type);
	this.id = id;
    }

    public TileMapPortal() {

    }

    int ticks = 0;

    public void ontick() {
	ticks++;
    }

    public boolean readyToTeleport() {

	if (ticks > 80) {
	    ticks = 0;
	    return true;
	}
	return false;

    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderFace(EnumFacing face) {
	return face == EnumFacing.UP;
    }

    @Override
    public void read(NBTTagCompound nbt) {
	super.read(nbt);

	id = nbt.getInt("dim_Id");
	ticks = nbt.getInt("ticks");
    }

    @Override
    public NBTTagCompound write(NBTTagCompound nbt) {
	super.write(nbt); // The super call is required to save and load the tile loc

	nbt.putInt("dim_Id", id);
	nbt.putInt("ticks", ticks);

	return nbt;
    }

}
