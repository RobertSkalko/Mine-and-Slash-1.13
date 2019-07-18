package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.items.runes.*;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.HashMap;

public class Runes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        HashMap<String, BaseRuneItem> All = new HashMap<String, BaseRuneItem>() {
            {
                {
                    put(new CenItem(0).name(), new CenItem(0));
                    put(new MosItem(0).name(), new MosItem(0));
                    put(new ItaItem(0).name(), new ItaItem(0));
                    put(new BerItem(0).name(), new BerItem(0));
                    put(new DosItem(0).name(), new DosItem(0));
                    put(new GohItem(0).name(), new GohItem(0));
                    put(new RahItem(0).name(), new RahItem(0));
                    put(new VohItem(0).name(), new VohItem(0));
                    put(new XahItem(0).name(), new XahItem(0));
                    put(new AnoItem(0).name(), new AnoItem(0));

                }

            }
        };

        All.values()
                .forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.RUNE)
                        .register(x));

    }
}
