package com.robertx22.items.bags;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.ItemSingle;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class BaseBagItem extends Item {

    public abstract boolean IsValidItem(ItemStack stack);

    public abstract IInteractionObject getInteractionObject(ItemStack stack);

    public static int size = 9 * 6;

    public BaseBagItem(String name) {

        super(new ItemSingle().group(CreativeTabs.MyModTab));
        RegisterItemUtils.RegisterItemName(this, name);

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    @Nonnull EnumHand hand) {
        if (!world.isRemote) {
            NetworkHooks.openGui((EntityPlayerMP) player, getInteractionObject(player.getHeldItem(hand)), buf -> {
                buf.writeBoolean(hand == EnumHand.OFF_HAND);
            });
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack,
                                                NBTTagCompound oldCapNbt) {
        return new InvProvider();
    }

    private static class InvProvider implements ICapabilitySerializable<INBTBase> {

        private final IItemHandler inv = new ItemStackHandler(size);
        private final LazyOptional<IItemHandler> opt = LazyOptional.of(() -> inv);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
            }
            return LazyOptional.empty();
        }

        @Override
        public INBTBase serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
        }

        @Override
        public void deserializeNBT(INBTBase nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);

        }

    }

}