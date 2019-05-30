package com.robertx22.uncommon.develeper;

import com.robertx22.IAutoLocName;
import com.robertx22.db_lists.Sets;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateLangFile {

    public static void create() {

        String json = "{\n";

        for (Map.Entry<String, List<IAutoLocName>> entry : getMap().entrySet()) {

            json += "\n";
            json += comment(entry.getKey());
            json += "\n";

            for (IAutoLocName iauto : entry.getValue()) {
                json += "\t" + "\"" + iauto.locName()
                        .getString() + "\": \"" + iauto.locNameForLangFile() + "\",\n";
            }
            json += "\n";
            json += comment(entry.getKey());
            json += "\n";

        }

        json += "\n}";

        json = replaceLast(json, ",", ""); // removes last , or else json wont work

        File file = new File(filepath());

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Wrote [LANG] file to " + filepath());

    }

    public static String replaceLast(String string, String toReplace,
                                     String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos) + replacement + string.substring(pos + toReplace
                    .length(), string.length());
        } else {
            return string;
        }
    }

    private static String filepath() {
        return FMLPaths.GAMEDIR.get().toString() + "/autolang.json";
    }

    private static String comment(String str) {
        return "\"_comment\": \"" + " [CATEGORY]: " + str + "\",\n";
    }

    public static HashMap<String, List<IAutoLocName>> getMap() {
        HashMap<String, List<IAutoLocName>> list = new HashMap<>();
        list.put("ITEM SETS", new ArrayList<>(Sets.All.values()));

        return list;

    }

}
