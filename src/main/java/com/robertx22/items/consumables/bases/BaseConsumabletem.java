package com.robertx22.items.consumables.bases;

import com.robertx22.database.IGUID;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseConsumabletem extends Item implements IGUID {

    public BaseConsumabletem() {
        super(new Properties().maxStackSize(64).group(CreativeTabs.MyModTab));
        this.setRegistryName(new ResourceLocation(GUID()));
    }

    public abstract ITextComponent tooltip();

    public int useDurationInTicks() {
        return 30;
    }

    public abstract void onFinish(ItemStack stack, World world, EntityLivingBase player,
                                  EntityData.UnitData unitdata);

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(Styles.GREENCOMP().appendSibling(tooltip()));

    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useDurationInTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world,
                                     EntityLivingBase player) {

        onFinish(stack, world, player, Load.Unit(player));
        stack.shrink(1);
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
