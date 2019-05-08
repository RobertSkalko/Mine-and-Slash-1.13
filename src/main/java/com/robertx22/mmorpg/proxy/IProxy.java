package com.robertx22.mmorpg.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IProxy {

    void preInit(FMLCommonSetupEvent event);

    void postInit(InterModProcessEvent event);

    void loadComplete(FMLLoadCompleteEvent event);

    EntityPlayer getPlayerEntityFromContext(Supplier<NetworkEvent.Context> ctx);

    // void regRenders(ModelRegistryEvent evt);

    String translate(ITextComponent comp);

    // java.util.function.Supplier<java.util.concurrent.Callable<net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer>> setTEISR();
}
