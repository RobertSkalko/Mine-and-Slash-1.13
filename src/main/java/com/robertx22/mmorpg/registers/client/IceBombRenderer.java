package com.robertx22.mmorpg.registers.client;

import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;

public class IceBombRenderer extends SpriteRenderer<SpellIceBomb.EntityIceBomb> {

    public IceBombRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }

}