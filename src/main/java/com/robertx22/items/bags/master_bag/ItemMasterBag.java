package com.robertx22.items.bags.master_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

public class ItemMasterBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":master_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemMasterBag() {
        super(ID);

        this.size *= ContainerMasterBag.ItemType.values().length;

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    @Nonnull Hand hand) {
        if (!world.isRemote) {

            NetworkHooks.openGui((ServerPlayerEntity) player, getNamedContainer(player.getHeldItem(hand)), extraData -> {
                extraData.writeString(ContainerMasterBag.ItemType.GEAR.toString());
                extraData.writeString(ContainerMasterBag.ItemType.GEAR.toString());
            });

        }
        return ActionResult.newResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public ItemFilterGroup filterGroup() {
        return ItemFilterGroup.ANY_MASTER_BAG;
    }

    @Override
    public INamedContainerProvider getNamedContainer(ItemStack stack) {
        return new NamedMasterBag(stack, ContainerMasterBag.ItemType.GEAR);
    }

}
