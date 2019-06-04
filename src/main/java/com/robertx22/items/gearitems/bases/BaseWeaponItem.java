package com.robertx22.items.gearitems.bases;

import com.google.common.collect.Sets;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IGearItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Set;

public abstract class BaseWeaponItem extends Item implements IWeapon, IAutoLocName, IGearItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public BaseWeaponItem(int rar) {

        super(new Properties().defaultMaxDamage(1000));
        this.rarity = rar;
    }

    public int rarity = 0;

    @Override
    public String locNameLangFileGUID(String guid) {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    public static boolean checkDurability(EntityLivingBase attacker, ItemStack stack) {

        if (stack.getDamage() > stack.getMaxDamage() - 20) {
            attacker.sendMessage(SLOC.chat("low_weapon_durability"));
            return false;

        }
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target,
                             EntityLivingBase attacker) {

        stack.damageItem(1, attacker);

        return true;
    }

}
