package com.robertx22.blocks.simple;

import com.robertx22.blocks.bases.NonFullBlock;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttunementBlock extends NonFullBlock {

    public static final String ID = Ref.MODID + ":attunement_altar";

    @ObjectHolder(ID)
    public static Block BLOCK = null;
    @ObjectHolder(ID)
    public static Block ITEMBLOCK = null;

    public AttunementBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3F));
    }

    @Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos,
                                        int fortune) {

        return ITEMBLOCK;
    }

    @Override
    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn,
                                   BlockPos pos, Random random) {
        return 1;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        event.getRegistry()
                .register(new ItemBlock(BLOCK, new Item.Properties().group(CreativeTabs.MyModTab))
                        .setRegistryName(ID));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new AttunementBlock().setRegistryName(ID));

    }

}
