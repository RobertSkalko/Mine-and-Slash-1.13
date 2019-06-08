package com.robertx22.items.consumables.bases;

import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public abstract class BaseGiveBuffItem extends BaseConsumabletem {

    @Override
    public ITextComponent tooltip() {
        return Styles.GREENCOMP()
                .appendSibling(new StringTextComponent("Gives ").appendSibling(potion().locName())
                        .appendText(" Buff"));
    }

    public abstract SpellPotionBase potion();

    public abstract int seconds();

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityData.UnitData unitdata) {

        player.addPotionEffect(new EffectInstance(potion(), seconds() * 20));

    }

}
