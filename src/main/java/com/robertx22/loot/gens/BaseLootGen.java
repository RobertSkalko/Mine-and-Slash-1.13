package com.robertx22.loot.gens;

import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.loot.LootUtils;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLootGen {

    public abstract float BaseChance();

    public boolean hasLevelDistancePunishment() {
        return true;
    }

    protected abstract ItemStack generateOne();

    public List<ItemStack> generate() {

        List<ItemStack> list = new ArrayList<ItemStack>();

        for (int i = 0; i < amount; i++) {
            try {
                list.add(generateOne());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int amount = 0;
    public IWorldData world;
    public int world_tier;

    public BaseLootGen(World theworld, float multi, IWorldData world) {

        this.world = world;
        this.world_tier = world.getTier(theworld);

        float chance = BaseChance();

        chance *= multi;

        amount = LootUtils.WhileRoll(chance);

    }

    public BaseLootGen(UnitData mob, UnitData player, IWorldData world,
                       EntityLivingBase victim) {

        this.world = world;
        this.world_tier = world.getTier(victim.world);

        float chance = BaseChance();

        float entity_type_multi = EntityTypeUtils.getLootMulti(victim);

        chance *= entity_type_multi;

        chance *= DimensionsContainer.INSTANCE.getConfig(victim.world).DROP_MULTIPLIER;

        if (hasLevelDistancePunishment()) {
            chance = LootUtils.ApplyLevelDistancePunishment(mob, player, chance);
        }

        chance = LootUtils.applyLootMultipliers(chance, player, mob, victim, world);

        amount = LootUtils.WhileRoll(chance);

    }

}
