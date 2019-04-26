package com.robertx22.customitems.gearitems.bases;

import java.util.Set;

import com.google.common.collect.Sets;
import com.robertx22.uncommon.SLOC;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemTool;

public abstract class BaseWeaponItem extends ItemTool implements IWeapon {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public abstract String Name();

    public BaseWeaponItem() {

	super(2F, 2F, ItemTier.DIAMOND, EFFECTIVE_ON, new Properties());

    }

    public static boolean checkDurability(EntityLivingBase attacker, ItemStack stack) {

	if (stack.getDamage() > stack.getMaxDamage() - 20) {
	    attacker.sendMessage(SLOC.chat("low_weapon_durability"));
	    return false;

	}
	return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

	stack.damageItem(1, attacker);

	return true;
    }

}
