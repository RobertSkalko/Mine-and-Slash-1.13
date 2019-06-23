package com.robertx22.mmorpg.registers.client;

import com.robertx22.mmorpg.Ref;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderRegister {

    //@SubscribeEvent
    public static void regRenders(/*ModelRegistryEvent evt*/) {

        RenderingRegistry.registerEntityRenderingHandler(SpellFireBolt.EntityFireBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellFireBomb.EntityFireBomb.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellFlameExplosion.EntityFlameExplosion.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(SpellFrostBolt.EntityFrostBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellIceBomb.EntityIceBomb.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellFrostExplosion.EntityFrostExplosion.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(SpellThunderBolt.EntityThunderBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellLightningExplosion.EntityLightningExplosion.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellThunderBomb.EntityThunderBomb.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(SpellAcidBolt.EntityAcidBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellAcidExplosion.EntityAcidExplosion.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(SpellAcidBomb.EntityAcidBomb.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(EntityStaffProjectile.class, newRenFac());

    }

    private static <T extends Entity & IRendersAsItem> IRenderFactory<T> newRenFac() {
        return manager -> new SpriteRenderer<>(manager, Minecraft.getInstance()
                .getItemRenderer());
    }
}
