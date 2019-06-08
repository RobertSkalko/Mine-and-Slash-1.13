package com.robertx22.items.consumables.bases;

import com.robertx22.database.IGUID;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.UseAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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

    public abstract void onFinish(ItemStack stack, World world, LivingEntity player,
                                  EntityData.UnitData unitdata);

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(Styles.GREENCOMP().appendSibling(tooltip()));

    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useDurationInTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world,
                                     LivingEntity player) {

        onFinish(stack, world, player, Load.Unit(player));
        stack.shrink(1);
        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player,
                                                    Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

}
