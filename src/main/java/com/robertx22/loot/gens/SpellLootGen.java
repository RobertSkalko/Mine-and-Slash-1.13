package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.SpellBlueprint;
import com.robertx22.loot.create.SpellItemGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpellLootGen extends BaseLootGen {

    SpellBlueprint spellPrint;

    public SpellLootGen(UnitData mob, UnitData player, IWorldData world,
                        EntityLivingBase victim) {
        super(mob, player, world, victim);

        spellPrint = new SpellBlueprint(mob.getLevel());

    }

    public SpellLootGen(World theworld, float multi, IWorldData world, int level) {
        super(theworld, multi, world);

        spellPrint = new SpellBlueprint(level);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.SPELL_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        return SpellItemGen.Create(spellPrint);

    }

}
