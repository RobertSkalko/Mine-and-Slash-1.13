package com.robertx22.items.gearitems.weapons;

import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.BowWeaponMechanic;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IGearItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class ItemBow extends net.minecraft.item.ItemBow implements IWeapon, IAutoLocName, IGearItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemBow(int rar) {
        super(new Properties().maxStackSize(1).defaultMaxDamage(1000));
        this.rarity = rar;
    }

    public int rarity = 0;

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Bow";
    }

    @Override
    public WeaponMechanic mechanic() {
        return new BowWeaponMechanic();
    }

    @Override
    public String locNameLangFileGUID(String guid) {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }
    // faster loading

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getUseDuration(ItemStack stack) {
        return 40000;//// 72000;
    }

}
