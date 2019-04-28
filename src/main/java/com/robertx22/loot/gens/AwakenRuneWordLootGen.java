package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.loot.create.AwakenRuneWordGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AwakenRuneWordLootGen extends BaseLootGen {

    public AwakenRuneWordLootGen(UnitData mob, UnitData player, IWorldData world,
                                 EntityLivingBase victim) {
        super(mob, player, world, victim);

    }

    public AwakenRuneWordLootGen(World theworld, float multi, IWorldData world) {
        super(theworld, multi, world);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.get().AWAKEN_RUNEWORD_DROPRATE.get();

    }

    @Override
    public ItemStack generateOne() {

        return AwakenRuneWordGen.Create(new AwakenRuneWordBlueprint());

    }

}
