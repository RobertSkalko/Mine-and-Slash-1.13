package com.robertx22.items.bags.currency_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.mmorpg.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

public class ItemCurrencyBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":currency_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemCurrencyBag() {
        super(ID);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    @Nonnull EnumHand hand) {
        if (!world.isRemote) {
            NetworkHooks.openGui((EntityPlayerMP) player, new InteractCurrencyBag(player.getHeldItem(hand)), buf -> {
                buf.writeBoolean(hand == EnumHand.OFF_HAND);
            });
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public boolean IsValidItem(ItemStack stack) {

        return stack.getItem() instanceof ICurrencyItemEffect || stack.getItem() instanceof CurrencyItem || stack
                .getItem() instanceof ItemOre;
    }

}
