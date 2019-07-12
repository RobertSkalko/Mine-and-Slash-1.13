package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;

public class BonusDmgPotion extends SpellPotionBase implements IGenerated<SpellPotionBase> {

    public static final BonusDmgPotion INSTANCE = new BonusDmgPotion(Elements.Physical);

    public static final HashMap<Elements, BonusDmgPotion> MAP = new HashMap<>();

    private BonusDmgPotion(Elements element) {
        // boolean isBadEffectIn, int liquidColorIn
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
        this.element = element;

    }

    public Elements element;

    public static EffectInstance getFirstEffect(LivingEntity entity) {

        for (BonusDmgPotion effect : BonusDmgPotion.MAP.values()) {
            EffectInstance potion = entity.getActivePotionEffect(effect);
            if (potion != null && potion.getPotion() instanceof BonusDmgPotion) {
                return potion;
            }
        }
        return null;
    }

    @Override
    public String GUID() {
        return "bonus_" + element.dmgName.toLowerCase() + "_dmg";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, LivingEntity target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(LivingEntity entity, int amplifier) {

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public String locNameForLangFile() {
        return "Bonus " + element.dmgName + " Dmg";
    }

    @Override
    public List<SpellPotionBase> generateAllPossibleStatVariations() {
        Elements.getAllSingleElements().forEach(x -> MAP.put(x, new BonusDmgPotion(x)));
        return null;
    }
}
