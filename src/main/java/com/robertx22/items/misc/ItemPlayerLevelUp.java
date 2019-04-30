package com.robertx22.items.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.BaseItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ItemPlayerLevelUp extends BaseItem {

    @ObjectHolder(Ref.MODID + ":player_levelup")
    public static final Item ITEM = null;

    public ItemPlayerLevelUp() {

	super(new Properties().group(CreativeTabs.CurrencyTab));

	RegisterItemUtils.RegisterItemName(this, "player_levelup");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {

	if (!worldIn.isRemote) {
	    try {

		int req = tokensRequired(Load.Unit(player).getLevel());

		if (hasEnoughTokens(player.getHeldItem(handIn), req)) {

		    if (Load.Unit(player).LevelUp((EntityPlayerMP) player)) {

			return new ActionResult<ItemStack>(EnumActionResult.PASS,
				EmptyOrDecrease(player.getHeldItem(handIn), req));

		    }
		} else {
		    player.sendMessage(new TextComponentString("You need a total of " + req + " tokens to level up."));
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
    }

    private boolean hasEnoughTokens(ItemStack stack, int tokensreq) {
	return stack.getCount() >= tokensreq;
    }

    private int tokensRequired(int level) {

	int req = level / 10;

	if (req < 1) {
	    req = 1;
	}
	if (req >= 64) {
	    return 64;
	} else {
	    return req;
	}
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemPlayerLevelUp());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
	    ITooltipFlag flagIn) {

	Tooltip.add(CLOC.tooltip("player_levelup"), tooltip);

    }

}
