package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class AxeWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new TextComponentString(Styles.GREEN + "Double Attack");
    }

    @Override
    public float GetEnergyCost() {
        return 8.5F;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Axe;
    }

    @Override
    public boolean Attack(EntityLivingBase source, EntityLivingBase target,
                          UnitData unitsource, UnitData targetUnit) {

        super.AttackXTimes(source, target, unitsource, targetUnit, 2);

        return true;
    }

}
