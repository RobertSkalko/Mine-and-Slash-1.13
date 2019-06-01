package com.robertx22.uncommon.effectdatas.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum WeaponTypes {
    None, Axe, Hammer, Bow, Sword, Staff;

    public static List<WeaponTypes> getAll() {

        return Arrays.stream(WeaponTypes.values())
                .filter(x -> x != WeaponTypes.None)
                .collect(Collectors.toList());

    }
}
