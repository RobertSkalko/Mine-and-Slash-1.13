package com.robertx22.world_gen.features;

import com.robertx22.loot.MasterLootGen;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.world_gen.utils.WorldGenUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;
import java.util.Random;

public class RandomSurfaceChestFeature extends Feature<NoFeatureConfig> {

    @Override
    public boolean place(IWorld iworld,
                         IChunkGenerator<? extends IChunkGenSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        if (iworld.isAirBlock(pos)) {
            World theworld = iworld.getWorld();
            IWorldData iworlddata = Load.World(theworld);

            List<ItemStack> loot = MasterLootGen.genAmount(pos, RandomUtils.RandomRange(3, 10), theworld, iworlddata);

            WorldGenUtils.generateChestWithLoot(iworld, pos, loot);
            return true;
        } else {
            return false;

        }

    }

}
