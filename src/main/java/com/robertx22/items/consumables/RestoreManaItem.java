package com.robertx22.items.consumables;

import com.robertx22.items.consumables.bases.BaseConsumabletem;
import com.robertx22.items.consumables.bases.IAmount;
import com.robertx22.mmorpg.registers.common.ConsumableRegister;
import com.robertx22.uncommon.capability.EntityData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class RestoreManaItem extends BaseConsumabletem implements IAmount {

    @Override
    public ITextComponent tooltip() {

        ITextComponent comp = new TextComponentString("Restores " + amount() + " Mana");

        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, EntityLivingBase player,
                         EntityData.UnitData unitdata) {

        unitdata.restoreMana(amount());

    }

    @Override
    public String GUID() {
        return ConsumableRegister.RESTORE_MANA_ID;
    }

    @Override
    public float amount() {
        return 20;
    }
}
