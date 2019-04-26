package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.config.ClientContainer;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.gui.dmg_numbers.OnDisplayDamage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.network.NetworkEvent;

public class DmgNumPacket {

    public int element;
    public String string;
    public double x;
    public double y;
    public double z;
    public float height;
    public boolean isExp;

    public DmgNumPacket() {

    }

    public DmgNumPacket(EntityLivingBase entity, Elements ele, String str) {
	this.element = ele.i;
	this.string = str;
	this.x = entity.posX;
	this.y = entity.posY;
	this.z = entity.posZ;
	this.height = entity.height;

    }

    public static DmgNumPacket decode(PacketBuffer buf) {

	DmgNumPacket newpkt = new DmgNumPacket();

	newpkt.element = buf.getInt(1);
	newpkt.x = buf.getDouble(2);
	newpkt.y = buf.getDouble(3);
	newpkt.z = buf.getDouble(4);
	newpkt.height = buf.getFloat(5);
	newpkt.isExp = buf.getBoolean(6);

	newpkt.string = buf.readTextComponent().toString();

	return newpkt;

    }

    public static void encode(DmgNumPacket packet, PacketBuffer tag) {

	tag.setInt(1, packet.element);
	tag.setDouble(2, packet.x);
	tag.setDouble(3, packet.y);
	tag.setDouble(4, packet.z);
	tag.setFloat(5, packet.height);
	tag.setBoolean(6, packet.isExp);

	tag.writeTextComponent(new TextComponentString(packet.string));

    }

    public static void handle(final DmgNumPacket pkt, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		if (pkt.isExp && ClientContainer.INSTANCE.SHOW_FLOATING_EXP.get()) {
		    OnDisplayDamage.displayParticle(pkt);

		} else if (pkt.isExp == false && ClientContainer.INSTANCE.RENDER_FLOATING_DAMAGE.get()) {
		    OnDisplayDamage.displayParticle(pkt);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	ctx.get().setPacketHandled(true);

    }

}
