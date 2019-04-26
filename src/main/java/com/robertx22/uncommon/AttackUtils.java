package com.robertx22.uncommon;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.NoEnergyPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class AttackUtils {

    public static void NoEnergyMessage(Entity entity) {

	if (entity instanceof EntityPlayerMP) {

	    MMORPG.sendToClient(new NoEnergyPacket(), (EntityPlayerMP) entity);

	}
    }
}
