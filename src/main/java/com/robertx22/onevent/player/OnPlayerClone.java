package com.robertx22.onevent.player;

import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnPlayerClone {

    public static final String PERS_DATA_LOC = "mmorpg:pers_entity_data";

    // TODO THIS NEEDS A FORGE FIX. EITHER THAT OR I BASICALLY MAKE MY OWN SAVE SYSTEM (OR USE ENTITYDATA.PERSISTENT TAG
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

        if (!event.getEntityPlayer().world.isRemote) {

            EntityPlayer player = event.getEntityPlayer();
            EntityPlayer original = event.getOriginal();

            Load.Unit(player).setNBT(Load.Unit(event.getOriginal()).getNBT());

        }

    }

}
