package com.robertx22.uncommon.capability;

import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.MapWorldPlayerListData;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.bases.BaseProvider;
import com.robertx22.uncommon.capability.bases.BaseStorage;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class WorldData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "worlddata");

    @CapabilityInject(IWorldData.class)
    public static final Capability<IWorldData> Data = null;

    public interface IWorldData extends ICommonCapability {

        boolean dropsUniques(World world);

        NBTTagCompound getNBT();

        void setNBT(NBTTagCompound value);

        boolean isMapWorld();

        void setIsMapWorld(boolean bool);

        String getWorldID();

        int getTier(World world);

        int getLevel();

        void init(BlockPos pos, World world, MapItemData map, String dimensionId,
                  EntityPlayer owner);

        MapItemData getMap();

        boolean isInit();

        boolean didntSetBackPortal();

        void setBackPortal();

        String getSaveName();

        void setSaveName(String name);

        BlockPos getMapDevicePos();

        DimensionType getOriginalDimension();

        void teleportPlayerBack(EntityPlayer player);

        void transferPlayersBack(World world);

        void passMinute(World world);

        void passAllTime(World world);

        void onPlayerDeath(EntityPlayer victim, World world);

        void setDelete(boolean bool, World world);

        boolean isReserved();

        void setReserved(boolean bool);

        boolean isOwner(EntityPlayer player);

    }

    @EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onWorldConstuct(AttachCapabilitiesEvent<World> event) {
            event.addCapability(RESOURCE, new Provider());
        }

    }

    public static class Provider extends BaseProvider<IWorldData> {

        @Override
        public IWorldData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IWorldData> dataInstance() {
            return Data;
        }
    }

    static final String SET_FOR_DELETE = "setForDelete";
    static final String IS_MAP_WORLD = "isMap";
    static final String LEVEL = "level";
    static final String OWNER = "owner";
    static final String TIER = "tier";
    static final String IS_INIT = "isInit";
    static final String ORIGINAL_DIM = "original_dimension";
    static final String MAP_DIM = "map_dimension_string";
    static final String DIDNT_SET_BACK_PORTAL = "didntSetBackPortal";
    static final String SAVE_NAME = "save_name";
    static final String POS_OBJ = "POS_OBJ";
    static final String MINUTES_PASSED = "minutes_passed";
    static final String ISRESERVED = "is_reserved";
    static final String ISPERMADEATH = "ISPERMADEATH";
    static final String ISFREE = "ISFREE";

    public static class DefaultImpl implements IWorldData {
        private NBTTagCompound nbt = new NBTTagCompound();

        long mapDevicePos;
        MapItemData mapdata = new MapItemData();
        int tier = 0;
        int level = 1;
        boolean isMap = false;
        boolean isInit = false;
        boolean didntSetBackPortal = true;
        String saveName = "";

        @Override
        public NBTTagCompound getNBT() {
            nbt.putInt(TIER, tier);
            nbt.putInt(LEVEL, level);
            nbt.putBoolean(IS_MAP_WORLD, isMap);
            nbt.putBoolean(IS_INIT, isInit);

            nbt.putBoolean(DIDNT_SET_BACK_PORTAL, didntSetBackPortal);
            nbt.putString(SAVE_NAME, saveName);

            if (mapdata != null) {
                Map.Save(nbt, mapdata);
            }

            nbt.putInt(TIER, tier);
            nbt.putInt(LEVEL, level);
            nbt.putLong(POS_OBJ, mapDevicePos);

            return nbt;

        }

        @Override
        public void setNBT(NBTTagCompound value) {
            this.nbt = value;
            tier = nbt.getInt(TIER);
            level = nbt.getInt(LEVEL);
            isMap = nbt.getBoolean(IS_MAP_WORLD);
            isInit = nbt.getBoolean(IS_INIT);

            this.didntSetBackPortal = nbt.getBoolean(DIDNT_SET_BACK_PORTAL);
            this.saveName = nbt.getString(SAVE_NAME);

            tier = nbt.getInt(TIER);
            mapdata = Map.Load(nbt);
            this.mapDevicePos = nbt.getLong(POS_OBJ);

        }

        @Override
        public boolean isMapWorld() {

            return isMap;

        }

        @Override
        public int getLevel() {
            return level;
        }

        @Override
        public void init(BlockPos pos, World world, MapItemData map, String dimensionId,
                         EntityPlayer owner) {

            this.isMap = true;
            this.level = map.level;
            this.tier = map.tier;
            this.mapdata = map;
            this.mapDevicePos = pos.toLong();
            this.isInit = true;

        }

        @Override
        public int getTier(World world, EntityPlayer player) {

            if (DimensionsContainer.INSTANCE.hasConfig(world)) {
                return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER;
            } else {

                Load.Unit(player).getTier();
            }

        }

        @Override
        public MapItemData getMap() {
            return mapdata;
        }

        @Override
        public boolean isInit() {
            return isInit;
        }

        @Override
        public boolean didntSetBackPortal() {
            return this.didntSetBackPortal;
        }

        @Override
        public void setBackPortal() {
            this.didntSetBackPortal = false;
        }

        @Override
        public String getSaveName() {
            return saveName;
        }

        @Override
        public void setSaveName(String name) {
            this.saveName = name;
        }

        @Override
        public MapWorldPlayerListData getWorldData() {
            return this.mapworlddata;
        }

        @Override
        public void setWorldData(MapWorldPlayerListData data) {
            this.mapworlddata = data;
        }

        @Override
        public void passMinute(World world) {

            if (this.isMap) {
                this.minutesPassed++;

                onMinutePassAnnounce(world);

                checkDeletition(world);
            }

        }

        private void onMinutePassAnnounce(World world) {
            int minutesLeft = getMinutesLeft();

            if (minutesLeft > 0) {
                if (minutesLeft < 5 || minutesLeft % 5 == 0) {
                    announceTimeLeft(world);
                }
            }
        }

        private void checkDeletition(World world) {

            if (this.getMinutesLeft() < 1) {
                this.setDelete(true, world);

            }

        }

        private int getMinutesLeft() {
            return this.mapdata.minutes - this.minutesPassed;

        }

        private void announceDeletition(World world) {
            for (EntityPlayer player : world.playerEntities) {
                player.sendMessage(SLOC.chat("mapworld_ran_out_of_time"));

            }
        }

        private void announceTimeLeft(World world) {

            for (EntityPlayer player : world.playerEntities) {
                player.sendMessage(SLOC.chat("mapworld_time_left")
                        .appendText(" " + this.getMinutesLeft() + " ")
                        .appendSibling(SLOC.chat("minutes")));

            }

        }

        @Override
        public void setDelete(boolean bool, World world) {
            this.setForDelete = bool;

            if (bool) {

                announceDeletition(world);

                this.transferPlayersBack(world);
            }

        }

        @Override
        public void onPlayerDeath(EntityPlayer victim, World world) {

            if (this.isPermaDeath()) {

                this.minutesPassed += 555555;

                for (EntityPlayer player : world.playerEntities) {
                    player.sendMessage(SLOC.chat("player_died_mapworld")
                            .appendText(" " + victim.getDisplayName()
                                    .getFormattedText() + " ")
                            .appendText("World closing due to permadeath"));
                }

            } else {
                int punishment = 5;

                for (EntityPlayer player : world.playerEntities) {
                    player.sendMessage(SLOC.chat("player_died_mapworld")
                            .appendText(" " + victim.getDisplayName()
                                    .getFormattedText() + " ")
                            .appendSibling(SLOC.chat("activating_mapworld_time_penalty")));
                }

                this.minutesPassed += punishment;

                announceTimeLeft(world);
            }

            checkDeletition(world);

        }

        @Override
        public boolean isReserved() {
            return reserved;
        }

        @Override
        public void setReserved(boolean bool) {
            this.reserved = bool;

        }

        @Override
        public boolean isOwner(EntityPlayer player) {
            return player.getUniqueID().toString().equals(this.owner);
        }

        @Override
        public boolean isFree() {
            return this.isFree;
        }

        @Override
        public void setIsFree(boolean bool) {
            this.isFree = bool;
        }

        @Override
        public void setPermaDeath(boolean bool) {
            this.isPermaDeath = bool;
        }

        @Override
        public boolean isPermaDeath() {
            return isPermaDeath;
        }

        @Override
        public boolean dropsUniques(World world) {

            if (isMap == true) {
                return true;
            }

            return DimensionsContainer.INSTANCE.getConfig(world).DROPS_UNIQUE_ITEMS;
        }

        @Override
        public void passAllTime(World world) {

            this.minutesPassed += 500000;

            this.passMinute(world);

        }

    }

    public static class Storage extends BaseStorage<IWorldData> {

    }
}
