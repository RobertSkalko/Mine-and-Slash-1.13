package com.robertx22.mmorpg.registers.common;

import com.robertx22.items.consumables.RestoreEnergyItem;
import com.robertx22.items.consumables.RestoreManaItem;
import com.robertx22.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConsumableRegister {

    public static final String RESTORE_MANA_ID = Ref.MODID + ":restore_mana_item";
    public static final String RESTORE_ENERGY_ID = Ref.MODID + ":restore_energy_item";

    @ObjectHolder(RESTORE_MANA_ID)
    public static Block RESTORE_MANA;
    @ObjectHolder(RESTORE_ENERGY_ID)
    public static Block RESTORE_ENERGY;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();

        reg.register(new RestoreEnergyItem());
        reg.register(new RestoreManaItem());
    }

}
