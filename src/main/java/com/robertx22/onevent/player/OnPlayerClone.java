package com.robertx22.onevent.player;

import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.PlayerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnPlayerClone {

    public static final String ENTITY_DATA_BACKUP = "mmorpg:entity_data_backup";
    public static final String PLAYER_MAP_DATA_BACKUP = "mmorpg:player_map_data_backup";

    @SubscribeEvent
    public static void onSave(PlayerEvent.SaveToFile event) {
        PlayerEntity player = event.getEntityPlayer();

        CompoundNBT unitdatanbt = Load.Unit(player).getNBT();
        CompoundNBT playermapdatanbt = Load.playerMapData(player).getNBT();

        CompoundNBT pesrsistentNBT = PlayerUtils.getPersistentNBT(player);

        pesrsistentNBT.put(ENTITY_DATA_BACKUP, unitdatanbt);
        pesrsistentNBT.put(PLAYER_MAP_DATA_BACKUP, playermapdatanbt);
        PlayerUtils.setPestistentNBT(player, pesrsistentNBT);

    }

    // TODO THIS NEEDS A FORGE FIX. EITHER THAT OR I BASICALLY MAKE MY OWN SAVE SYSTEM (OR USE ENTITYDATA.PERSISTENT TAG
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {

        PlayerEntity original = event.getOriginal();

        CompoundNBT backupNBT = PlayerUtils.getPersistentNBT(original);

        CompoundNBT unitdataNBT = backupNBT.getCompound(ENTITY_DATA_BACKUP);
        CompoundNBT playermapdataNBT = backupNBT.getCompound(PLAYER_MAP_DATA_BACKUP);

        Load.Unit(event.getEntityPlayer()).setNBT(unitdataNBT);
        Load.playerMapData(event.getEntityPlayer()).setNBT(playermapdataNBT);

    }

}
