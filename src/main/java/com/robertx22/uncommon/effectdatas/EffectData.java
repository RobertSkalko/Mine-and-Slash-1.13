package com.robertx22.uncommon.effectdatas;

import com.robertx22.db_lists.Stats;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffect.EffectSides;
import com.robertx22.uncommon.interfaces.IStatEffects;
import net.minecraft.entity.EntityLivingBase;

import java.util.ArrayList;
import java.util.List;

public abstract class EffectData {

    public EffectData(EntityLivingBase source, EntityLivingBase target) {

        this.Source = source;
        this.Target = target;

        if (target != null) {
            this.targetData = Load.Unit(target);
        }
        if (source != null) {
            this.sourceData = Load.Unit(source);

        }
        if (source != null) {

            try {
                if (target != null) {
                    targetUnit = targetData.getUnit();
                }

                sourceUnit = sourceData.getUnit();

                if (sourceUnit != null) {
                    sourceData.recalculateStats(source);

                } else {
                    this.canceled = true;
                }
                if (targetUnit != null) {

                    targetData.recalculateStats(target);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public EffectData(EntityLivingBase source, EntityLivingBase target,
                      UnitData sourceData, UnitData targetData) {

        this.Source = source;
        this.Target = target;

        if (sourceData != null && targetData != null) {
            this.sourceData = sourceData;
            this.targetData = targetData;

            this.sourceUnit = sourceData.getUnit();
            this.targetUnit = targetData.getUnit();

        } else {
            this.canceled = true;
        }

    }

    private EffectTypes effectType = EffectTypes.BASIC_ATTACK;

    public EffectTypes getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectTypes effectType, WeaponTypes weaponType) {
        this.effectType = effectType;
        this.weaponType = weaponType;
    }

    public WeaponTypes weaponType = WeaponTypes.None;

    public enum EffectTypes {
        NORMAL, SPELL, BASIC_ATTACK, BONUS_ATTACK
    }

    public UnitData sourceData;
    public UnitData targetData;

    public boolean canceled = false;
    public Unit sourceUnit;
    public Unit targetUnit;

    public EntityLivingBase Source;
    public EntityLivingBase Target;

    public float Number = 0;

    public Unit GetSource() {

        return sourceUnit;
    }

    public Unit GetTarget() {
        return targetUnit;
    }

    public void Activate() {

        if (Source == null || Target == null || canceled == true || sourceUnit == null || targetUnit == null || sourceData == null || targetData == null)
            return;

        TryApplyEffects(this.GetSource());
        TryApplyEffects(this.GetTarget());

        if (this.canceled != true) {

            activate();

        }
    }

    protected abstract void activate();

    protected EffectData TryApplyEffects(Unit unit) {

        if (this.canceled) {
            return this;
        }

        EffectData Data = this;

        List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

        Effects = AddEffects(Effects, unit);

        Effects.sort(new EffectUnitStat());

        for (EffectUnitStat item : Effects) {
            if (item.stat.Value != 0) {
                if (AffectsThisUnit(item.effect, Data, item.source)) {
                    item.effect.TryModifyEffect(Data, item.source, item.stat, item.stat.GetStat());
                }

            }
        }

        return Data;
    }

    public boolean AffectsThisUnit(IStatEffect effect, EffectData data, Unit source) {

        if (effect.Side().equals(EffectSides.Target)) {
            return source.equals(data.targetUnit);

        } else {
            return source.equals(data.sourceUnit);
        }
    }

    private List<EffectUnitStat> AddEffects(List<EffectUnitStat> effects, Unit unit) {
        if (unit != null) {

            for (IStatEffects stateffects : Stats.allPreGenMapStatLists.get(IStatEffects.class)) {
                for (IStatEffect stateffect : stateffects.GetEffects()) {
                    effects.add(new EffectUnitStat(stateffect, unit, unit.MyStats.get(stateffects
                            .GUID())));
                }

            }

        }
        return effects;
    }

}