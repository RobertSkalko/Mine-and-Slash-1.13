package com.robertx22.onevent.ontick;

import com.robertx22.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.database.stats.stat_types.resources.HealthRegen;
import com.robertx22.database.stats.stat_types.resources.ManaRegen;
import com.robertx22.items.misc.ItemMapBackPortal;
import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.IWorldPacket;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import java.util.HashMap;
import java.util.UUID;

@EventBusSubscriber
public class OnTickLogic {

    static final int TicksToUpdatePlayer = 18;
    static final int TicksToRegen = 100;
    static final int TicksToGiveMapPortal = 400;
    static final int TicksToUpdateWorld = 300;

    public static HashMap<UUID, PlayerTickData> PlayerTickDatas = new HashMap<UUID, PlayerTickData>();

    @SubscribeEvent
    public static void onTickLogicVoid(TickEvent.PlayerTickEvent event) {

        if (event.phase == Phase.END && event.side.equals(LogicalSide.SERVER)) {

            try {

                EntityPlayerMP player = (EntityPlayerMP) event.player;

                PlayerTickData data = PlayerTickDatas.get(player.getUniqueID());

                if (data == null) {
                    data = new PlayerTickData();
                }

                data.increment();

                if (data.regenTicks > TicksToRegen) {
                    data.regenTicks = 0;
                    if (player.isAlive()) {

                        IWorldData mapdata = Load.World(player.world);
                        UnitData unit_capa = Load.Unit(player);
                        unit_capa.forceRecalculateStats(player, mapdata); // has to do this cus curios doesnt call equipsChanged event
                        Unit unit = unit_capa.getUnit();

                        int manarestored = (int) unit.MyStats.get(new ManaRegen().GUID()).Value;
                        unit_capa.restoreMana(manarestored);

                        int energyrestored = (int) unit.MyStats.get(new EnergyRegen().GUID()).Value;
                        unit_capa.restoreEnergy(energyrestored);

                        int healthrestored = (int) unit.MyStats.get(new HealthRegen().GUID()).Value;
                        unit_capa.heal(player, healthrestored);

                    }
                }

                if (data.worldUpdateTicks > TicksToUpdateWorld) {
                    data.worldUpdateTicks = 0;
                    IWorldData mapdata = Load.World(player.world);
                    if (mapdata.isMapWorld()) {
                        MMORPG.sendToClient(new IWorldPacket(mapdata), player);
                    }

                }

                if (data.mapPortalTicks > TicksToGiveMapPortal) {
                    data.mapPortalTicks = 0;

                    IWorldData mapdata = Load.World(player.world);

                    if (mapdata.isMapWorld()) {
                        ItemStack portalitem = new ItemStack(ItemMapBackPortal.ITEM);
                        if (!player.inventory.hasItemStack(portalitem)) {
                            player.inventory.addItemStackToInventory(portalitem);

                        }
                    }

                }

                if (data.playerSyncTick > TicksToUpdatePlayer) {
                    data.playerSyncTick = 0;
                    Load.Unit(player).syncToClient(player);
                }

                PlayerTickDatas.put(player.getUniqueID(), data);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    static class PlayerTickData {
        public int regenTicks = 0;
        public int playerSyncTick = 0;
        public int mapPortalTicks = 0;
        public int worldUpdateTicks = 0;

        public void increment() {
            regenTicks++;
            playerSyncTick++;
            mapPortalTicks++;
            worldUpdateTicks++;
        }

    }

}
