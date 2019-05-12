package com.robertx22.api.msg_types;

import com.robertx22.database.affixes.BaseAffix;
import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;

public class AffixMSG implements MineAndSlashMSG {

    public enum GearFamilly {
        Armor, Weapon, Jewerly, All
    }

    public BaseAffix affix;
    public GearFamilly family;

    /*family means which gear group will the affix go to. Jewerly means it will only spawn on rings, charms etc.

     */
    public AffixMSG(BaseAffix affix, GearFamilly family) {
        this.affix = affix;
        this.family = family;

    }

    @Override
    public final void register() {

        if (affix instanceof Prefix) {

            if (family == GearFamilly.All) {
                Prefixes.Armor.add((Prefix) affix);
                Prefixes.Jewerly.add((Prefix) affix);
                Prefixes.Weapon.add((Prefix) affix);

            } else if (family == GearFamilly.Armor) {
                Prefixes.Armor.add((Prefix) affix);
            } else if (family == GearFamilly.Jewerly) {
                Prefixes.Jewerly.add((Prefix) affix);
            } else {
                Prefixes.Weapon.add((Prefix) affix);
            }
            Prefixes.all.put(affix.GUID(), (Prefix) affix);

        } else {

            if (family == GearFamilly.All) {
                Suffixes.Armor.add((Suffix) affix);
                Suffixes.Jewerly.add((Suffix) affix);
                Suffixes.Weapon.add((Suffix) affix);

            } else if (family == GearFamilly.Armor) {
                Suffixes.Armor.add((Suffix) affix);
            } else if (family == GearFamilly.Jewerly) {
                Suffixes.Jewerly.add((Suffix) affix);
            } else {
                Suffixes.Weapon.add((Suffix) affix);
            }
            Suffixes.all.put(affix.GUID(), (Suffix) affix);
        }

    }

}
