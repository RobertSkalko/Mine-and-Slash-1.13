package com.robertx22.blocks.bases;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseInventoryBlock extends NonFullBlock {
    protected BaseInventoryBlock(Properties prop) {
        super(prop);

    }

    @Deprecated
    public List<ItemStack> getDrops(BlockState blockstate, LootContext.Builder context) {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(this));

        TileEntity tileentity = context.get(LootParameters.BLOCK_ENTITY);

        if (tileentity instanceof BaseTile) {

            BaseTile inv = (BaseTile) tileentity;

            for (ItemStack stack : inv.itemStacks) {
                if (stack.isEmpty() == false) {
                    items.add(stack);
                }
            }

        }

        return items;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos,
                                    PlayerEntity player, Hand hand,
                                    BlockRayTraceResult ray) {
        if (world.isRemote) {
            return true;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof BaseTile) {

            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, extraData -> {
                extraData.writeBlockPos(tile.getPos());
            });

        }

        return true;
    }

}
