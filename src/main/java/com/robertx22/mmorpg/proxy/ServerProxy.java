package com.robertx22.mmorpg.proxy;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public class ServerProxy implements IProxy {

    public void regArrow(Item item, Class<? extends Entity> theclass, int id) {
	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
		Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);
    }

    @Override
    public String translate(String str) {
	return "TRANSLATION PROXY ERROR";
    }

    @Override
    public void RegisterModEntity(Item item, Class<? extends Entity> theclass, int id) {

	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
		Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);

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
    public void regRenders(ModelRegistryEvent evt) {
	// TODO Auto-generated method stub

    }
}