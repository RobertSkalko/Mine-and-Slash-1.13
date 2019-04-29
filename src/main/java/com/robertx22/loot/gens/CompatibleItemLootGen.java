package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.config.non_mine_items.ConfigItems;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class CompatibleItemLootGen extends BaseLootGen {

    EntityData.UnitData mob;

    public CompatibleItemLootGen(EntityData.UnitData mob, EntityData.UnitData player,
                                 IWorldData world, EntityLivingBase victim) {
        super(mob, player, world, victim);

        this.mob = mob;
    }

    public CompatibleItemLootGen(World theworld, float multi, IWorldData world,
                                 int level) {
        super(theworld, multi, world);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.COMPATIBLE_ITEMS_DROPRATE.get();
    }

    @Override
    public ItemStack generateOne() {

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get() == true && mob != null) {

            ConfigItem config = (ConfigItem) RandomUtils.WeightedRandom(ListUtils.CollectionToList(ConfigItems.INSTANCE
                    .getAll()));

            ResourceLocation res = new ResourceLocation(config.registryName);

            if (ForgeRegistries.ITEMS.containsKey(res)) {

                ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(res));

                config.create(stack, mob);
            }

        }

        return ItemStack.EMPTY;

    }

}