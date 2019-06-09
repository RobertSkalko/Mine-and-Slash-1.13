package com.robertx22.mmorpg.registers.client;

import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.spells.entities.bases.EntityStaffProjectile;
import com.robertx22.spells.projectile.SpellAcidBolt;
import com.robertx22.spells.projectile.SpellFireBolt;
import com.robertx22.spells.projectile.SpellFrostBolt;
import com.robertx22.spells.projectile.SpellThunderBolt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    // TODO IDK IF THIS WORKS
    public static <T extends Entity & IRendersAsItem> IRenderFactory<T> newRenFac(
            final Item itemToRender) {
        return manager -> new SpriteRenderer<>(manager, Minecraft.getInstance()
                .getItemRenderer());
    }
}
