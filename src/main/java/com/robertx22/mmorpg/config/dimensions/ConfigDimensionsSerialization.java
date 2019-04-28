package com.robertx22.mmorpg.config.dimensions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.mmorpg.config.ISerializedConfig;
import com.robertx22.mmorpg.config.non_mine_items.ConfigItems;
import com.robertx22.uncommon.utilityclasses.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigDimensionsSerialization implements ISerializedConfig {

    public static final ConfigDimensionsSerialization INSTANCE = new ConfigDimensionsSerialization();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "DimensionConfigs.txt";
    }

    public void generateIfEmpty() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new DimensionsContainer());

        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    public void load() {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(folder()));

            DimensionsContainer.INSTANCE = new Gson().fromJson(reader, DimensionsContainer.class);

            System.out.println("Dimensions added to config: " + ConfigItems.INSTANCE.map.size());
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

}
