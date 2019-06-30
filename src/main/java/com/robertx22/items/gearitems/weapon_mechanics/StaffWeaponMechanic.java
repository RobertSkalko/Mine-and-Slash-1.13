package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.localization.Styles;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class StaffWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Double Damage");
    }

    @Override
    public float GetEnergyCost() {
        return 9;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Staff;
    }

    @Override
    public boolean Attack(LivingEntity source, LivingEntity target,
                          UnitData unitsource, UnitData targetUnit) {

        super.multiplyDamage(source, target, unitsource, targetUnit, 2);

        return true;
    }
}
