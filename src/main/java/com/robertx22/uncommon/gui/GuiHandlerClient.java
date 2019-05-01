package com.robertx22.uncommon.gui;

import com.robertx22.blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.blocks.gear_factory_station.TileGearFactory;
import com.robertx22.blocks.item_modify_station.GuiInventoryModify;
import com.robertx22.blocks.item_modify_station.StartupModify;
import com.robertx22.blocks.item_modify_station.TileInventoryModify;
import com.robertx22.blocks.map_device.GuiMap;
import com.robertx22.blocks.map_device.StartupMap;
import com.robertx22.blocks.map_device.TileMap;
import com.robertx22.blocks.repair_station.GuiInventoryRepair;
import com.robertx22.blocks.repair_station.StartupRepair;
import com.robertx22.blocks.repair_station.TileInventoryRepair;
import com.robertx22.blocks.salvage_station.GuiInventorySalvage;
import com.robertx22.blocks.salvage_station.StartupSalvage;
import com.robertx22.blocks.salvage_station.TileInventorySalvage;
import com.robertx22.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.items.bags.currency_bag.InteractCurrencyBag;
import com.robertx22.items.bags.currency_bag.InventoryCurrencyBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandlerClient {

    public static GuiScreen getClientGuiElement(FMLPlayMessages.OpenContainer msg) {

        EntityPlayer player = Minecraft.getInstance().player;

        BlockPos pos = null;
        TileEntity te = null;

        try {
            pos = msg.getAdditionalData().readBlockPos();
            te = Minecraft.getInstance().world.getTileEntity(pos);

        } catch (Exception e) {

        }

        String test = msg.getId().toString();

        if (te != null) {
            switch (msg.getId().toString()) {

                case StartupGearFactory.GEAR_FACTORY_ID: {
                    if (te instanceof TileGearFactory) {
                        return new GuiGearFactory(player.inventory, (TileGearFactory) te);
                    }
                    break;
                }
                case StartupModify.ID: {
                    if (te instanceof TileInventoryModify) {
                        return new GuiInventoryModify(player.inventory, (TileInventoryModify) te);
                    }
                    break;
                }
                case StartupSalvage.ID: {
                    if (te instanceof TileInventorySalvage) {
                        return new GuiInventorySalvage(player.inventory, (TileInventorySalvage) te);
                    }
                    break;
                }
                case StartupMap.ID: {
                    if (te instanceof TileMap) {
                        return new GuiMap(player.inventory, (TileMap) te);
                    }
                    break;
                }
                case StartupRepair.ID: {
                    if (te instanceof TileInventoryRepair) {
                        return new GuiInventoryRepair(player.inventory, (TileInventoryRepair) te);
                    }
                    break;
                }
            }
        } else {
            boolean isOffHand = msg.getAdditionalData().readBoolean();

            EnumHand hand = isOffHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;

            switch (msg.getId().toString()) {

                case InteractCurrencyBag.ID: {
                    ItemStack item = player.getHeldItem(hand);
                    return new GuiCurrencyBag(player.inventory, new InventoryCurrencyBag(item));
                }

            }

        }
        return null;
    }

}