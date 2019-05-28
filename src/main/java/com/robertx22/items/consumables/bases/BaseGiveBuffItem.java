package com.robertx22.items.consumables.bases;

import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public abstract class BaseGiveBuffItem extends BaseConsumabletem {

    @Override
    public ITextComponent tooltip() {
        return Styles.GREENCOMP()
                .appendSibling(new TextComponentString("Gives ").appendSibling(potion().locName())
                        .appendText(" Buff"));
    }

    public abstract SpellPotionBase potion();

    public abstract int seconds();

    @Override
    public void onFinish(ItemStack stack, World world, EntityLivingBase player,
                         EntityData.UnitData unitdata) {

        player.addPotionEffect(new PotionEffect(potion(), seconds() * 20));

    }

}
