package com.robertx22.uncommon.utilityclasses;

import com.robertx22.config.dimension_configs.DimensionConfig;
import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.database.world_providers.BaseWorldProvider;
import com.robertx22.database.world_providers.IWP;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.testing.Watch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WorldUtils {

    public static List<MapAffixData> getAllAffixesThatAffect(
            PlayerMapData.IPlayerMapData mapdata, EntityLivingBase entity) {

        List<MapAffixData> list = new ArrayList<>();

        if (mapdata != null) {
            list.addAll(MapItemData.getAllAffixesThatAffect(mapdata.getMap().affixes, entity));
        }

        list.addAll(MapItemData.getAllAffixesThatAffect(getAllMapAffixes(entity.world), entity));

        return list;
    }

    public static List<MapAffixData> getAllMapAffixes(World world) {

        List<MapAffixData> list = new ArrayList<>();

        if (WorldUtils.isMapWorldClass(world) && world.getDimension() instanceof IWP) {
            IWP iwp = (IWP) world.getDimension();

            list.addAll(iwp.getMapAffixes());

        }

        return list;
    }

    public static BlockPos getPosByLevel(World world, int lvl) {

        Watch watch = new Watch();

        DimensionConfig config = DimensionsContainer.INSTANCE.getConfig(world);

        BlockPos pos = LevelUtils.getAreaPosOfLevel(world, lvl, config);

        pos = getSurface(world, pos);

        watch.print("getting spawn pos took ");

        return pos;

    }

    public static BlockPos getSurface(World world, BlockPos pos) {

        pos = pos.add(0, world.getHeight() - 1, 0);

        while (world.isAirBlock(pos)) {

            pos = pos.down();

        }

        return pos.up();

    }

    public static boolean isMapWorld(World world) {

        if (world.getDimension() instanceof BaseWorldProvider == false) {
            return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER > 0;
        }

        return true;

    }

    public static boolean isMapWorldClass(World world) {

        return world.getDimension() instanceof BaseWorldProvider;
    }

    public static IWP getIWP(World theworld) {

        if (theworld.getDimension() instanceof IWP) {

            return (IWP) theworld.getDimension();

        }
        return null;
    }

    public int getTier(World world, EntityPlayer player) {

        if (DimensionsContainer.INSTANCE.hasConfig(world)) {
            return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER;
        } else {

            return Load.playerMapData(player).getTier();
        }

    }

    public static boolean dropsUniques(World world) {

        if (isMapWorld(world) == true) {
            return true;
        }

        return DimensionsContainer.INSTANCE.getConfig(world).DROPS_UNIQUE_ITEMS;
    }

}
