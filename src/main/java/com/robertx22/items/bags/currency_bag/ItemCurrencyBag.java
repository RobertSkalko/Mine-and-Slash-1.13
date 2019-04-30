package com.robertx22.items.bags.currency_bag;

import javax.annotation.Nonnull;

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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemCurrencyBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356514;

    @ObjectHolder(Ref.MODID + ":currency_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemCurrencyBag() {
	super("currency_bag");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemCurrencyBag());

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
	if (!world.isRemote) {
	    NetworkHooks.openGui((EntityPlayerMP) player, new InteractCurrencyBag(player.getHeldItem(hand)), buf -> {
		buf.writeBoolean(hand == EnumHand.OFF_HAND);
	    });
	}
	return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public boolean IsValidItem(ItemStack stack) {

	return stack.getItem() instanceof ICurrencyItemEffect || stack.getItem() instanceof CurrencyItem
		|| stack.getItem() instanceof ItemOre;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
