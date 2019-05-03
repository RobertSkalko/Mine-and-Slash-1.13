package com.robertx22.spells.self;

import com.robertx22.potion_effects.all.AoeRegenPotion;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.SpellBuffCheck;
import com.robertx22.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;

public abstract class BaseSpellHeal extends BaseSpell {

    @Override
    public SpellType Type() {
        return SpellType.Self_Heal;
    }

    @Override
    public Elements Element() {
        return Elements.None;
    }

    public int Weight() {
        return super.Weight() * 3;
    }

    public void checkZephyrSpeedBoost(EntityPlayer caster, SpellBuffCheck buffable) {

        if (buffable.getBuff().equals(SpellBuffType.Zephyr_Speed_Boost)) {
            caster.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200));
        }

    }

    public void checkPurityRemoveNegativeEffect(EntityPlayer caster,
                                                SpellBuffCheck buffable) {

        if (buffable.getBuff().equals(SpellBuffType.Purity_Remove_Negative_Effects)) {
            for (PotionEffect pot : new ArrayList<PotionEffect>(caster.getActivePotionEffects())) {
                if (pot.getPotion().isBadEffect()) {
                    caster.removePotionEffect(pot.getPotion());
                    break;
                }
            }
        }

    }

    public void checkAddLightBuff(EntityPlayer caster, SpellBuffCheck buffable) {
        if (buffable.getBuff().equals(SpellBuffType.Light_Aoe_Regen)) {
            caster.addPotionEffect(new PotionEffect(AoeRegenPotion.INSTANCE, 200));
        }

    }

    public void checkSpellBuffs(EntityPlayer caster, SpellBuffCheck buffable) {
        checkZephyrSpeedBoost(caster, buffable);
        checkAddLightBuff(caster, buffable);
        checkPurityRemoveNegativeEffect(caster, buffable);
    }

}
