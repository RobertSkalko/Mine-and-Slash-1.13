package com.robertx22.uncommon.capability;

import com.robertx22.dimensions.MapManager;
import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.bases.BaseProvider;
import com.robertx22.uncommon.capability.bases.BaseStorage;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerMapData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "playermapdata");

    @CapabilityInject(IPlayerMapData.class)
    public static final Capability<IPlayerMapData> Data = null;

    static final String POS_OBJ = "POS_OBJ";
    static final String ORIGINAL_DIM = "original_dimension";
    static final String MAP_GUID = "MAP_GUID";

    public interface IPlayerMapData extends ICommonCapability {

        float getBonusLootAmount(EntityPlayer player);

        int getBonusLootAmountInPercent(EntityPlayer player);

        String getLastMapGUID();

        boolean hasTimeForMap();

        int getLevel();

        int getTier();

        MapItemData getMap();

        BlockPos getMapDevicePos();

        DimensionType getOriginalDimension();

        void teleportPlayerBack(EntityPlayer player);

        void onPlayerDeath(EntityPlayer player);

        boolean isPermaDeath();

        void onMinute(EntityPlayer player);

        void init(BlockPos pos, MapItemData map, DimensionType type, EntityPlayer player);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof EntityPlayer) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IPlayerMapData> {

        @Override
        public IPlayerMapData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerMapData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerMapData {

        private NBTTagCompound nbt = new NBTTagCompound();

        long mapDevicePos;
        MapItemData mapdata = new MapItemData();
        DimensionType originalDimension = null;
        int minutesPassed = 0;
        String mapGUID = ""; // used to check if same map for chests

        @Override
        public NBTTagCompound getNBT() {

            if (mapdata != null) {
                Map.Save(nbt, mapdata);
            }

            nbt.putString(MAP_GUID, mapGUID);
            nbt.putLong(POS_OBJ, mapDevicePos);

            if (this.originalDimension != null) {
                nbt.putString(ORIGINAL_DIM, MapManager.getResourceLocation(originalDimension)
                        .toString());
            }

            return nbt;

        }

        @Override
        public void setNBT(NBTTagCompound value) {
            this.nbt = value;

            mapdata = Map.Load(nbt);
            this.mapDevicePos = nbt.getLong(POS_OBJ);
            this.mapGUID = nbt.getString(MAP_GUID);
            if (nbt.contains(ORIGINAL_DIM)) {
                this.originalDimension = DimensionType.byName(new ResourceLocation(nbt.getString(ORIGINAL_DIM)));
            }

        }

        @Override
        public void onPlayerDeath(EntityPlayer player) {

            if (this.isPermaDeath()) {

                this.minutesPassed += 555555;

                player.sendMessage(SLOC.chat("player_died_mapworld")
                        .appendText(" " + player.getDisplayName()
                                .getFormattedText() + " ")
                        .appendText("World closing due to permadeath"));

            } else {
                int punishment = 5;

                player.sendMessage(SLOC.chat("player_died_mapworld")
                        .appendText(" " + player.getDisplayName()
                                .getFormattedText() + " ")
                        .appendSibling(SLOC.chat("activating_mapworld_time_penalty")));

                this.minutesPassed += punishment;

                announceTimeLeft(player);
            }

            player.setHealth(player.getMaxHealth() / 4); // needs to have more hp to actually teleport lol and not die

            teleportPlayerBack(player);

        }

        @Override
        public boolean isPermaDeath() {
            return mapdata.isPermaDeath;
        }

        @Override
        public void onMinute(EntityPlayer player) {
            this.minutesPassed++;

            if (this.getMinutesLeft() < 1) {

                this.announceEnd(player);
                this.teleportPlayerBack(player);

            } else {
                onMinutePassAnnounce(player);

            }

        }

        @Override
        public void init(BlockPos pos, MapItemData map, DimensionType type,
                         EntityPlayer player) {

            this.minutesPassed = 0;
            this.mapDevicePos = pos.toLong();
            this.originalDimension = player.world.getDimension().getType();
            this.mapdata = map;
            this.mapGUID = UUID.randomUUID().toString();

        }

        private void onMinutePassAnnounce(EntityPlayer player) {
            int minutesLeft = getMinutesLeft();

            if (minutesLeft > 0) {
                if (minutesLeft < 5 || minutesLeft % 5 == 0) {
                    announceTimeLeft(player);
                }
            }
        }

        @Override
        public float getBonusLootAmount(EntityPlayer player) {
            if (WorldUtils.isMapWorld(player.world)) {
                return this.mapdata.getBonusLootAmount();
            } else {
                return 0;
            }
        }

        @Override
        public int getBonusLootAmountInPercent(EntityPlayer player) {
            return (int) (this.getBonusLootAmount(player) * 100);
        }

        @Override
        public String getLastMapGUID() {
            return this.mapGUID;
        }

        @Override
        public boolean hasTimeForMap() {
            return this.getMinutesLeft() > 0;
        }

        @Override
        public int getLevel() {
            return this.mapdata.level;
        }

        @Override
        public int getTier() {
            return this.mapdata.tier;
        }

        @Override
        public MapItemData getMap() {
            return this.mapdata;
        }

        @Override
        public BlockPos getMapDevicePos() {
            return BlockPos.fromLong(mapDevicePos).south(3);
        }

        @Override
        public DimensionType getOriginalDimension() {
            return originalDimension;
        }

        @Override
        public void teleportPlayerBack(EntityPlayer player) {

            if (WorldUtils.isMapWorld(player.world)) {
                if (this.originalDimension != null) {
                    if (player.isAlive()) {
                        BlockPos pos = player.getBedLocation();
                        if (getMapDevicePos() != null) {
                            pos = getMapDevicePos();
                            pos = pos.north(2);
                            player.changeDimension(this.originalDimension, new MyTeleporter(player.world, pos, player));
                            player.setPosition(pos.getX(), pos.getY(), pos.getZ());
                        }

                    }
                } else {

                }
            }
        }

        private void announceEnd(EntityPlayer player) {

            player.sendMessage(SLOC.chat("mapworld_ran_out_of_time"));

        }

        private void announceTimeLeft(EntityPlayer player) {

            player.sendMessage(SLOC.chat("mapworld_time_left")
                    .appendText(" " + this.getMinutesLeft() + " ")
                    .appendSibling(SLOC.chat("minutes")));

        }

        private int getMinutesLeft() {
            return this.mapdata.minutes - this.minutesPassed;

        }
    }

    public static class Storage extends BaseStorage<IPlayerMapData> {

    }

}
