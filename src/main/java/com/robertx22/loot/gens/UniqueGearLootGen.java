package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.loot.create.UniqueGearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UniqueGearLootGen extends BaseLootGen {
    UniqueBlueprint gearPrint;

    public UniqueGearLootGen(UnitData mob, UnitData player, EntityLivingBase victim,
                             EntityPlayer killer) {
        super(mob, player, victim, killer);
        gearPrint = new UniqueBlueprint(mob.getLevel(), this.tier, true);

    }

    public UniqueGearLootGen(World theworld, float multi, int level) {
        super(theworld, multi);
        gearPrint = new UniqueBlueprint(level, this.tier, true);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        ItemStack stack = UniqueGearGen.CreateStack(gearPrint);

        GearItemData gear = Gear.Load(stack);

        return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
