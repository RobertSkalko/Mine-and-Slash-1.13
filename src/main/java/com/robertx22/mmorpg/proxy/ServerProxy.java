package com.robertx22.mmorpg.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ServerProxy implements IProxy {

    @Override
    public EntityPlayer getPlayerEntityFromContext(Supplier<Context> ctx) {
        return ctx.get().getSender();
    }

    @Override
    public String translate(ITextComponent comp) {
        return "error";
    }

}