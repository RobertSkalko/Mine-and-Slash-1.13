package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.MapBlueprint;
import com.robertx22.loot.create.MapGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MapLootGen extends BaseLootGen {
    MapBlueprint blueprint;

    public MapLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                      EntityPlayer killer) {
        super(mob, player, victim, killer);

        blueprint = new MapBlueprint(mob.getLevel(), this.tier);

    }

    public MapLootGen(World theworld, float multi, int level) {
        super(theworld, multi);

        blueprint = new MapBlueprint(level, tier);

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
