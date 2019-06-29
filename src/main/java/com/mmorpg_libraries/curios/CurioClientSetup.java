package com.mmorpg_libraries.curios;

import com.robertx22.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.CuriosAPI;

public class CurioClientSetup {
    public static final String CURIOS = "curios";

    public static void setup(final FMLClientSetupEvent event) {

        send(CuriosAPI.IMC.REGISTER_ICON, new Tuple<>(RefCurio.BRACELET, new ResourceLocation(Ref.MODID, "textures/items/slots/bracelet")));
        send(CuriosAPI.IMC.REGISTER_ICON, new Tuple<>(RefCurio.SALVAGE_BAG, new ResourceLocation(Ref.MODID, "textures/items/slots/salvage_bag")));

    }

    private static void send(String id, Object msg) {
        InterModComms.sendTo(CURIOS, id, () -> msg);
    }

}
