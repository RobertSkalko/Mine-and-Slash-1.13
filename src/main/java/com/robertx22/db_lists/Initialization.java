package com.robertx22.db_lists;

public class Initialization {

    public static void initAllDatabases() {

        Stats.init(); // STATS MUST BE INIT FIRST CUS STATMODS ARE DERIVED FROM STATS, or should be at least

        StatMods.init();
        Prefixes.init();

        Suffixes.init();
        Sets.init();

    }

}
