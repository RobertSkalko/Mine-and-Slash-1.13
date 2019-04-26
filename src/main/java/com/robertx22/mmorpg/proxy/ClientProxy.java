package com.robertx22.mmorpg.proxy;

import java.util.function.Supplier;

import com.robertx22.items.gearitems.offhands.ShieldRenderer;
import com.robertx22.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.uncommon.gui.mobs.HealthBarRenderer;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ClientProxy implements IProxy {

    public void preInit(FMLCommonSetupEvent event) {

	MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
	KeybindsRegister.register();
	TileEntityItemStackRenderer.instance = new ShieldRenderer(TileEntityItemStackRenderer.instance);

    }

    public void postInit() {
	// DEBUG
	System.out.println("on Client side");

	MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));

    }

    @Override
    public String translate(String str) {
	return I18n.format(str);
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
	return Minecraft.getInstance().player;
    }

}
