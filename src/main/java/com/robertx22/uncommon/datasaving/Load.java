package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.PlayerMapData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Load {

    public static boolean hasUnit(
            ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            return provider.getCapability(EntityData.Data).isPresent();
        }
        return false;
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
            return provider.getCapability(PlayerMapData.Data).orElse(null);
        }
        return null;
    }

}
