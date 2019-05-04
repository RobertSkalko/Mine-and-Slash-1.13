package com.robertx22.mmorpg.proxy;

import com.mmorpg_libraries.gui.neat_mob_overlay.HealthBarRenderer;
import com.robertx22.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLCommonSetupEvent event) {

        MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
        KeybindsRegister.register();

    }

    @Override
    public void postInit(InterModProcessEvent event) {
        // DEBUG
        System.out.println("on Client side");

        MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));

    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(Supplier<Context> ctx) {
        return Minecraft.getInstance().player;
    }

    @Override
    public String translate(ITextComponent comp) {
        return I18n.format(comp.getUnformattedComponentText());
    }

}
