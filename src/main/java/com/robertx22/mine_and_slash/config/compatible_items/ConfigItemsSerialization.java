package com.robertx22.mine_and_slash.config.compatible_items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.initializers.UniqueItems;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;
import net.minecraft.item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigItemsSerialization implements ISerializedConfig {

    public static ConfigItemsSerialization INSTANCE = new ConfigItemsSerialization();

    @Override
    public void generateIfEmpty() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(GenCompItemJsons.generate());

        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    @Override
    public String fileName() {
        return "CompatibleItems.txt";
    }

    @Override
    public String folder() {
        return SerializationUtils.CONFIG_PATH + "compatible_items/";
    }

    @Override
    public void load() {

        JsonReader reader;
        try {

            for (File file : Objects.requireNonNull(new File(folder()).listFiles())) {

                reader = new JsonReader(new FileReader(file.getPath()));
                ConfigItems.INSTANCE.addAll(new Gson().fromJson(reader, ConfigItems.class));

            }
            System.out.println("Items added to config: " + ConfigItems.INSTANCE.map.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        ConfigItems.INSTANCE.validateAll();

    }

    // needs to be done after unique items are actually registered.
    public void generateConfigTutorials() {
        genListOfUniqueItems();
        genListOfItemTypes();
    }

    private void genListOfUniqueItems() {

        List<String> list = new ArrayList();

        for (Item item : UniqueItems.ITEMS.values()) {
            IUnique uniq = (IUnique) item;
            list.add(uniq.GUID());
        }

        String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW THE GUIDS/IDS OF ALL UNIQUE ITEMS\n" + String
                .join("\n", list);

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "UniqueItemGUIDS-TUTORIAL.txt", text);

    }

    private void genListOfItemTypes() {

        List<String> list = new ArrayList();

        for (GearItemSlot item : SlashRegistry.GearTypes().getAll().values()) {
            list.add(item.GUID());
        }

        String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW WHAT ITEM TYPES THERE ARE\n" + String
                .join("\n", list);

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "GearTypeGUIDS-TUTORIAL.txt", text);

    }

}
