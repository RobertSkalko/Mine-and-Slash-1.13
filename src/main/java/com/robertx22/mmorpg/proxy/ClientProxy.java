package com.robertx22.mmorpg.proxy;

import java.util.function.Supplier;

import com.robertx22.customitems.gearitems.MyEntityArrow;
import com.robertx22.customitems.gearitems.RenderMyArrow;
import com.robertx22.customitems.gearitems.offhands.ShieldRenderer;
import com.robertx22.dimensions.blocks.RenderTileMapPortal;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.Keybinds;
import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.spells.bases.projectile.EntityStaffProjectile;
import com.robertx22.spells.projectile.SpellAcidBolt;
import com.robertx22.spells.projectile.SpellFireBolt;
import com.robertx22.spells.projectile.SpellFrostBolt;
import com.robertx22.spells.projectile.SpellThunderBolt;
import com.robertx22.uncommon.gui.mobs.HealthBarRenderer;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class ClientProxy implements IProxy {

    public void preInit(FMLCommonSetupEvent event) {

	MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());

    }

    public static void regRenders(ModelRegistryEvent evt) {

	ClientRegistry.bindTileEntitySpecialRenderer(TileMapPortal.class, new RenderTileMapPortal());

	RenderingRegistry.registerEntityRenderingHandler(MyEntityArrow.class, RenderMyArrow::new);

	RenderingRegistry.registerEntityRenderingHandler(SpellFireBolt.EntityFireBolt.class,
		newRenFac(Items.MAGMA_CREAM));
	RenderingRegistry.registerEntityRenderingHandler(SpellFireBomb.EntityFireBomb.class,
		newRenFac(Items.MAGMA_CREAM));
	RenderingRegistry.registerEntityRenderingHandler(SpellFlameExplosion.EntityFlameExplosion.class,
		newRenFac(Items.MAGMA_CREAM));

	RenderingRegistry.registerEntityRenderingHandler(SpellFrostBolt.EntityFrostBolt.class,
		newRenFac(Items.SNOWBALL));
	RenderingRegistry.registerEntityRenderingHandler(SpellIceBomb.EntityIceBomb.class, newRenFac(Items.SNOWBALL));
	RenderingRegistry.registerEntityRenderingHandler(SpellFrostExplosion.EntityFrostExplosion.class,
		newRenFac(Items.SNOWBALL));

	RenderingRegistry.registerEntityRenderingHandler(SpellThunderBolt.EntityThunderBolt.class,
		newRenFac(Items.GLOWSTONE_DUST));
	RenderingRegistry.registerEntityRenderingHandler(SpellLightningExplosion.EntityLightningExplosion.class,
		newRenFac(Items.GLOWSTONE_DUST));
	RenderingRegistry.registerEntityRenderingHandler(SpellThunderBomb.EntityThunderBomb.class,
		newRenFac(Items.GLOWSTONE_DUST));

	RenderingRegistry.registerEntityRenderingHandler(SpellAcidBolt.EntityAcidBolt.class,
		newRenFac(Items.SLIME_BALL));
	RenderingRegistry.registerEntityRenderingHandler(SpellAcidExplosion.EntityAcidExplosion.class,
		newRenFac(Items.SLIME_BALL));
	RenderingRegistry.registerEntityRenderingHandler(SpellAcidBomb.EntityAcidBomb.class,
		newRenFac(Items.SLIME_BALL));

	RenderingRegistry.registerEntityRenderingHandler(EntityStaffProjectile.class, newRenFac(Items.ENDER_PEARL));

    }

    public void init() {
	// DEBUG
	System.out.println("on Client side");

	Keybinds.register();

	TileEntityItemStackRenderer.instance = new ShieldRenderer(TileEntityItemStackRenderer.instance);

    }

    public void postInit() {
	// DEBUG
	System.out.println("on Client side");

	MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));

    }

    public static <T extends Entity> IRenderFactory<T> newRenFac(final Item itemToRender) {
	return manager -> new RenderSprite<>(manager, itemToRender, Minecraft.getInstance().getItemRenderer());
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
