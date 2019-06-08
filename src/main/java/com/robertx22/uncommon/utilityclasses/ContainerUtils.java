package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ContainerUtils {

    public static int windowId(PlayerInventory inv) {

        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) inv.player;
        serverPlayer.getNextWindowId();
        return serverPlayer.currentWindowId;
    }
}
