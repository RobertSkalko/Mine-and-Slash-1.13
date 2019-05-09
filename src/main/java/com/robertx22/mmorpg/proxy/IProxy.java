package com.robertx22.mmorpg.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IProxy {

    EntityPlayer getPlayerEntityFromContext(Supplier<NetworkEvent.Context> ctx);

    String translate(ITextComponent comp);

}