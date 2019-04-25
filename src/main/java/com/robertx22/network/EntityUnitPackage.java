package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.Main;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class EntityUnitPackage {

    public int id;
    public NBTTagCompound nbt;

    public EntityUnitPackage() {

    }

    public EntityUnitPackage(Entity entity) {
	this.id = entity.getEntityId();
	this.nbt = Load.Unit(entity).getNBT();
    }

    public EntityUnitPackage(Entity entity, UnitData data) {
	this.id = entity.getEntityId();
	this.nbt = data.getNBT();
    }

    public static EntityUnitPackage decode(PacketBuffer buf) {

	EntityUnitPackage newpkt = new EntityUnitPackage();

	newpkt.id = buf.getInt(0);
	newpkt.nbt = buf.readCompoundTag();

	return newpkt;

    }

    public static void encode(EntityUnitPackage packet, PacketBuffer tag) {

	tag.setInt(0, packet.id);
	tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final EntityUnitPackage pkt, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		final EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);

		if (player != null && player.world != null) {
		    Entity entity = player.world.getEntityByID(pkt.id);

		    if (entity instanceof EntityLivingBase) {

			EntityLivingBase en = (EntityLivingBase) entity;

			Load.Unit(en).setNBT(pkt.nbt);
		    }
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	ctx.get().setPacketHandled(true);

    }

}
