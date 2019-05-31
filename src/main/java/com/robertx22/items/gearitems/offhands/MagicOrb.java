package com.robertx22.items.gearitems.offhands;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class MagicOrb extends Item {
    ResourceLocation resource = new ResourceLocation("");
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public MagicOrb(Properties prop, String name) {

        super(prop);

        resource = new ResourceLocation("mmorpg:textures/items/magic_orb/" + name + ".png");

    }
}
