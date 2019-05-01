package com.robertx22.network;

import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class EntityUnitPacket {

    public int id;
    public NBTTagCompound nbt;

    public EntityUnitPacket() {

    }

    public EntityUnitPacket(Entity entity) {
        this.id = entity.getEntityId();
        this.nbt = Load.Unit(entity).getNBT();
    }

    public EntityUnitPacket(Entity entity, UnitData data) {
        this.id = entity.getEntityId();
        this.nbt = data.getNBT();
    }

    public static EntityUnitPacket decode(PacketBuffer buf) {

        EntityUnitPacket newpkt = new EntityUnitPacket();

        newpkt.id = buf.readInt();
        newpkt.nbt = buf.readCompoundTag();

        return newpkt;

    }

    public static void encode(EntityUnitPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.id);
        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final EntityUnitPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                Entity entity = Minecraft.getInstance().world.getEntityByID(pkt.id);

                if (entity instanceof EntityLivingBase) {

                    EntityLivingBase en = (EntityLivingBase) entity;

                    Load.Unit(en).setNBT(pkt.nbt);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
