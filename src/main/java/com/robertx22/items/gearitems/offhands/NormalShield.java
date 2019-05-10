package com.robertx22.items.gearitems.offhands;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;

public class NormalShield extends ItemShield {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    ResourceLocation resource = new ResourceLocation("");

    public NormalShield(Properties prop, String name) {

        super(prop);

        resource = new ResourceLocation("mmorpg:textures/shield/" + name + ".png");

    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
        return true;
    }

}