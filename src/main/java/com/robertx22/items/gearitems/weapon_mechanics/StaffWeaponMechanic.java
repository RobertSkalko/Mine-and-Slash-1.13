package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.EntityLivingBase;

public class StaffWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
        return 9;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Staff;
    }

    @Override
    public boolean Attack(EntityLivingBase source, EntityLivingBase target,
                          UnitData unitsource, UnitData targetUnit) {

        super.AttackXTimes(source, target, unitsource, targetUnit, 2);

        return true;
    }
}
