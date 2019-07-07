package com.robertx22.items.gearitems.bases.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class RarityItemTier implements IItemTier {

    public RarityItemTier(int rar) {
        this.rarity = rar;
    }

    int rarity = 0;

    @Override
    public int getMaxUses() {
        return net.minecraft.item.ItemTier.IRON.getMaxUses() + (net.minecraft.item.ItemTier.WOOD
                .getMaxUses() * 200);
    }

    @Override
    public float getEfficiency() {
        return net.minecraft.item.ItemTier.IRON.getEfficiency() + net.minecraft.item.ItemTier.WOOD
                .getEfficiency() * rarity;
    }

    @Override
    public float getAttackDamage() {
        return net.minecraft.item.ItemTier.IRON.getAttackDamage() + net.minecraft.item.ItemTier.WOOD
                .getAttackDamage() * rarity;
    }

    @Override
    public int getHarvestLevel() {
        return net.minecraft.item.ItemTier.IRON.getHarvestLevel() + 1 * rarity;
    }

    @Override
    public int getEnchantability() {
        return net.minecraft.item.ItemTier.IRON.getEnchantability() + (net.minecraft.item.ItemTier.IRON
                .getEnchantability() * rarity / 3);
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(Items.STRUCTURE_BLOCK);
    }
}
