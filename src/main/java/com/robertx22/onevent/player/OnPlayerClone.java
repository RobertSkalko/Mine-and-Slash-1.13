package com.robertx22.onevent.player;

import com.robertx22.uncommon.capability.MasterLootBagCap;
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
    public static final String MASTER_LOOT_BAG_BACKUP = "mmorpg:master_loot_bag_backup";

    @SubscribeEvent
    public static void onSave(PlayerEvent.SaveToFile event) {
        PlayerEntity player = event.getEntityPlayer();

        CompoundNBT unitdatanbt = null;
        try {
            unitdatanbt = Load.Unit(player).getNBT();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompoundNBT playermapdatanbt = null;
        try {
            playermapdatanbt = Load.playerMapData(player).getNBT();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CompoundNBT masterbagnbt = null;
        try {
            masterbagnbt = (player).getCapability(MasterLootBagCap.Data)
                    .orElse(new MasterLootBagCap.DefaultImpl())
                    .getNBT();

        } catch (Exception e) {
            e.printStackTrace();
        }

        CompoundNBT pesrsistentNBT = null;
        try {
            pesrsistentNBT = PlayerUtils.getPersistentNBT(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pesrsistentNBT != null) {

            if (unitdatanbt != null) {
                pesrsistentNBT.put(ENTITY_DATA_BACKUP, unitdatanbt);
            }
            if (playermapdatanbt != null) {
                pesrsistentNBT.put(PLAYER_MAP_DATA_BACKUP, playermapdatanbt);
            }
            if (masterbagnbt != null) {
                pesrsistentNBT.put(MASTER_LOOT_BAG_BACKUP, masterbagnbt);
            }

            PlayerUtils.setPestistentNBT(player, pesrsistentNBT);

        }

    }

    // TODO THIS NEEDS A FORGE FIX. EITHER THAT OR I BASICALLY MAKE MY OWN SAVE SYSTEM (OR USE ENTITYDATA.PERSISTENT TAG
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {

        PlayerEntity original = event.getOriginal();

        CompoundNBT backupNBT = PlayerUtils.getPersistentNBT(original);

        try {
            CompoundNBT nbt = backupNBT.getCompound(ENTITY_DATA_BACKUP);
            Load.Unit(event.getEntityPlayer()).setNBT(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            CompoundNBT nbt = backupNBT.getCompound(PLAYER_MAP_DATA_BACKUP);
            Load.playerMapData(event.getEntityPlayer()).setNBT(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            CompoundNBT nbt = backupNBT.getCompound(MASTER_LOOT_BAG_BACKUP);
            event.getEntityPlayer()
                    .getCapability(MasterLootBagCap.Data)
                    .orElse(new MasterLootBagCap.DefaultImpl())
                    .setNBT(nbt);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
