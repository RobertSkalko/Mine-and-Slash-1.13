package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.MapBlueprint;
import com.robertx22.loot.create.MapGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MapLootGen extends BaseLootGen {
    MapBlueprint blueprint;

    public MapLootGen(UnitData mob, UnitData player, IWorldData world,
                      EntityLivingBase victim) {
        super(mob, player, world, victim);

        blueprint = new MapBlueprint(mob.getLevel(), world.getTier(victim.world));

    }

    public MapLootGen(World theworld, float multi, IWorldData world, int level) {
        super(theworld, multi, world);

        blueprint = new MapBlueprint(level, world.getTier(theworld));

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
        if (blueprint.level >= ModConfig.INSTANCE.Server.MAPS_DROP_AFTER_LEVEL.get()) {
            return MapGen.Create(blueprint);
        } else {
            return ItemStack.EMPTY;
        }

    }

}
