package com.robertx22.loot.create;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.MapAffixes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.misc.ItemMap;
import com.robertx22.loot.blueprints.MapBlueprint;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MapGen {
    public static ItemStack Create(MapBlueprint blueprint) {

        blueprint.level = blueprint.level + 1; // temp hotfix for too many low level maps

        MapItemData data = new MapItemData();
        MapRarity rarity = Rarities.Maps.get(blueprint.GetRarity());
        ItemStack stack = new ItemStack(ItemMap.Items.get(rarity.Rank()));

        data.rarity = rarity.Rank();

        data.minutes = RandomUtils.RandomRange(15, 60);

        data.tier = blueprint.getTier();

        data.isPermaDeath = blueprint.getIsPermaDeath();

        data.level = blueprint.GetLevel();

        data = genAffixes(data, rarity);

        Map.Save(stack, data);

        return stack;

    }

    private static MapItemData genAffixes(MapItemData map, MapRarity rarity) {

        int amount = RandomUtils.RandomRange(rarity.AffixAmount().Min, rarity.AffixAmount().Max);

        List<String> affixes = new ArrayList<String>();

        for (int i = 0; i < amount; i++) {

            BaseMapAffix affix = (BaseMapAffix) RandomUtils.WeightedRandom(ListUtils.CollectionToList(MapAffixes.All
                    .values()));

            while (affixes.contains(affix.GUID())) {
                affix = (BaseMapAffix) RandomUtils.WeightedRandom(ListUtils.CollectionToList(MapAffixes.All
                        .values()));
            }

            int percent = RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);

            map.affixes.add(new MapAffixData(affix, percent));
            affixes.add(affix.GUID());

        }

        return map;
    }

}
