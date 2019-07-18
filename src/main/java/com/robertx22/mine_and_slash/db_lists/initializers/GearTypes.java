package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.*;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;

import java.util.HashMap;

public class GearTypes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        HashMap<String, GearItemSlot> All = new HashMap<String, GearItemSlot>() {
            {
                {
                    put(new Boots().GUID(), new Boots());
                    put(new Pants().GUID(), new Pants());
                    put(new Helmet().GUID(), new Helmet());
                    put(new Chest().GUID(), new Chest());
                    put(new Ring().GUID(), new Ring());
                    put(new Sword().GUID(), new Sword());
                    put(new Necklace().GUID(), new Necklace());
                    put(new Bracelet().GUID(), new Bracelet());
                    put(new Bow().GUID(), new Bow());
                    put(new Charm().GUID(), new Charm());
                    put(new Hammer().GUID(), new Hammer());
                    put(new Staff().GUID(), new Staff());
                    put(new Axe().GUID(), new Axe());
                    put(new Shield().GUID(), new Shield());
                    put(new Torch().GUID(), new Torch());

                }

            }
        };

        All.values().

                forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.GEAR_TYPE).

                        register(x));

    }
}
