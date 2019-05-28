package com.robertx22.items.spells;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.datasaving.Spell;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseSpellItem extends Item {

    public abstract String GUID();

    public abstract BaseSpell Spell();

    public BaseSpellItem() {
        super(new Properties().maxStackSize(0).defaultMaxDamage(0));

        this.setRegistryName(GUID().toLowerCase());

    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    final public int getUseDuration(ItemStack stack) {
        return Spell().useTimeTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     EntityLivingBase playerIn) {

        if (playerIn instanceof EntityPlayer) {
            try {
                SpellItemData data = Spell.Load(stack);

                if (worldIn.isRemote) {
                    this.Spell()
                            .cast(worldIn, (EntityPlayer) playerIn, playerIn.getActiveHand(), 5, data);
                } else {

                    if (data != null) {

                        if (Spell().CanCast((EntityPlayer) playerIn, data)) {
                            Spell().cast(worldIn, (EntityPlayer) playerIn, playerIn.getActiveHand(), 5, data);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player,
                                                    EnumHand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

}
