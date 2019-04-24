package com.robertx22.mmorpg.proxy;

import javax.xml.ws.handler.MessageContext;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ServerProxy implements IProxy {
    @Override
    public void preInit() {

	// DEBUG
	System.out.println("on Server side");

    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void serverStarting() {

    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
	return ctx.getServerHandler().player;
    }

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
}