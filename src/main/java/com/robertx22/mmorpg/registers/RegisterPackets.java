package com.robertx22.mmorpg.registers;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.NoEnergyPacket;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;

public class RegisterPackets {
    public static void register() {
	int index = 0;

	MMORPG.Network.registerMessage(index++, DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode,
		DmgNumPacket::handle);

	MMORPG.Network.registerMessage(index++, EntityUnitPackage.class, EntityUnitPackage::encode,
		EntityUnitPackage::decode, EntityUnitPackage::handle);

	MMORPG.Network.registerMessage(index++, NoEnergyPacket.class, NoEnergyPacket::encode, NoEnergyPacket::decode,
		NoEnergyPacket::handle);

	MMORPG.Network.registerMessage(index++, ParticlePackage.class, ParticlePackage::encode, ParticlePackage::decode,
		ParticlePackage::handle);

	MMORPG.Network.registerMessage(index++, WorldPackage.class, WorldPackage::encode, WorldPackage::decode,
		WorldPackage::handle);

	MMORPG.Network.registerMessage(index++, PlayerUnitPackage.class, PlayerUnitPackage::encode,
		PlayerUnitPackage::decode, PlayerUnitPackage::handle);

    }
}
