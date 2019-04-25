package com.robertx22.mmorpg.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public interface IProxy {

    void preInit(FMLCommonSetupEvent event);

    void postInit(InterModProcessEvent event);

    void loadComplete(FMLLoadCompleteEvent event);

    String translate(String str);

    // void regRenders(ModelRegistryEvent evt);
}
