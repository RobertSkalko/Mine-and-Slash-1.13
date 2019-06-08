package com.robertx22.items.gearitems.bases;

import com.google.common.collect.Sets;
import com.robertx22.uncommon.Chats;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IGearItem;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;
import java.util.Set;

public abstract class BaseWeaponItem extends Item implements IWeapon, IAutoLocName, IGearItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public BaseWeaponItem(int rar) {

        super(new Properties().defaultMaxDamage(1000));
        this.rarity = rar;
    }

    public int rarity = 0;

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    public static boolean checkDurability(LivingEntity attacker, ItemStack stack) {

        if (stack.getDamage() > stack.getMaxDamage() - 20) {
            attacker.sendMessage(Chats.Weapon_durability_is_too_low.locName());
            return false;

        }
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target,
                             LivingEntity attacker) {

        stack.attemptDamageItem(1, new Random(), null);

        return true;
    }

}
