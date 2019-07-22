package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import net.minecraft.item.ItemStack;

public abstract class BaseBlueprintRequest implements ITooltipList {

    public abstract boolean matches(ItemStack stack);

}
