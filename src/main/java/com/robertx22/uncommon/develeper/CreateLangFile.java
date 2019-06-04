package com.robertx22.uncommon.develeper;

import com.robertx22.Words;
import com.robertx22.db_lists.*;
import com.robertx22.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.uncommon.interfaces.IAutoLocName;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class CreateLangFile {

    public static void create() {

        String json = "";

        for (Map.Entry<String, List<IAutoLocName>> entry : getMap().entrySet()) {

            json += CreateLangFileUtils.comment(entry.getKey());
            for (IAutoLocName iauto : entry.getValue()) {
                if (iauto.locNameForLangFile().isEmpty() == false) {

                    if (iauto.locNameForLangFile().contains("\"")) {
                        try {
                            throw new Exception(iauto.locNameForLangFile() + " contains double \"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    json += "\t" + "\"" + iauto.formattedLocNameLangFileGUID() + "\": \"" + iauto
                            .locNameForLangFile() + "\",\n";
                }
            }
            json += CreateLangFileUtils.comment(entry.getKey());

        }
        for (Map.Entry<String, List<IAutoLocDesc>> entry : getDescMap().entrySet()) {

            json += CreateLangFileUtils.comment(entry.getKey());
            for (IAutoLocDesc iauto : entry.getValue()) {
                if (iauto.locDescForLangFile().isEmpty() == false) {

                    if (iauto.locDescForLangFile().contains("\"")) {
                        try {
                            throw new Exception(iauto.locDescForLangFile() + " contains double \"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    json += "\t" + "\"" + iauto.formattedLocDescLangFileGUID() + "\": \"" + iauto
                            .locDescForLangFile() + "\",\n";
                }
            }
            json += CreateLangFileUtils.comment(entry.getKey());

        }

        json += "";

        json = CreateLangFileUtils.replaceLast(json, ",", ""); // removes last , or else json wont work

        File file = new File(CreateLangFileUtils.filepath());

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Wrote [LANG] file to " + CreateLangFileUtils.filepath());

    }

    public static HashMap<String, List<IAutoLocName>> getMap() {
        List<IAutoLocName> list = CreateLangFileUtils.getNamesFromRegistries();

        list.addAll(Sets.All.values());
        list.addAll(RuneWords.All.values());
        list.addAll(Prefixes.all.values());
        list.addAll(Suffixes.all.values());
        list.addAll(Stats.All.values());
        list.addAll(GearTypes.All.values());
        list.addAll(WorldProviders.All.values());
        list.addAll(Arrays.asList(Words.values()));
        list.addAll(Rarities.Items.rarities());

        HashMap<IAutoLocName.Group, List<IAutoLocName>> map = new HashMap<>();

        for (IAutoLocName.Group group : IAutoLocName.Group.values()) {
            map.put(group, list.stream()
                    .filter(x -> x.locNameGroup().equals(group))
                    .collect(Collectors.toList()));
        }

        HashMap<String, List<IAutoLocName>> sortedMap = new HashMap<>();
        for (Map.Entry<IAutoLocName.Group, List<IAutoLocName>> entry : map.entrySet()) {
            List<IAutoLocName> sortedlist = new ArrayList<>(entry.getValue());
            CreateLangFileUtils.sortName(sortedlist);
            if (sortedlist.size() > 0) {
                sortedMap.put(entry.getValue().get(0).getGroupName(), sortedlist);
            }
        }

        return sortedMap;

    }

    public static HashMap<String, List<IAutoLocDesc>> getDescMap() {
        List<IAutoLocDesc> list = CreateLangFileUtils.getDescsFromRegistries();
        list.addAll(Stats.All.values());

        HashMap<IAutoLocDesc.Group, List<IAutoLocDesc>> map = new HashMap<>();

        for (IAutoLocDesc.Group group : IAutoLocDesc.Group.values()) {
            map.put(group, list.stream()
                    .filter(x -> x.locDescGroup().equals(group))
                    .collect(Collectors.toList()));
        }

        HashMap<String, List<IAutoLocDesc>> sortedMap = new HashMap<>();
        for (Map.Entry<IAutoLocDesc.Group, List<IAutoLocDesc>> entry : map.entrySet()) {
            List<IAutoLocDesc> sortedlist = new ArrayList<>(entry.getValue());
            CreateLangFileUtils.sortDesc(sortedlist);
            if (sortedlist.size() > 0) {
                sortedMap.put(entry.getValue().get(0).getGroupName(), sortedlist);
            }
        }

        return sortedMap;

    }

}
