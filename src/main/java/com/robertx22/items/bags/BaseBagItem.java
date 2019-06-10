package com.robertx22.items.bags;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.ItemSingle;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class BaseBagItem extends Item {

    public abstract boolean IsValidItem(ItemStack stack);

    public abstract INamedContainerProvider getNamedContainer(ItemStack stack);

    public static int size = 9 * 6;

    public BaseBagItem(String name) {

        super(new ItemSingle().group(CreativeTabs.MyModTab));
        RegisterItemUtils.RegisterItemName(this, name);

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    @Nonnull Hand hand) {
        if (!world.isRemote) {
            player.openContainer(getNamedContainer(player.getHeldItem(hand)));
        }
        return ActionResult.newResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
        return new InvProvider();
    }

    private static class InvProvider implements ICapabilitySerializable<INBT> {

        private final IItemHandler inv = new ItemStackHandler(size);
        private final LazyOptional<IItemHandler> opt = LazyOptional.of(() -> inv);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
            }
            return LazyOptional.empty();
        }

        @Override
        public INBT serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
        }

        @Override
        public void deserializeNBT(INBT nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);

        }

    }

}