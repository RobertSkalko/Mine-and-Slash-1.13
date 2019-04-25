package com.robertx22.mmorpg.proxy;

import java.util.function.Supplier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ServerProxy implements IProxy {

    @Override
    public String translate(String str) {
	return "TRANSLATION PROXY ERROR";
    }

    @Override
    public void preInit(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

    @Override
    public void postInit(InterModProcessEvent event) {
	// TODO Auto-generated method stub

    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {
	// TODO Auto-generated method stub

    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(Supplier<Context> ctx) {
	return ctx.get().getSender();
    }

}