package com.robertx22.items.gearitems.offhands;

import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IGearItem;
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

public class NormalShield extends ItemShield implements IEffectItem, IAutoLocName, IGearItem {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    ResourceLocation resource = new ResourceLocation("");

    public NormalShield(int rarity, Properties prop, String name) {

        super(prop);
        this.rarity = rarity;
        resource = new ResourceLocation("mmorpg:textures/shield/" + name + ".png");

    }

    public int rarity = 0;

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Shield";
    }

    @Override
    public String locNameLangFileGUID(String guid) {
        return formatString(this.getRegistryName().toString());
    }

    @Override
    public String GUID() {
        return "";
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