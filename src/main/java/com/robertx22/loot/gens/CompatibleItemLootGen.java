package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.config.compatible_items.ConfigItem;
import com.robertx22.config.compatible_items.ConfigItems;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class CompatibleItemLootGen extends BaseLootGen {

    EntityData.UnitData mob;

    public CompatibleItemLootGen(EntityData.UnitData mob, EntityData.UnitData player,
                                 EntityLivingBase victim, EntityPlayer killer) {
        super(mob, player, victim, killer);

        this.mob = mob;
    }

    public CompatibleItemLootGen(World theworld, float multi, int level) {
        super(theworld, multi);

    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.COMPATIBLE_ITEMS_DROPRATE.get().floatValue();
    }

    @Override
    public ItemStack generateOne() {

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get() == true && mob != null) {

            return gen(mob.getLevel());
        }

        return ItemStack.EMPTY;

    }

    public static ItemStack gen(int level) {

        ConfigItem config = (ConfigItem) RandomUtils.WeightedRandom(ListUtils.CollectionToList(ConfigItems.INSTANCE
                .getAll()
                .stream()
                .filter(x -> x.statsAddedOnlyOnDrop == false)
                .collect(Collectors.toList())));

        if (config != null) {
            ResourceLocation res = new ResourceLocation(config.registryName);

            if (ForgeRegistries.ITEMS.containsKey(res)) {

                ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(res));

                return config.create(stack, level);
            }
        }

        return ItemStack.EMPTY;
    }

}