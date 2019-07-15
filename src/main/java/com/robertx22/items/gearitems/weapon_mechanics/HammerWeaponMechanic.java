package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.localization.Styles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Aoe Attack");
    }

    @Override
    public float GetEnergyCost() {
        return 10;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Hammer;
    }

    float radius = 1.5F;

    @Override
    public boolean Attack(LivingEntity source, LivingEntity target, UnitData unitsource,
                          UnitData targetUnit) {

        List<LivingEntity> entities = new ArrayList<LivingEntity>();

        for (Entity en : target.world.getEntitiesWithinAABBExcludingEntity(source, new AxisAlignedBB(target.posX - radius, target.posY - radius, target.posZ - radius, target.posX + radius, target.posY + radius, target.posZ + radius))) {
            if (en instanceof LivingEntity) {
                entities.add((LivingEntity) en);
            }
        }
        int num = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;

        for (LivingEntity entity : entities) {

            DamageEffect dmg = new DamageEffect(source, entity, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer);
            dmg.Activate();
        }

        return true;
    }

}
