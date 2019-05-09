package com.mmorpg_libraries.curios;

import com.robertx22.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.CuriosAPI;

public class CurioClientSetup {

    public static void setup(final FMLClientSetupEvent event) {

        CuriosAPI.registerIcon(RefCurio.BRACELET, new ResourceLocation(Ref.MODID, "items/slots/bracelet"));
        CuriosAPI.registerIcon(RefCurio.SALVAGE_BAG, new ResourceLocation(Ref.MODID, "items/slots/salvage_bag"));

    }

}
