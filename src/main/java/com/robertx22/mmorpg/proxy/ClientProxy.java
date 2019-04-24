package com.robertx22.mmorpg.proxy;

import javax.xml.ws.handler.MessageContext;

import com.robertx22.customitems.gearitems.RenderMyArrow;
import com.robertx22.customitems.gearitems.offhands.ShieldRenderer;
import com.robertx22.dimensions.blocks.RenderTileMapPortal;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.Keybinds;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.MessagePackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.gui.mobs.HealthBarRenderer;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {
    // functionality

    @Override
    public void preInit() {
	// DEBUG
	System.out.println("on Client side");

	MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());

	MinecraftForge.EVENT_BUS.register(new PlayerUnitPackage.Handler());
	MinecraftForge.EVENT_BUS.register(new EntityUnitPackage.Handler());
	MinecraftForge.EVENT_BUS.register(new DmgNumPacket());
	MinecraftForge.EVENT_BUS.register(new ParticlePackage.Handler());
	MinecraftForge.EVENT_BUS.register(new WorldPackage.Handler());
	MinecraftForge.EVENT_BUS.register(new MessagePackage.Handler());

	ClientRegistry.bindTileEntitySpecialRenderer(TileMapPortal.class, new RenderTileMapPortal());

    }

    @Override
    public void init() {
	// DEBUG
	System.out.println("on Client side");

	Keybinds.register();

	TileEntityItemStackRenderer.instance = new ShieldRenderer(TileEntityItemStackRenderer.instance);

    }

    @Override
    public void postInit() {
	// DEBUG
	System.out.println("on Client side");

	MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));

    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
	return (ctx.side.isClient() ? Minecraft.getInstance().player : Main.proxy.getPlayerEntityFromContext(ctx));
    }

    @Override
    public void serverStarting() {
	// This will never get called on client side

    }

    public void regArrow(Item item, Class<? extends Entity> theclass, int id) {

	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
		Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);

	RenderingRegistry.registerEntityRenderingHandler(theclass, new IRenderFactory() {
	    @Override
	    public Render createRenderFor(RenderManager manager) {
		return new RenderMyArrow(manager);
	    }
	});

    }

    private static <T extends Entity> IRenderFactory<T> RegisterModEntity(final Item itemToRender) {
	return manager -> new RenderSprite<>(manager, itemToRender, Minecraft.getInstance().getItemRenderer());
    }

    @Override
    public String translate(String str) {
	return I18n.format(str);
    }

}
