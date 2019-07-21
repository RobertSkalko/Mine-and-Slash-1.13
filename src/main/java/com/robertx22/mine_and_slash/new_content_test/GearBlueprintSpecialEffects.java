package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana.BaseMajorArcana;
import com.robertx22.mine_and_slash.db_lists.initializers.Prefixes;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrefixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

public enum GearBlueprintSpecialEffects {

    ALWAYS_SET("always_set") {
        @Override
        public void modify(GearItemData gear) {
            gear.set = new SetData();
            gear.set = gear.set.generate(gear);

        }
    },
    ALWAYS_CHAOS_STATS("always_chaos_stats") {
        @Override
        public void modify(GearItemData gear) {
            gear.chaosStats = new ChaosStatsData();
            gear.chaosStats.RerollFully(gear);

        }
    },
    MYTHIC_AFFIXES("mythic_affixes") {
        @Override
        public void modify(GearItemData gear) {

            gear.prefix = new PrefixData();

            List<Prefix> prefixes = SlashRegistry.Prefixes()
                    .getFiltered(SlashRegistry.PREDICATES.ofRarityOrHigher(5));

            Prefix prefix = RandomUtils.weightedRandom(Prefixes.INSTANCE.allThatMeetRequirement(prefixes, new GearRequestedFor(gear)));

            gear.prefix.create(gear, prefix);

        }
    },

    MAJOR_ARCANA_CHAOS_STAT("major_arcana_chaos") {
        @Override
        public void modify(GearItemData gear) {

            StatMod mod = RandomUtils.weightedRandom(SlashRegistry.StatMods()
                    .getList()
                    .stream()
                    .filter(x -> x.GetBaseStat() instanceof BaseMajorArcana)
                    .collect(Collectors.toList()));

            gear.chaosStats = new ChaosStatsData();
            gear.chaosStats.create(gear, mod);

        }
    };

    GearBlueprintSpecialEffects(String guid) {
        this.guid = guid;
    }

    public String guid;

    public abstract void modify(GearItemData gear);
}
