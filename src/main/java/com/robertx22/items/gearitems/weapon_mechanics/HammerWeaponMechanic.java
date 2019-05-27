package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new TextComponentString(Styles.GREEN + "Aoe Attack");
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
    public boolean Attack(EntityLivingBase source, EntityLivingBase target,
                          UnitData unitsource, UnitData targetUnit) {

        List<EntityLivingBase> entities = new ArrayList<EntityLivingBase>();

        for (Entity en : target.world.getEntitiesWithinAABBExcludingEntity(source, new AxisAlignedBB(target.posX - radius, target.posY - radius, target.posZ - radius, target.posX + radius, target.posY + radius, target.posZ + radius))) {
            if (en instanceof EntityLivingBase) {
                entities.add((EntityLivingBase) en);
            }
        }
        int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;

        for (EntityLivingBase entity : entities) {

            DamageEffect dmg = new DamageEffect(source, entity, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer);
            dmg.Activate();
        }

        return true;
    }

}
