package com.mmorpg_libraries.curios;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.ICurioItemHandler;
import top.theillusivec4.curios.api.inventory.CurioStackHandler;

import java.util.ArrayList;
import java.util.List;

public class MyCurioUtils {

    public static List<ItemStack> getAllSlots(EntityPlayer player) {

        List<ItemStack> list = new ArrayList<>();

        ICurioItemHandler handler = CuriosAPI.getCuriosHandler(player).orElse(null);

        if (handler != null) {

            for (CurioStackHandler value : handler.getCurioMap().values()) {

                for (int i = 0; i < value.getSlots(); i++) {

                    ItemStack stack = value.getStackInSlot(i);

                    if (stack.isEmpty() == false) {
                        list.add(stack);
                    }
                }

            }

        }

        return list;

    }

    // defaults to first slot
    public static ItemStack getSlot(EntityPlayer player, CurioSlots slot) {

        ICurioItemHandler handler = CuriosAPI.getCuriosHandler(player).orElse(null);

        if (handler != null) {

            return handler.getStackInSlot(slot.name, 0);

        }

        return ItemStack.EMPTY;

    }

}
