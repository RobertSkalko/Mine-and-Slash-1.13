package com.robertx22.uncommon.gui;

import com.robertx22.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.items.bags.currency_bag.InventoryCurrencyBag;
import com.robertx22.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.items.bags.loot_bag.GuiLootBag;
import com.robertx22.items.bags.loot_bag.InventoryLootBag;
import com.robertx22.items.bags.loot_bag.ItemLootBag;
import com.robertx22.items.bags.map_bag.GuiMapBag;
import com.robertx22.items.bags.map_bag.InventoryMapBag;
import com.robertx22.items.bags.map_bag.ItemMapBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandlerClient {

    public static Screen getClientGuiElement(FMLPlayMessages.OpenContainer msg) {

        PlayerEntity player = Minecraft.getInstance().player;

        BlockPos pos = null;
        TileEntity te = null;

        try { // if it's from tileentity, it will send pos dataInstance, otherwise not and causes errors
            pos = msg.getAdditionalData().readBlockPos();
            te = Minecraft.getInstance().player.world.getTileEntity(pos);

        } catch (Exception e) {

        }

        if (te != null) {

            msg.getType().func_221506_a(0, player.inventory);

        } else { // it means it's from a bag then, no tileentity but does have a hand boolean

            boolean isOffHand = msg.getAdditionalData().readBoolean();
            Hand hand = isOffHand ? Hand.OFF_HAND : Hand.MAIN_HAND;

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