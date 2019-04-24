package com.robertx22.customitems.misc;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class ItemMapBackPortal extends Item {

    @ObjectHolder(Ref.MODID + ":map_back_portal")
    public static final Item ITEM = null;

    public ItemMapBackPortal() {

	RegisterItemUtils.RegisterItemName(this, "map_back_portal");
	this.setMaxStackSize(1);
	this.setMaxDamage(0);
	this.setCreativeTab(CreativeTabList.MyModTab);

    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

	try {
	    if (!worldIn.isRemote && entityIn instanceof EntityPlayer) {

		NBTTagCompound nbt = stack.getTag();

		if (nbt == null) {
		    nbt = new NBTTagCompound();
		}

		if (nbt.getBoolean("porting")) {

		    BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));

		    if (pos.distanceSq(entityIn.getPosition()) > 2) {

			nbt.putBoolean("porting", false);
			nbt.setInt("ticks", 0);

			entityIn.sendMessage(SLOC.chat("teleport_canceled"));

		    } else {

			if (nbt.hasKey("ticks")) {

			    int ticks = nbt.getInt("ticks");
			    nbt.setInt("ticks", ticks + 1);

			    if (ticks > 100) {

				nbt.setInt("ticks", 0);
				nbt.putBoolean("porting", false);

				IWorldData data = Load.World(worldIn);
				data.teleportPlayerBack((EntityPlayer) entityIn);

				stack.setCount(stack.getCount() - 1);

			    }
			} else {
			    nbt.setInt("ticks", 1);
			}

		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

	if (!worldIn.isRemote) {
	    try {

		IWorldData data = Load.World(worldIn);
		if (data != null) {
		    if (data.isMapWorld()) {

			if (!player.getHeldItem(hand).hasTag()) {
			    player.getHeldItem(hand).setTag(new NBTTagCompound());
			}

			player.getHeldItem(hand).getTag().putBoolean("porting", true);
			player.getHeldItem(hand).getTag().setLong("pos", player.getPosition().toLong());

			player.sendMessage(SLOC.chat("teleport_begin"));

			return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));

		    } else {
			player.sendMessage(SLOC.chat("not_inside_map"));

		    }

		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemMapBackPortal());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

}
