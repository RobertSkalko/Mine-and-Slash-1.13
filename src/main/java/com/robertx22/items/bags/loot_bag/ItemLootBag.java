package com.robertx22.items.bags.loot_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.datasaving.Spell;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

public class ItemLootBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":loot_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemLootBag() {
        super(ID);

    }

    @Override
    public INamedContainerProvider getNamedContainer(ItemStack stack) {
        return new InteractLootBag(stack);
    }

    public boolean IsValidItem(ItemStack stack) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null) {
            return true;
        }

        SpellItemData spell = Spell.Load(stack);

        if (spell != null) {
            return true;
        }

        RuneItemData rune = Rune.Load(stack);
        if (rune != null) {
            return true;

        }
        return false;
    }

}
