package com.robertx22.uncommon.develeper;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CreateLangFileUtils {

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

    public static String filepath() {
        return FMLPaths.GAMEDIR.get().toString() + "/autolang.txt";
    }

    public static String comment(String str) {
        return "\n" + "\"_comment\": \"" + " [CATEGORY]: " + str + "\",\n" + "\n";
    }

    public static boolean matches(ResourceLocation loc) {
        if (loc == null || loc.getNamespace().equals(Ref.MODID) == false) {
            return false;
        }
        return true;
    }

    public static List<IAutoLocName> getNamesFromRegistries() {

        List<IAutoLocName> list = new ArrayList<>();

        for (Item item : ForgeRegistries.ITEMS) {
            if (matches(item.getRegistryName()) && item instanceof IAutoLocName) {
                list.add((IAutoLocName) item);
            }
        }
        for (Block item : ForgeRegistries.BLOCKS) {
            if (matches(item.getRegistryName()) && item instanceof IAutoLocName) {
                list.add((IAutoLocName) item);
            }
        }
        return list;

    }

    public static List<IAutoLocDesc> getDescsFromRegistries() {

        List<IAutoLocDesc> list = new ArrayList<>();

        for (Item item : ForgeRegistries.ITEMS) {
            if (matches(item.getRegistryName()) && item instanceof IAutoLocDesc) {
                list.add((IAutoLocDesc) item);
            }
        }
        for (Block item : ForgeRegistries.BLOCKS) {
            if (matches(item.getRegistryName()) && item instanceof IAutoLocDesc) {
                list.add((IAutoLocDesc) item);
            }
        }
        return list;

    }

    public static List<IAutoLocMultiLore> getMultiLoresFromRegistries() {

        List<IAutoLocMultiLore> list = new ArrayList<>();

        for (Item item : ForgeRegistries.ITEMS) {
            if (matches(item.getRegistryName()) && item instanceof IAutoLocMultiLore) {
                list.add((IAutoLocMultiLore) item);
            }
        }
        for (Block item : ForgeRegistries.BLOCKS) {
            if (matches(item.getRegistryName()) && item instanceof IAutoLocMultiLore) {
                list.add((IAutoLocMultiLore) item);
            }
        }
        return list;

    }

    public static void sortName(List<IAutoLocName> list) {
        if (list != null && list.size() > 1) {
            try {
                Collections.sort(list, Comparator.comparing(x -> x.locNameLangFileGUID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sortDesc(List<IAutoLocDesc> list) {
        if (list != null && list.size() > 1) {
            try {
                Collections.sort(list, Comparator.comparing(x -> x.locDescLangFileGUID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sortLores(List<IAutoLocMultiLore> list) {
        if (list != null && list.size() > 1) {
            try {
                Collections.sort(list, Comparator.comparing(x -> x.formattedGUID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
