package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootInfo;
import com.robertx22.loot.blueprints.MapBlueprint;
import com.robertx22.loot.create.MapGen;
import net.minecraft.item.ItemStack;

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
            return MapGen.Create(blueprint);
        } else {
            return ItemStack.EMPTY;
        }

    }

}
