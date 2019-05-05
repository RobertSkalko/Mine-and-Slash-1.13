package com.robertx22.uncommon.capability;

import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.DimensionData;
import com.robertx22.saveclasses.MapDataList;
import com.robertx22.uncommon.datasaving.MapsNbt;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class DimsData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "map_dims_data");

    @CapabilityInject(IDimsData.class)
    public static final Capability<IDimsData> Data = null;

    public interface IDimsData {

        NBTTagCompound getNBT();

        void setNBT(NBTTagCompound value);

        void add(DimensionType type, IWP iwp);

        void unregisterAll();

        void registerAll();

        void remove(World world);

    }

    static final String MAP_OBJECT = "map_data_list";
    static final String ISRESERVED = "is_reserved";

    public static class DefaultImpl implements IDimsData {
        private NBTTagCompound nbt = new NBTTagCompound();

        MapDataList mapdata = new MapDataList();
        boolean reserved = false;

        @Override
        public NBTTagCompound getNBT() {

            nbt.setBoolean(ISRESERVED, reserved);

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
            this.mapdata.dimDatas.put(type.getRegistryName()
                    .toString(), new DimensionData(type, iwp));

        }

        @Override
        public void unregisterAll() {
            for (DimensionData data : this.mapdata.dimDatas.values()) {
                MapManager.expire(data.getResource());
            }

        }

        @Override
        public void registerAll() {
            for (DimensionData data : this.mapdata.dimDatas.values()) {
                MapManager.register(data.getResource(), data.getIWP());
            }

        }

        @Override
        public void remove(World world) {
            this.mapdata.dimDatas.remove(world.getDimension()
                    .getType()
                    .getRegistryName()
                    .toString());

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

    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {
        IDimsData impl = new DefaultImpl();
        private final LazyOptional<IDimsData> cap = LazyOptional.of(() -> impl);

        @Override
        public NBTTagCompound serializeNBT() {
            if (Data != null && Data.getStorage() != null) {
                return (NBTTagCompound) Data.getStorage().writeNBT(Data, impl, null);
            }
            return new NBTTagCompound();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            if (Data != null && Data.getStorage() != null) {
                Data.getStorage().readNBT(Data, impl, null, nbt);
            }
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
            if (cap == Data) {
                return this.cap.cast();
            }
            return LazyOptional.empty();
        }
    }

    public static class Storage implements IStorage<IDimsData> {
        @Override
        public INBTBase writeNBT(Capability<IDimsData> capability, IDimsData instance,
                                 EnumFacing side) {

            return instance.getNBT();
        }

        @Override
        public void readNBT(Capability<IDimsData> capability, IDimsData instance,
                            EnumFacing side, INBTBase nbt) {

            instance.setNBT((NBTTagCompound) nbt);

        }
    }

}