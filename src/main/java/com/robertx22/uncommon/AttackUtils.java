package com.robertx22.uncommon;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.NoEnergyPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class AttackUtils {

    public static void NoEnergyMessage(Entity entity) {

	if (entity instanceof EntityPlayerMP) {

	    Main.sendToClient(new NoEnergyPacket(), (EntityPlayerMP) entity);

	}
    }
}
