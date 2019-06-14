package com.robertx22.uncommon.utilityclasses;

import net.minecraftforge.fml.loading.FMLPaths;

public class DirUtils {

    public static String modDir() {
        return FMLPaths.GAMEDIR.get().toString().replace("run", "src");
    }

    public static String langFolderDir() {
        return modDir() + "/main/resources/assets/mmorpg/lang/";
    }

    public static String langFilePath() {
        return langFolderDir() + "en_us.json";
    }

    public static String curiosItemTagsPath() {
        return modDir() + "/main/resources/data/curios/tags/items/";
    }
}
