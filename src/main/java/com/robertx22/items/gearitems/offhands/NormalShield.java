package com.robertx22.items.gearitems.offhands;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NormalShield extends ItemShield implements IEffectItem {

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

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new TextComponentString(""));
        list.add(new TextComponentString(color() + "" + TextFormatting.BOLD + "[Active]: " + TextFormatting.RESET + color() + "Block"));
        if (moreInfo) {
            list.add(new TextComponentString(color() + "DMG Reduced Based on Block Strength"));
        }
        return list;
    }

}