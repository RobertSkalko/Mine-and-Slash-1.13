package com.robertx22.config.compatible_items;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.UniqueItems;
import net.minecraft.item.Item;

public class GenCompItemJsons {

    public static ConfigItems generate() {

        ConfigItems items = new ConfigItems();

        items.map.clear();

        try {
            for (IUnique uniq : UniqueItems.getAll()) {
                ConfigItem item = new ConfigItem().setAlwaysUnique()
                        .setUniqueId(uniq)
                        .setSalvagable(true)
                        .setType(uniq.slot());
                Item theitem = (Item) uniq;
                item.dropsAsLoot = false;

                items.add(theitem.getRegistryName().toString(), item);
            }

            for (GearItemSlot slot : GearTypes.All.values())
                for (int i = 0; i < 5; i++) {
                    Item item = slot.GetItemForRarity(i);
                    ConfigItem config = new ConfigItem().setGenerationWeights(1000, 200, 0)
                            .setMaxRarity(i)
                            .setMinRarity(i)
                            .setSalvagable(true)
                            .setType(slot);
                    config.dropsAsLoot = false;

                    items.add(item.getRegistryName().toString(), config);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

}
