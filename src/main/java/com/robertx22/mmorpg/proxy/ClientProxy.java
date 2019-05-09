package com.robertx22.mmorpg.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ClientProxy implements IProxy {

    @Override
    public EntityPlayer getPlayerEntityFromContext(Supplier<Context> ctx) {
        return Minecraft.getInstance().player;
    }

    @Override
    public String translate(ITextComponent comp) {
        return I18n.format(comp.getUnformattedComponentText());
    }

}
