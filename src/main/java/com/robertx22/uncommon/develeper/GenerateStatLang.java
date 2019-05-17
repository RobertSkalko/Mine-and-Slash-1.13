package com.robertx22.uncommon.develeper;

import com.robertx22.database.stats.Stat;
import com.robertx22.db_lists.Stats;
import net.minecraft.client.resources.I18n;

public class GenerateStatLang {

    public static void Gen() {

        String text = "";

        for (Stat stat : Stats.All.values()) {
            text += "\"mmorpg.stat." + stat.GUID()
                    .toLowerCase()
                    .replaceAll(" ", "_") + "\"" + ": " + "\"" + I18n.format(stat.locName()
                    .getString()) + "\"" + ",\n";
        }

        System.out.println(text);

    }
}
