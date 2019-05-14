package com.robertx22.onevent.player;

import com.robertx22.uncommon.datasaving.Load;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnPlayerClone {

    public static final String PERS_DATA_LOC = "mmorpg:pers_entity_data";

    // TODO THIS NEEDS A FORGE FIX. EITHER THAT OR I BASICALLY MAKE MY OWN SAVE SYSTEM (OR USE ENTITYDATA.PERSISTENT TAG
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {

        Load.Unit(event.getEntityPlayer())
                .HandleCloneEvent(Load.Unit(event.getOriginal()));

    }
    
}
