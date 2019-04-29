package com.robertx22.dimensions;

import com.robertx22.loot.MasterLootGen;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.List;
import java.util.Random;

public class ChestGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        IWorldData data = Load.World(world);

        if (data != null && data.isMapWorld()) {

            if (RandomUtils.roll(1F)) {

                List<ItemStack> loot = MasterLootGen.gen(world, 2F, data, data.getLevel());

                while (loot.size() < 2) {
                    loot.addAll(MasterLootGen.gen(world, 1F, data, data.getLevel()));
                }

                int x = chunkX * 16 + random.nextInt(8);
                int z = chunkZ * 16 + random.nextInt(8);

                BlockPos pos1 = new BlockPos(x, 0, z);

                BlockPos pos = getTopSolidBlock(world, pos1);

                genChest(world, pos, loot);

            }

        }

    }

    public static BlockPos getTopSolidBlock(World world, BlockPos pos) {

        int y = world.getHeight();

        while (world.isAirBlock(new BlockPos(pos.getX(), y, pos.getZ())) && y > -1) {
            y--;
        }

        return new BlockPos(pos.getX(), y, pos.getZ());

    }

    private void genChest(World world, BlockPos pos, List<ItemStack> items) {

        TileEntityChest chest = new TileEntityChest();

        for (int i = 0; i < chest.getSizeInventory(); i++) {
            if (items.size() > i) {
                chest.setInventorySlotContents(i, items.get(i));
            }
        }

        world.setBlockState(pos, Blocks.CHEST.getDefaultState());
        world.setTileEntity(pos, chest);

    }

}
