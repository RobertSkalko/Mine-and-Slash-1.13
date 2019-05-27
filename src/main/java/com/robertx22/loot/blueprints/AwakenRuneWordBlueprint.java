package com.robertx22.loot.blueprints;

import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

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
