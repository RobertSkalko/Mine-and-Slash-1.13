package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.NoEnergyPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class AttackUtils {

    public static void NoEnergyMessage(Entity entity) {

	if (entity instanceof ServerPlayerEntity) {

	    MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) entity);

	}
    }
}
