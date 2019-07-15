package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityCap;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.localization.Styles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class BowWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Quadruple Damage");
    }

    @Override
    public float GetEnergyCost() {
        return 18;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Bow;
    }

    @Override
    public boolean Attack(LivingEntity source, LivingEntity target,
                          EntityCap.UnitData unitsource, EntityCap.UnitData targetUnit) {

        super.multiplyDamage(source, target, unitsource, targetUnit, 4);

        return true;
    }
}
