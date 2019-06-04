package com.robertx22.uncommon.develeper;

import com.robertx22.Words;
import com.robertx22.database.IGUID;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.*;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IGearItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

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
        list.put("RARITIES", new ArrayList<>(Rarities.Items.rarities()));

        List<IAutoLocName> gearItems = new ArrayList<>();
        List<IAutoLocName> runes = new ArrayList<>();
        List<IAutoLocName> misc = new ArrayList<>();

        for (Item item : ForgeRegistries.ITEMS) {

            if (item.getRegistryName() == null || item.getRegistryName()
                    .getNamespace()
                    .equals(Ref.MODID) == false) {
                continue;
            }

            if (item instanceof IAutoLocName && item instanceof IUnique == false) {
                if (item instanceof BaseRuneItem) {
                    runes.add((IAutoLocName) item);
                } else if (item instanceof IGearItem) {
                    gearItems.add((IAutoLocName) item);
                } else {
                    misc.add((IAutoLocName) item);
                }
            }
        }
        list.put("GEAR ITEMS", gearItems);
        list.put("RUNES", runes);
        list.put("MISC ITEMS", misc);

        HashMap<String, List<IAutoLocName>> sortedMap = new HashMap<>();
        for (Map.Entry<String, List<IAutoLocName>> entry : list.entrySet()) {
            List<IAutoLocName> sortedlist = new ArrayList<>(entry.getValue());
            sortName(sortedlist);
            sortedMap.put(entry.getKey(), sortedlist);
        }

        return sortedMap;

    }

    public static HashMap<String, List<IAutoLocDesc>> getDescMap() {
        HashMap<String, List<IAutoLocDesc>> list = new HashMap<>();
        list.put("STAT DESCRIPTIONS", new ArrayList<>(Stats.All.values()));
        list.put("UNIQUE ITEM DESCRIPTIONS", new ArrayList<>(UniqueItems.getAll()));

        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof IAutoLocDesc && item instanceof IUnique == false) {

            }
        }

        HashMap<String, List<IAutoLocDesc>> sortedMap = new HashMap<>();
        for (Map.Entry<String, List<IAutoLocDesc>> entry : list.entrySet()) {
            List<IAutoLocDesc> sortedlist = new ArrayList<>(entry.getValue());
            sortDesc(sortedlist);
            sortedMap.put(entry.getKey(), sortedlist);
        }

        return sortedMap;

    }

    private static void sortName(List<IAutoLocName> list) {
        if (list != null && list.size() > 1) {
            try {
                Collections.sort(list, Comparator.comparing(IGUID::GUID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sortDesc(List<IAutoLocDesc> list) {
        if (list != null && list.size() > 1) {
            try {
                Collections.sort(list, Comparator.comparing(IGUID::GUID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
