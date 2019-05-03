package com.robertx22.items.bags;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.ItemSingle;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class BaseBagItem extends Item {

    public abstract int GuiNumber();

    public abstract boolean IsValidItem(ItemStack stack);

    public static int size = 9 * 6;

    private static final String TAG_ITEMS = "InvItems";

    public BaseBagItem(String name) {

        super(new ItemSingle().group(CreativeTabs.MyModTab));
        RegisterItemUtils.RegisterItemName(this, name);

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

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
            else
                return null;
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

    // I THINK ITS NOT NEEDED
    /*
     * @Override public void inventoryTick(ItemStack stack, World world, Entity
     * entityIn, int slot, boolean selected) { if (stack.getTag() != null &&
     * stack.getTag().contains(TAG_ITEMS)) { NBTTagList oldData =
     * stack.getTag().getList(TAG_ITEMS, Constants.NBT.TAG_COMPOUND); IItemHandler
     * newInv = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
     * null).orElse(null);
     *
     * if (newInv != null) {
     * CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(newInv, null, oldData);
     *
     * stack.getTag().remove(TAG_ITEMS);
     *
     * if (stack.getTag().size() == 0) stack.setTag(null); } } }
     */

}