package com.robertx22.mmorpg.registers.client;

import com.robertx22.dimensions.blocks.RenderTileMapPortal;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.items.gearitems.MyEntityArrow;
import com.robertx22.items.gearitems.RenderMyArrow;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class RenderRegister {

    private static final Item THUNDER = Items.GLOWSTONE_DUST;
    private static final Item FIRE = Items.MAGMA_CREAM;
    private static final Item WATER = Items.SNOWBALL;
    private static final Item NATURE = Items.SLIME_BALL;

    @SubscribeEvent
    public static void regRenders(ModelRegistryEvent evt) {

        ClientRegistry.bindTileEntitySpecialRenderer(TileMapPortal.class, new RenderTileMapPortal());

        RenderingRegistry.registerEntityRenderingHandler(MyEntityArrow.class, RenderMyArrow::new);

        RenderingRegistry.registerEntityRenderingHandler(SpellFireBolt.EntityFireBolt.class, newRenFac(FIRE));
        RenderingRegistry.registerEntityRenderingHandler(SpellFireBomb.EntityFireBomb.class, newRenFac(FIRE));
        RenderingRegistry.registerEntityRenderingHandler(SpellFlameExplosion.EntityFlameExplosion.class, newRenFac(FIRE));

        RenderingRegistry.registerEntityRenderingHandler(SpellFrostBolt.EntityFrostBolt.class, newRenFac(WATER));
        RenderingRegistry.registerEntityRenderingHandler(SpellIceBomb.EntityIceBomb.class, newRenFac(WATER));
        RenderingRegistry.registerEntityRenderingHandler(SpellFrostExplosion.EntityFrostExplosion.class, newRenFac(WATER));

        RenderingRegistry.registerEntityRenderingHandler(SpellThunderBolt.EntityThunderBolt.class, newRenFac(THUNDER));
        RenderingRegistry.registerEntityRenderingHandler(SpellLightningExplosion.EntityLightningExplosion.class, newRenFac(THUNDER));
        RenderingRegistry.registerEntityRenderingHandler(SpellThunderBomb.EntityThunderBomb.class, newRenFac(THUNDER));

        RenderingRegistry.registerEntityRenderingHandler(SpellAcidBolt.EntityAcidBolt.class, newRenFac(NATURE));
        RenderingRegistry.registerEntityRenderingHandler(SpellAcidExplosion.EntityAcidExplosion.class, newRenFac(NATURE));
        RenderingRegistry.registerEntityRenderingHandler(SpellAcidBomb.EntityAcidBomb.class, newRenFac(NATURE));

        RenderingRegistry.registerEntityRenderingHandler(EntityStaffProjectile.class, newRenFac(Items.ENDER_PEARL));

    }

    public static <T extends Entity> IRenderFactory<T> newRenFac(
            final Item itemToRender) {
        return manager -> new RenderSprite<>(manager, itemToRender, Minecraft.getInstance()
                .getItemRenderer());
    }
}
