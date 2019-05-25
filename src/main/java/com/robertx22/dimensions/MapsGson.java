package com.robertx22.dimensions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.config.base.ISerializedConfig;
import com.robertx22.config.compatible_items.ConfigItems;
import com.robertx22.uncommon.utilityclasses.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MapsGson implements ISerializedConfig {

    public static MapsGson INSTANCE = new MapsGson();

    public String folder() {
        return SerializationUtils.CONFIG_PATH;
    }

    public String fileName() {
        return "mmorpg_maps_storage.txt";
    }

    public void generateIfEmpty() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new MapsGson());

        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    @Override
    public void load() {

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(this.getPath()));

            INSTANCE = new Gson().fromJson(reader, MapsGson.class);

            System.out.println("Dimensions added to config: " + ConfigItems.INSTANCE.map.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    public void save(MapsGson maps) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(maps);
        SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

    }

    public List<MapGson> list = new ArrayList<>();

    public static class MapGson {

        public String reg = "";
        public String iwp = "";
    }

}
