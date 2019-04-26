package com.robertx22.mmorpg.config.non_mine_items;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.items.unique_items.IUnique;
import com.robertx22.mmorpg.config.ISerializedConfig;
import com.robertx22.uncommon.utilityclasses.SerializationUtils;

import net.minecraft.item.Item;

public class ConfigItemsSerialization implements ISerializedConfig {

    public static final ConfigItemsSerialization INSTANCE = new ConfigItemsSerialization();

    public void generateIfEmpty() {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String json = gson.toJson(new ConfigItems());

	SerializationUtils.makeFileAndDirAndWrite(folder(), fileName(), json);

	generateConfigTutorials();

    }

    public String fileName() {
	return "CompatibleItems.txt";
    }

    @Override
    public String folder() {
	return SerializationUtils.CONFIG_PATH;
    }

    public void load() {

	JsonReader reader;
	try {
	    reader = new JsonReader(new FileReader(this.getPath()));

	    ConfigItems.INSTANCE = new Gson().fromJson(reader, ConfigItems.class);

	    System.out.println("Items added to config: " + ConfigItems.INSTANCE.map.size());
	} catch (FileNotFoundException e) {

	    e.printStackTrace();
	}
    }

    private void generateConfigTutorials() {
	genListOfUniqueItems();
	genListOfItemTypes();
    }

    private void genListOfUniqueItems() {

	List<String> list = new ArrayList();

	for (Item item : IUnique.ITEMS.values()) {
	    IUnique uniq = (IUnique) item;
	    list.add(uniq.GUID());
	}

	String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW THE GUIDS/IDS OF ALL UNIQUE ITEMS\n"
		+ String.join("\n", list);

	SerializationUtils.makeFileAndDirAndWrite("tutorials", "UniqueItemGUIDS-TUTORIAL.txt", text);

    }

    private void genListOfItemTypes() {

	List<String> list = new ArrayList();

	for (GearItemSlot item : GearTypes.All.values()) {
	    list.add(item.GUID());
	}

	String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW WHAT ITEM TYPES THERE ARE\n"
		+ String.join("\n", list);

	SerializationUtils.makeFileAndDirAndWrite("tutorials", "GearTypeGUIDS-TUTORIAL.txt", text);

    }

}
