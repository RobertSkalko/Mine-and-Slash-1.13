package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.MapData;
import com.robertx22.uncommon.capability.PlayerCapBackupData;
import com.robertx22.uncommon.capability.PlayerMapData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Load {

    public static boolean hasUnit(
            ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            return provider.getCapability(EntityData.Data).isPresent();
        }
        return false;
    }

    public static MapData.IMapData mapData(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(MapData.Data).orElse(new MapData.DefaultImpl());

        }
        return null;
    }

    public static PlayerCapBackupData.IPlayerCapBackupData playersCapBackup(World world) {

        if (world != null) {

            return world.getCapability(PlayerCapBackupData.Data)
                    .orElse(new PlayerCapBackupData.DefaultImpl());

        }
        return null;
    }

    public static UnitData Unit(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(EntityData.Data)
                    .orElse(new EntityData.DefaultImpl());

        }
        return null;
    }

    public static PlayerMapData.IPlayerMapData playerMapData(PlayerEntity provider) {

        if (provider != null) {
            return provider.getCapability(PlayerMapData.Data)
                    .orElse(new PlayerMapData.DefaultImpl());
        }
        return null;
    }

}
