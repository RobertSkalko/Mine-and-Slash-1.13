package com.robertx22.uncommon.develeper;

import com.robertx22.Words;
import com.robertx22.db_lists.*;
import com.robertx22.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class CreateLangFile {

    public static void create() {

        String json = "";

        for (Map.Entry<String, List<IAutoLocName>> entry : getMap().entrySet()) {

            json += comment(entry.getKey());
            for (IAutoLocName iauto : entry.getValue()) {
                if (iauto.locNameForLangFile().isEmpty() == false) {

                    if (iauto.locNameForLangFile().contains("\"")) {
                        try {
                            throw new Exception(iauto.locNameForLangFile() + " contains double \"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    json += "\t" + "\"" + iauto.locNameLangFileGUID(iauto.formattedGUID()) + "\": \"" + iauto
                            .locNameForLangFile() + "\",\n";
                }
            }
            json += comment(entry.getKey());

        }
        for (Map.Entry<String, List<IAutoLocDesc>> entry : getDescMap().entrySet()) {

            json += comment(entry.getKey());
            for (IAutoLocDesc iauto : entry.getValue()) {
                if (iauto.locDescForLangFile().isEmpty() == false) {

                    if (iauto.locDescForLangFile().contains("\"")) {
                        try {
                            throw new Exception(iauto.locDescForLangFile() + " contains double \"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    json += "\t" + "\"" + iauto.locDescLangFileGUID(iauto.formattedGUID()) + "\": \"" + iauto
                            .locDescForLangFile() + "\",\n";
                }
            }
            json += comment(entry.getKey());

        }

        json += "";

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
        return FMLPaths.GAMEDIR.get().toString() + "/autolang.txt";
    }

    private static String comment(String str) {
        return "\n" + "\"_comment\": \"" + " [CATEGORY]: " + str + "\",\n" + "\n";
    }

    public static HashMap<String, List<IAutoLocName>> getMap() {
        HashMap<String, List<IAutoLocName>> list = new HashMap<>();
        list.put("ITEM SETS", new ArrayList<>(Sets.All.values()));
        list.put("RUNEWORDS", new ArrayList<>(RuneWords.All.values()));
        list.put("PREFIXES", new ArrayList<>(Prefixes.all.values()));
        list.put("SUFFIXES", new ArrayList<>(Suffixes.all.values()));
        list.put("STATS", new ArrayList<>(Stats.All.values()));
        list.put("GEAR SLOT TYPES", new ArrayList<>(GearTypes.All.values()));
        list.put("UNIQUE ITEM NAMES", new ArrayList<>(UniqueItems.getAll()));
        list.put("WORLD TYPES", new ArrayList<>(WorldProviders.All.values()));
        list.put("WORDS", Arrays.asList(Words.values()));

        return list;

    }

    public static HashMap<String, List<IAutoLocDesc>> getDescMap() {
        HashMap<String, List<IAutoLocDesc>> list = new HashMap<>();
        list.put("STAT DESCRIPTIONS", new ArrayList<>(Stats.All.values()));
        list.put("UNIQUE ITEM DESCRIPTIONS", new ArrayList<>(UniqueItems.getAll()));

        return list;

    }
}
