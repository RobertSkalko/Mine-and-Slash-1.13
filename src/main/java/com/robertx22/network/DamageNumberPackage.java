package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.gui.dmg_numbers.OnDisplayDamage;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.network.NetworkEvent;

public class DamageNumberPackage {

    public int element;
    public String string;
    public double x;
    public double y;
    public double z;
    public float height;
    public boolean isExp;

    public DamageNumberPackage() {

    }

    public DamageNumberPackage(EntityLivingBase entity, Elements ele, String str) {
	this.element = ele.i;
	this.string = str;
	this.x = entity.posX;
	this.y = entity.posY;
	this.z = entity.posZ;
	this.height = entity.height;

    }

    public void fromBytes(DamageNumberPackage packet, ByteBuf buf) {

	element = buf.getInt(1);
	x = buf.getDouble(2);
	y = buf.getDouble(3);
	z = buf.getDouble(4);
	height = buf.getFloat(5);
	string = buf.getString(6);
	isExp = buf.getBoolean(7);

    }

    @Override
    public void toBytes(ByteBuf tag) {

	tag.setInt(1, element);
	tag.putDouble("x", x);
	tag.putDouble("y", y);
	tag.putDouble("z", z);
	tag.putFloat("height", height);
	tag.putString("string", string);
	tag.setBoolean("isExp", isExp);
	ByteBufUtils.writeTag(buf, tag);

    }

    public static class Handler {

	public void onMessage(final DamageNumberPackage pkt, Supplier<NetworkEvent.Context> ctx) {

	    ctx.get().enqueueWork(() -> {
		try {

		    if (pkt.isExp && ModConfig.Client.SHOW_FLOATING_EXP) {
			OnDisplayDamage.displayParticle(pkt);

		    } else if (pkt.isExp == false && ModConfig.Client.RENDER_FLOATING_DAMAGE) {
			OnDisplayDamage.displayParticle(pkt);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    });

	    ctx.get().setPacketHandled(true);

	}
    }

}
