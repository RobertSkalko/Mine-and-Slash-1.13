package com.robertx22.uncommon.capability;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.StructuresData;
import com.robertx22.uncommon.capability.bases.BaseProvider;
import com.robertx22.uncommon.capability.bases.BaseStorage;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.Structures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MapData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "mapdata");

    @CapabilityInject(IMapData.class)
    public static final Capability<IMapData> Data = null;

    static final String STRUCTURE_DATAS = "STRUCTURE_DATAS";

    public interface IMapData extends ICommonCapability {

        StructuresData getStructuresData();

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<World> event) {
            event.addCapability(RESOURCE, new Provider());
        }

    }

    public static class Provider extends BaseProvider<IMapData> {

        @Override
        public IMapData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IMapData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IMapData {

        private CompoundNBT nbt = new CompoundNBT();

        StructuresData structuresData = new StructuresData();

        @Override
        public CompoundNBT getNBT() {

            if (structuresData != null) {
                Structures.Save(nbt, structuresData);
            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT value) {
            this.nbt = value;

            structuresData = Structures.Load(nbt);

        }

        @Override
        public StructuresData getStructuresData() {
            return this.structuresData;
        }
    }

    public static class Storage extends BaseStorage<IMapData> {

    }

}
