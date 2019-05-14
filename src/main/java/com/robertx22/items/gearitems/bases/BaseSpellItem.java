package com.robertx22.items.gearitems.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.datasaving.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class BaseSpellItem extends Item {

    public abstract String GUID();

    public abstract BaseSpell Spell();

    public BaseSpellItem() {
        super(new Properties().maxStackSize(0).defaultMaxDamage(0));

        this.setRegistryName(GUID().toLowerCase());

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                    EnumHand handIn) {

        if (worldIn.isRemote) {
            this.Spell().cast(worldIn, playerIn, handIn, 5, null);
        } else {

            try {
                SpellItemData data = Spell.Load(playerIn.getHeldItem(handIn));

                if (data != null) {

                    if (Spell().CanCast(playerIn, data)) {
                        Spell().cast(worldIn, playerIn, handIn, 5, data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
