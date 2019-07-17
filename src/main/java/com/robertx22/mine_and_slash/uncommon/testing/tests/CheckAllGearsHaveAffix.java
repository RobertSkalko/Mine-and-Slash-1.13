package com.robertx22.mine_and_slash.uncommon.testing.tests;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.GearTypes;
import com.robertx22.mine_and_slash.db_lists.Prefixes;
import com.robertx22.mine_and_slash.db_lists.Suffixes;

public class CheckAllGearsHaveAffix {

    public static void check() {

        for (GearItemSlot slot : GearTypes.All.values()) {

            Prefix prefix = Prefixes.INSTANCE.random(new GearRequestedFor(slot));
            Suffix suffix = Suffixes.INSTANCE.random(new GearRequestedFor(slot));

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
