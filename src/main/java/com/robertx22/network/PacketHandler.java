package com.robertx22.network;

import com.robertx22.mmorpg.Main;

public class PacketHandler {
    public static void register() {
	int disc = 0;

	Main.Network.registerMessage(disc++, DamageNumberPackage.class, DamageNumberPackage::encode,
		DamageNumberPackage::decode, DamageNumberPackage::handle);

    }
}
