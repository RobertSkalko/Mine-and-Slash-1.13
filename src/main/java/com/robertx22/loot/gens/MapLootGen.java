package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.MapAffixes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.WorldProviders;
import com.robertx22.items.misc.ItemMap;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.MapBlueprint;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MapLootGen extends BaseLootGen {

    public MapLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.MAP_DROPRATE.get().floatValue();
    }

    @Override
    public boolean hasLevelDistancePunishment() {
        return false;
    }

    @Override
    public ItemStack generateOne() {

        MapBlueprint blueprint = new MapBlueprint(info.level, info.tier);

        if (blueprint.level >= ModConfig.INSTANCE.Server.MAPS_DROP_AFTER_LEVEL.get()) {
            return Create(blueprint);
        } else {
            return ItemStack.EMPTY;
        }

    }

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

        data.worldGeneratorName = WorldProviders.INSTANCE.random().GUID();

        Map.Save(stack, data);

        return stack;

    }

    private static MapItemData genAffixes(MapItemData map, MapRarity rarity) {

        int amount = RandomUtils.RandomRange(rarity.AffixAmount().Min, rarity.AffixAmount().Max);

        List<String> affixes = new ArrayList<String>();

        for (int i = 0; i < amount; i++) {

            BaseMapAffix affix = RandomUtils.weightedRandom(MapAffixes.All.values());

            while (affixes.contains(affix.GUID()) || affix.isBeneficial()) { // can't have moba affixes arndom anymore. only on dimension types
                affix = RandomUtils.weightedRandom(MapAffixes.All.values());
            }

            int percent = RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);

            map.affixes.add(new MapAffixData(affix, percent));
            affixes.add(affix.GUID());

        }

        return map;
    }

}
