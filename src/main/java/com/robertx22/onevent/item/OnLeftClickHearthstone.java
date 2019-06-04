package com.robertx22.onevent.item;

import com.robertx22.items.level_incentives.Hearthstone;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.uncommon.Chats;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnLeftClickHearthstone {

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock evt) {
        if (evt.getSide().equals(LogicalSide.SERVER)) {

            EntityPlayerMP player = (EntityPlayerMP) evt.getEntityPlayer();
            IBlockState block = player.world.getBlockState(evt.getPos());

            ItemStack stack = evt.getItemStack();

            if (stack.getItem() instanceof Hearthstone == false) {
                return;
            }

            Hearthstone item = (Hearthstone) evt.getItemStack().getItem();

            if (block.getBlock().equals(BlockRegister.ATTUNEMENT_ALTAR_BLOCK)) {

                DimensionType type = evt.getWorld().getDimension().getType();

                item.setLoc(stack, new BlockPos(evt.getHitVec()), type);
                player.sendMessage(Chats.You_have_attuned_to_this_Altar.locName());

            } else {
                player.sendMessage(Chats.This_is_not_an_Attunement_Altar.locName());
            }
        }

    }

}