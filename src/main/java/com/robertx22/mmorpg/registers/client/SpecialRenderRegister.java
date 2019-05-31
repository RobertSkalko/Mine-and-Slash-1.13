package com.robertx22.mmorpg.registers.client;

import com.robertx22.dimensions.blocks.RenderTileMapPortal;
import com.robertx22.dimensions.blocks.TileMapPortal;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class SpecialRenderRegister {

    public static void register(FMLClientSetupEvent event) {

        ClientRegistry.bindTileEntitySpecialRenderer(TileMapPortal.class, new RenderTileMapPortal());

    }

}
