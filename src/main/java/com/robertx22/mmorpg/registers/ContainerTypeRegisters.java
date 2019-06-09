package com.robertx22.mmorpg.registers;

import com.robertx22.blocks.gear_factory_station.ContainerGearFactory;
import com.robertx22.mmorpg.Ref;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerTypeRegisters {

    static final String GEAR_FACTORY_ID = Ref.MODID + ":" + "gear_factory";
    static final String GEAR_MODIFY_ID = Ref.MODID + ":" + "gear_modify";
    static final String GEAR_REPAIR_ID = Ref.MODID + ":" + "gear_repair";
    static final String GEAR_SALVAGE_ID = Ref.MODID + ":" + "gear_salvage";
    static final String MAP_DEVICE_ID = Ref.MODID + ":" + "map_device";

    static final String LOOT_BAG_ID = Ref.MODID + ":" + "loot_bag";
    static final String MAP_BAG_ID = Ref.MODID + ":" + "map_bag";
    static final String CURRENCY_BAG_ID = Ref.MODID + ":" + "currency_bag";

    @ObjectHolder(GEAR_FACTORY_ID)
    public static final ContainerType<ContainerGearFactory> GEAR_FACTORY = null;
    @ObjectHolder(GEAR_MODIFY_ID)
    public static final ContainerType<ContainerGearFactory> GEAR_MODIFY = null;
    @ObjectHolder(GEAR_REPAIR_ID)
    public static final ContainerType<ContainerGearFactory> GEAR_REPAIR = null;
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static final ContainerType<ContainerGearFactory> GEAR_SALVAGE = null;
    @ObjectHolder(MAP_DEVICE_ID)
    public static final ContainerType<ContainerGearFactory> MAP_DEVICE = null;
    @ObjectHolder(LOOT_BAG_ID)
    public static final ContainerType<ContainerGearFactory> LOOT_BAG = null;
    @ObjectHolder(MAP_BAG_ID)
    public static final ContainerType<ContainerGearFactory> MAP_BAG = null;
    @ObjectHolder(CURRENCY_BAG_ID)
    public static final ContainerType<ContainerGearFactory> CURRENCY_BAG = null;

    @SubscribeEvent
    public static void registerContainers(
            final RegistryEvent.Register<ContainerType<?>> event) {

        IForgeRegistry<ContainerType<?>> r = event.getRegistry();

        r.register(new ContainerType<>(ContainerGearFactory::new).setRegistryName(GEAR_FACTORY_ID));

    }

}
