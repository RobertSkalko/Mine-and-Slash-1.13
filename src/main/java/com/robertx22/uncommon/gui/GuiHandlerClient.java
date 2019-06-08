package com.robertx22.uncommon.gui;

import com.robertx22.blocks.gear_factory_station.ContainerGearFactory;
import com.robertx22.blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.blocks.gear_factory_station.TileGearFactory;
import com.robertx22.blocks.item_modify_station.GuiInventoryModify;
import com.robertx22.blocks.item_modify_station.TileInventoryModify;
import com.robertx22.blocks.map_device.GuiMap;
import com.robertx22.blocks.map_device.TileMap;
import com.robertx22.blocks.repair_station.GuiInventoryRepair;
import com.robertx22.blocks.repair_station.TileInventoryRepair;
import com.robertx22.blocks.salvage_station.GuiInventorySalvage;
import com.robertx22.blocks.salvage_station.TileInventorySalvage;
import com.robertx22.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.items.bags.currency_bag.InventoryCurrencyBag;
import com.robertx22.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.items.bags.loot_bag.GuiLootBag;
import com.robertx22.items.bags.loot_bag.InventoryLootBag;
import com.robertx22.items.bags.loot_bag.ItemLootBag;
import com.robertx22.items.bags.map_bag.GuiMapBag;
import com.robertx22.items.bags.map_bag.InventoryMapBag;
import com.robertx22.items.bags.map_bag.ItemMapBag;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import info.loenwind.autosave.handlers.java.HandleEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandlerClient {

    public static Screen getClientGuiElement(FMLPlayMessages.OpenContainer msg) {

        PlayerEntity player = Minecraft.getInstance().player;

        BlockPos pos = null;
        TileEntity te = null;

        try { // if it's from tileentity, it will send pos dataInstance, otherwise not and causes errors
            pos = msg.getAdditionalData().readBlockPos();
            te = Minecraft.getInstance().world.getTileEntity(pos);

        } catch (Exception e) {

        }

        if (te != null) {
            switch (msg.getType()) {

                case ContainerGearFactory.TYPE: {
                    if (te instanceof TileGearFactory) {
                        TileGearFactory tile = (TileGearFactory) te;
                        ContainerGearFactory container = (ContainerGearFactory) tile.createMenu(0, player.inventory, player);
                        return new GuiGearFactory(container, player.inventory, tile);
                    }
                    break;
                }
                case BlockRegister.GEAR_MODIFY_ID: {
                    if (te instanceof TileInventoryModify) {
                        return new GuiInventoryModify(player.inventory, (TileInventoryModify) te);
                    }
                    break;
                }
                case BlockRegister.GEAR_SALVAGE_ID: {
                    if (te instanceof TileInventorySalvage) {
                        return new GuiInventorySalvage(player.inventory, (TileInventorySalvage) te);
                    }
                    break;
                }
                case BlockRegister.MAP_DEVICE_ID: {
                    if (te instanceof TileMap) {
                        return new GuiMap(player.inventory, (TileMap) te);
                    }
                    break;
                }
                case BlockRegister.GEAR_REPAIR_ID: {
                    if (te instanceof TileInventoryRepair) {
                        return new GuiInventoryRepair(player.inventory, (TileInventoryRepair) te);
                    }
                    break;
                }
            }
        } else { // it means it's from a bag then, no tileentity but does have a hand boolean

            boolean isOffHand = msg.getAdditionalData().readBoolean();
            HandleEnum hand = isOffHand ? HandleEnum.OFF_HAND : HandleEnum.MAIN_HAND;

            switch (msg.getId().toString()) {

                case ItemCurrencyBag.ID: {
                    ItemStack item = player.getHeldItem(hand);
                    return new GuiCurrencyBag(player.inventory, new InventoryCurrencyBag(item));
                }
                case ItemLootBag.ID: {
                    ItemStack item = player.getHeldItem(hand);
                    return new GuiLootBag(player.inventory, new InventoryLootBag(item));
                }
                case ItemMapBag.ID: {
                    ItemStack item = player.getHeldItem(hand);
                    return new GuiMapBag(player.inventory, new InventoryMapBag(item));
                }

            }

        }
        return null;
    }

}