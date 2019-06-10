package com.robertx22.mmorpg.registers;

import com.robertx22.blocks.gear_factory_station.ContainerGearFactory;
import com.robertx22.blocks.item_modify_station.ContainerGearModify;
import com.robertx22.blocks.map_device.ContainerMapDevice;
import com.robertx22.blocks.repair_station.ContainerGearRepair;
import com.robertx22.blocks.salvage_station.ContainerGearSalvage;
import com.robertx22.items.bags.currency_bag.ContainerCurrencyBag;
import com.robertx22.items.bags.loot_bag.ContainerLootBag;
import com.robertx22.items.bags.map_bag.ContainerMapBag;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerTypeRegisters {

    static final String LOOT_BAG_ID = Ref.MODID + ":" + "loot_bag";
    static final String MAP_BAG_ID = Ref.MODID + ":" + "map_bag";
    static final String CURRENCY_BAG_ID = Ref.MODID + ":" + "currency_bag";

    @ObjectHolder(BlockRegister.GEAR_FACTORY_ID)
    public static final ContainerType<ContainerGearFactory> GEAR_FACTORY = null;
    @ObjectHolder(BlockRegister.GEAR_MODIFY_ID)
    public static final ContainerType<ContainerGearModify> GEAR_MODIFY = null;
    @ObjectHolder(BlockRegister.GEAR_REPAIR_ID)
    public static final ContainerType<ContainerGearRepair> GEAR_REPAIR = null;
    @ObjectHolder(BlockRegister.GEAR_SALVAGE_ID)
    public static final ContainerType<ContainerGearSalvage> GEAR_SALVAGE = null;
    @ObjectHolder(BlockRegister.MAP_DEVICE_ID)
    public static final ContainerType<ContainerMapDevice> MAP_DEVICE = null;
    @ObjectHolder(LOOT_BAG_ID)
    public static final ContainerType<ContainerLootBag> LOOT_BAG = null;
    @ObjectHolder(MAP_BAG_ID)
    public static final ContainerType<ContainerMapBag> MAP_BAG = null;
    @ObjectHolder(CURRENCY_BAG_ID)
    public static final ContainerType<ContainerCurrencyBag> CURRENCY_BAG = null;

    @SubscribeEvent
    public static void registerContainers(
            final RegistryEvent.Register<ContainerType<?>> event) {

        IForgeRegistry<ContainerType<?>> r = event.getRegistry();

        r.register(new ContainerType<>(ContainerGearFactory::new).setRegistryName(BlockRegister.GEAR_FACTORY_ID));
        r.register(new ContainerType<>(ContainerGearModify::new).setRegistryName(BlockRegister.GEAR_MODIFY_ID));
        r.register(new ContainerType<>(ContainerGearRepair::new).setRegistryName(BlockRegister.GEAR_REPAIR_ID));
        r.register(new ContainerType<>(ContainerGearSalvage::new).setRegistryName(BlockRegister.GEAR_SALVAGE_ID));
        r.register(new ContainerType<>(ContainerMapDevice::new).setRegistryName(BlockRegister.MAP_DEVICE_ID));

        r.register(new ContainerType<>(ContainerMapBag::new).setRegistryName(MAP_BAG_ID));
        // r.register(new ContainerType<>(ContainerLootBag::new).setRegistryName(LOOT_BAG_ID));
        // r.register(new ContainerType<>(ContainerCurrencyBag::new).setRegistryName(CURRENCY_BAG_ID));

    }

}
