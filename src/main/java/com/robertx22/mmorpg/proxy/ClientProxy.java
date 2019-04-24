package com.robertx22.mmorpg.proxy;

import com.robertx22.customitems.gearitems.MyEntityArrow;
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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy {
    // functionality

    public static void preInit(FMLCommonSetupEvent event) {
	// DEBUG
	System.out.println("on Client side");

	MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());

	MinecraftForge.EVENT_BUS.register(new PlayerUnitPackage.Handler());
	MinecraftForge.EVENT_BUS.register(new EntityUnitPackage.Handler());
	MinecraftForge.EVENT_BUS.register(new DmgNumPacket());
	MinecraftForge.EVENT_BUS.register(new ParticlePackage.Handler());
	MinecraftForge.EVENT_BUS.register(new WorldPackage.Handler());
	MinecraftForge.EVENT_BUS.register(new MessagePackage.Handler());

    }

    public static void regRenders(ModelRegistryEvent evt) {

	RegisterModEntity(Items.SNOWBALL, SpellFrostBolt.EntityFrostBolt.class, i++);
	RegisterModEntity(Items.MAGMA_CREAM, SpellFireBolt.EntityFireBolt.class, i++);
	RegisterModEntity(Items.SLIME_BALL, SpellAcidBolt.EntityAcidBolt.class, i++);
	RegisterModEntity(Items.GLOWSTONE_DUST, SpellThunderBolt.EntityThunderBolt.class, i++);

	RegisterModEntity(Items.SNOWBALL, SpellFrostExplosion.EntityFrostExplosion.class, i++);
	RegisterModEntity(Items.MAGMA_CREAM, SpellFlameExplosion.EntityFlameExplosion.class, i++);
	RegisterModEntity(Items.SLIME_BALL, SpellAcidExplosion.EntityAcidExplosion.class, i++);
	RegisterModEntity(Items.GLOWSTONE_DUST, SpellLightningExplosion.EntityLightningExplosion.class, i++);

	RegisterModEntity(Items.ENDER_PEARL, EntityStaffProjectile.class, i++);

	regArrow(Items.ARROW, MyEntityArrow.class, i++);
	//

	RegisterModEntity(Items.SLIME_BALL, SpellAcidBomb.EntityAcidBomb.class, i++);
	RegisterModEntity(Items.GLOWSTONE_DUST, SpellThunderBomb.EntityThunderBomb.class, i++);
	RegisterModEntity(Items.SNOWBALL, SpellIceBomb.EntityIceBomb.class, i++);

	ClientRegistry.bindTileEntitySpecialRenderer(TileMapPortal.class, new RenderTileMapPortal());

	RenderingRegistry.registerEntityRenderingHandler(SpellFireBomb.EntityFireBomb.class,
		createRenderFactory(Items.MAGMA_CREAM));

    }

    public static void init() {
	// DEBUG
	System.out.println("on Client side");

	Keybinds.register();

	TileEntityItemStackRenderer.instance = new ShieldRenderer(TileEntityItemStackRenderer.instance);

    }

    public static void postInit() {
	// DEBUG
	System.out.println("on Client side");

	MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));

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

    public static <T extends Entity> IRenderFactory<T> createRenderFactory(final Item itemToRender) {
	return manager -> new RenderSprite<>(manager, itemToRender, Minecraft.getInstance().getItemRenderer());
    }

    @Override
    public String translate(String str) {
	return I18n.format(str);
    }

}
