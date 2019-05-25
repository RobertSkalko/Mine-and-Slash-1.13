package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.loot.create.AwakenRuneWordGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AwakenRuneWordLootGen extends BaseLootGen {

    public AwakenRuneWordLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                                 EntityPlayer killer) {
        super(mob, player, victim, killer);

    }

    public AwakenRuneWordLootGen(World theworld, float multi) {
        super(theworld, multi);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.AWAKEN_RUNEWORD_DROPRATE.get().floatValue();

    }

    @Override
    public ItemStack generateOne() {

        return AwakenRuneWordGen.Create(new AwakenRuneWordBlueprint());

    }

}
