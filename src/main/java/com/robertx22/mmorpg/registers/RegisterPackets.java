package com.robertx22.mmorpg.registers;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.NoEnergyPacket;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;

public class RegisterPackets {
    public static void register() {
	int index = 0;

	Main.Network.registerMessage(index++, DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode,
		DmgNumPacket::handle);

	Main.Network.registerMessage(index++, EntityUnitPackage.class, EntityUnitPackage::encode,
		EntityUnitPackage::decode, EntityUnitPackage::handle);

	Main.Network.registerMessage(index++, NoEnergyPacket.class, NoEnergyPacket::encode, NoEnergyPacket::decode,
		NoEnergyPacket::handle);

	Main.Network.registerMessage(index++, ParticlePackage.class, ParticlePackage::encode, ParticlePackage::decode,
		ParticlePackage::handle);

	Main.Network.registerMessage(index++, WorldPackage.class, WorldPackage::encode, WorldPackage::decode,
		WorldPackage::handle);

	Main.Network.registerMessage(index++, PlayerUnitPackage.class, PlayerUnitPackage::encode,
		PlayerUnitPackage::decode, PlayerUnitPackage::handle);

    }
}
