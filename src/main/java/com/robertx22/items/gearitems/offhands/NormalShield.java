package com.robertx22.items.gearitems.offhands;

import com.robertx22.mmorpg.Ref;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.HashMap;

public class NormalShield extends ItemShield {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public static final String ID = "shields/normal_shield";
    @ObjectHolder(Ref.MODID + ":" + ID)
    public static final Item ITEM = null;

    ResourceLocation resource = new ResourceLocation("");

    public NormalShield(String name) {

        super(new Properties().defaultMaxDamage(750).setTEISR(ShieldRenderer::new));

        resource = new ResourceLocation("mmorpg:textures/shield/" + name + ".png");

    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
        return true;
    }

}