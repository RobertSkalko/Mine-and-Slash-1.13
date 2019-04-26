package com.robertx22.uncommon.utilityclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.robertx22.mmorpg.Ref;

import net.minecraftforge.fml.loading.FMLPaths;

public class SerializationUtils {
    public static final String CONFIG_PATH = FMLPaths.CONFIGDIR.get().toAbsolutePath().toString() + "/" + Ref.MODID
	    + "/";

    public static void makeFileAndDirAndWrite(String path, String filename, String text) {

	if (path.contains(CONFIG_PATH) == false) {
	    path = CONFIG_PATH + path;
	}

	String combined = path + "/" + filename;

	try {
	    new File(path).mkdirs();

	    if (new File(combined).exists() == false) {

		new File(combined).createNewFile();
		FileWriter fileWriter;
		fileWriter = new FileWriter(combined);
		fileWriter.write(text);
		fileWriter.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
