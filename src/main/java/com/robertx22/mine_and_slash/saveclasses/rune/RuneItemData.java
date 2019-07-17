package com.robertx22.mine_and_slash.saveclasses.rune;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.ISalvagable;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ListUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Storable
public class RuneItemData implements ISalvagable {

    public RuneItemData() {

    }

    public RuneItemData(int level, String name, int rarity, StatModData weapon,
                        StatModData armor, StatModData jewerly) {

        this.name = name;
        this.level = level;
        this.rarity = rarity;
        this.weapon = weapon;
        this.armor = armor;
        this.jewerly = jewerly;
    }

    @Store
    public String name;

    @Store
    public int level = 1;

    @Store
    public StatModData weapon;
    @Store
    public StatModData armor;
    @Store
    public StatModData jewerly;

    public StatModData getModFor(GearItemData gear) {

        GearItemSlot slot = gear.GetBaseGearType();

        if (slot.slotType().equals(GearItemSlot.GearSlotType.Armor)) {
            return armor;
        } else if (slot.slotType().equals(GearItemSlot.GearSlotType.Jewerly)) {
            return jewerly;
        }

        return weapon;

    }

    @Store
    public int rarity = 0;

    @Override
    public int getSalvagedRarity() {
        return this.rarity;
    }

    public RuneRarity GetRarity() {

        return Rarities.Runes.get(rarity);

    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        int min = tryIncreaseAmount(salvageBonus, 1);
        int max = tryIncreaseAmount(salvageBonus, 2);

        ItemStack stack = ItemStack.EMPTY;

        if (RandomUtils.roll(this.GetRarity().specialItemChance())) {

            Item item = RandomUtils.weightedRandom(ListUtils.SameTierOrLess(CurrencyItem.ITEMS, 10));

            stack = new ItemStack(item);
        } else {

            int amount = RandomUtils.RandomRange(min, max);

            ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);

            stack = new ItemStack(ore);
            stack.setCount(amount);

        }

        return stack;
    }

    @Override
    public boolean isSalvagable() {
        return true;
    }

}