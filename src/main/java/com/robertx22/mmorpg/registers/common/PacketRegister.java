package com.robertx22.mmorpg.registers.common;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.*;

public class PacketRegister {
    public static void register() {
        int index = 0;

        MMORPG.Network.registerMessage(index++, DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode, DmgNumPacket::handle);

        MMORPG.Network.registerMessage(index++, EntityUnitPacket.class, EntityUnitPacket::encode, EntityUnitPacket::decode, EntityUnitPacket::handle);

        MMORPG.Network.registerMessage(index++, NoEnergyPacket.class, NoEnergyPacket::encode, NoEnergyPacket::decode, NoEnergyPacket::handle);

        MMORPG.Network.registerMessage(index++, ParticleGenPacket.class, ParticleGenPacket::encode, ParticleGenPacket::decode, ParticleGenPacket::handle);

        MMORPG.Network.registerMessage(index++, IWorldPacket.class, IWorldPacket::encode, IWorldPacket::decode, IWorldPacket::handle);

        MMORPG.Network.registerMessage(index++, PlayerUnitPacket.class, PlayerUnitPacket::encode, PlayerUnitPacket::decode, PlayerUnitPacket::handle);

    }
}
