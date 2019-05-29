package com.robertx22.uncommon.testing.tests;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequested;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;

public class CheckAllGearsHaveAffix {

    public static void check() {

        for (GearItemSlot slot : GearTypes.All.values()) {

            Prefix prefix = Prefixes.INSTANCE.random(new AffixRequested(slot));
            Suffix suffix = Suffixes.INSTANCE.random(new AffixRequested(slot));

            if (prefix == null) {
                try {
                    throw new Exception(slot.GUID() + " has no possible prefix!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (suffix == null) {
                try {
                    throw new Exception(slot.GUID() + " has no possible suffix!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
