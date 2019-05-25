package com.robertx22.uncommon.capability;

import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.DimensionData;
import com.robertx22.saveclasses.MapDataList;
import com.robertx22.uncommon.capability.bases.BaseProvider;
import com.robertx22.uncommon.capability.bases.BaseStorage;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.MapsNbt;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class DimsData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "map_dims_data");

    @CapabilityInject(IDimsData.class)
    public static final Capability<IDimsData> Data = null;

    public interface IDimsData extends ICommonCapability {

        void add(DimensionType type, IWP iwp);

        void add(String type, String iwp);

        void unregisterAll();

        void registerAll();

        void remove(World world);

        DimensionData getFreeDimension();

    }

    static final String ISRESERVED = "is_reserved";

    public static class DefaultImpl implements IDimsData {
        private NBTTagCompound nbt = new NBTTagCompound();

        MapDataList mapdata = new MapDataList();
        boolean reserved = false;

        @Override
        public NBTTagCompound getNBT() {

            nbt.putBoolean(ISRESERVED, reserved);

            if (mapdata != null) {
                MapsNbt.Save(nbt, mapdata);

            }

            return nbt;

        }

        @Override
        public void setNBT(NBTTagCompound value) {
            this.nbt = value;

            this.reserved = nbt.getBoolean(ISRESERVED);

            mapdata = MapsNbt.Load(nbt);

        }

        @Override
        public void add(DimensionType type, IWP iwp) {
            this.mapdata.dimDatas.put(DimensionType.getKey(type)
                    .toString(), new DimensionData(type, iwp));

        }

        @Override
        public void add(String type, String iwp) {
            this.mapdata.dimDatas.put(type, new DimensionData(type, iwp));

        }

        @Override
        public void unregisterAll() {
            for (DimensionData data : this.mapdata.dimDatas.values()) {
                MapManager.expire(data.getResource());
            }

        }

        @Override
        public void registerAll() {

            if (this.mapdata == null || this.mapdata.dimDatas == null) {
                return;
            }

            for (DimensionData data : this.mapdata.dimDatas.values()) {
                MapManager.register(data.getResource(), data.getIWP());
            }

        }

        @Override
        public void remove(World world) {
            this.mapdata.dimDatas.remove(MapManager.getResourceLocation(world.getDimension()
                    .getType()).toString());

        }

        private DimensionData randomData() {
            if (mapdata.dimDatas.values().size() > 0) {
                return mapdata.dimDatas.values()
                        .toArray(new DimensionData[]{})[RandomUtils.RandomRange(0, mapdata.dimDatas
                        .size())];
            } else {
                return null;
            }
        }

        @Override
        public DimensionData getFreeDimension() {

            int tries = 0;
            while (true) {
                tries++;

                DimensionData data = randomData();

                DimensionType type = data.getDimensionType();
                World world = MapManager.getWorld(type);
                WorldData.IWorldData worlddata = Load.World(world);

                if (worlddata.isFree()) {
                    worlddata.setIsFree(false);
                    return data;
                }

                if (tries > 500) {
                    return null;
                }

            }

        }
    }

    @EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onWorldConstuct(AttachCapabilitiesEvent<World> event) {

            if (event.getObject()
                    .getDimension()
                    .getType()
                    .equals(DimensionType.OVERWORLD)) {

                event.addCapability(RESOURCE, new Provider());

            }
        }

    }

    public static class Provider extends BaseProvider<IDimsData> {

        @Override
        public IDimsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IDimsData> dataInstance() {
            return Data;
        }
    }

    public static class Storage extends BaseStorage<IDimsData> {

    }

}