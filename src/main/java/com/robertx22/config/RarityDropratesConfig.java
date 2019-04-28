package com.robertx22.config;

public class RarityDropratesConfig {
    public static class RarityWeights {

        public RarityWeight ITEMS = new RarityWeight();
        public RarityWeight RUNED_ITEMS = new RarityWeight();
        public RarityWeight RUNES = RarityWeight.Runes();
        public RarityWeight MOBS = new RarityWeight();
        public RarityWeight MAPS = new RarityWeight();
        public RarityWeight CURRENCY = new RarityWeight();
        public RarityWeight SPELLS = new RarityWeight();

    }

    public static class RarityWeight {

        public static RarityWeight Runes() {
            RarityWeight r = new RarityWeight();
            r.COMMON_WEIGHT = 12000;
            r.UNCOMMON_WEIGHT = 7000;
            r.RARE_WEIGHT = 4000;
            r.LEGENDARY_WEIGHT = 1500;
            r.MYTHICAL_WEIGHT = 500;
            return r;

        }

        public int COMMON_WEIGHT = 25000;

        public int UNCOMMON_WEIGHT = 20000;

        public int RARE_WEIGHT = 5000;

        public int EPIC_WEIGHT = 3000;

        public int LEGENDARY_WEIGHT = 1250;

        public int MYTHICAL_WEIGHT = 300;

    }
}
