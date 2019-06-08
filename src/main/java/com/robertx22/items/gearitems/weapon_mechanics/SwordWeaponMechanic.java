package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SwordWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Normal Attack");
    }

    @Override
    public float GetEnergyCost() {
        return 4;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Sword;
    }

}
