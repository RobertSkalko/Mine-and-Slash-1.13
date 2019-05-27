package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class BowWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new TextComponentString(Styles.GREEN + "Double Attack");
    }

    @Override
    public float GetEnergyCost() {
        return 9;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Bow;
    }

    @Override
    public boolean Attack(EntityLivingBase source, EntityLivingBase target,
                          EntityData.UnitData unitsource,
                          EntityData.UnitData targetUnit) {

        super.AttackXTimes(source, target, unitsource, targetUnit, 2);

        return true;
    }
}
