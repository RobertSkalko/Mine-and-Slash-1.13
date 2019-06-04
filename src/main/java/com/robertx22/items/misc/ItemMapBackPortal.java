package com.robertx22.items.misc;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.Chats;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
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
import net.minecraftforge.registries.ObjectHolder;

public class ItemMapBackPortal extends Item {

    @ObjectHolder(Ref.MODID + ":map_back_portal")
    public static final Item ITEM = null;

    public ItemMapBackPortal() {

        super(new Properties().group(CreativeTabs.MyModTab));

        RegisterItemUtils.RegisterItemName(this, "map_back_portal");

    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn,
                              int itemSlot, boolean isSelected) {

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
                        nbt.putInt("ticks", 0);

                        entityIn.sendMessage(Chats.Teleport_canceled_due_to_movement.locName());

                    } else {

                        if (nbt.contains("ticks")) {

                            int ticks = nbt.getInt("ticks");
                            nbt.putInt("ticks", ticks + 1);

                            if (ticks > 100) {

                                nbt.putInt("ticks", 0);
                                nbt.putBoolean("porting", false);

                                PlayerMapData.IPlayerMapData data = Load.playerMapData((EntityPlayer) entityIn);
                                data.teleportPlayerBack((EntityPlayer) entityIn);

                                stack.setCount(stack.getCount() - 1);

                            }
                        } else {
                            nbt.putInt("ticks", 1);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    EnumHand hand) {

        if (!world.isRemote) {
            try {

                if (WorldUtils.isMapWorld(world)) {

                    if (!player.getHeldItem(hand).hasTag()) {
                        player.getHeldItem(hand).setTag(new NBTTagCompound());
                    }

                    player.getHeldItem(hand).getTag().putBoolean("porting", true);
                    player.getHeldItem(hand)
                            .getTag()
                            .putLong("pos", player.getPosition().toLong());

                    player.sendMessage(Chats.Teleport_started.locName());

                    return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));

                } else {
                    player.sendMessage(Chats.You_are_not_inside_a_map_world.locName());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

}
