package com.robertx22.items.bags.map_bag;

import javax.annotation.Nonnull;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.datasaving.Map;

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

@EventBusSubscriber
public class ItemMapBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356516;

    @ObjectHolder(Ref.MODID + ":map_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemMapBag() {
	super("map_bag");

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
	if (!world.isRemote) {
	    NetworkHooks.openGui((EntityPlayerMP) player, new InteractMapBag(player.getHeldItem(hand)), buf -> {
		buf.writeBoolean(hand == EnumHand.OFF_HAND);
	    });
	}
	return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemMapBag());
    }

    public boolean IsValidItem(ItemStack stack) {

	MapItemData map = Map.Load(stack);

	if (map != null) {
	    return true;

	}

	return false;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
