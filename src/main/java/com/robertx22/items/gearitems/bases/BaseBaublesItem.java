package com.robertx22.items.gearitems.bases;

import com.robertx22.uncommon.interfaces.IAutoLocName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseBaublesItem extends Item implements IAutoLocName {

    public int rarity = 0;

    public BaseBaublesItem(int rar) {

        super(new Properties().maxStackSize(1));
        this.rarity = rar;
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    EnumHand hand) {

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

}
