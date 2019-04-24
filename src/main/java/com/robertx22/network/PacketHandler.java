package com.robertx22.network;

import com.robertx22.mmorpg.Main;

public class PacketHandler {
    public static void register() {
	int disc = 0;

	Main.Network.registerMessage(disc++, DmgNumPacket.class, DmgNumPacket::encode,
		DmgNumPacket::decode, DmgNumPacket::handle);

    }
}
