package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.initializers.RuneWords;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class AwakenRuneWordBlueprint extends ItemBlueprint {

    public String word = "";

    public AwakenRuneWordBlueprint() {
        super(1);
    }

    public RuneWord getWord() {

        if (RuneWords.All.containsKey(word) == false) {

            RuneWord random = RandomUtils.weightedRandom(RuneWords.All.values());

            word = random.GUID();

        }

        return RuneWords.All.get(word);

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Items;
    }
}
