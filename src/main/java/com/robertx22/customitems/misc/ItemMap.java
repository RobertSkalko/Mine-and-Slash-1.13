package com.robertx22.customitems.misc;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.dimensions.blocks.MapPortalBlock;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import com.robertx22.uncommon.utilityclasses.Tooltip;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemMap extends Item {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
	    ITooltipFlag flagIn) {

	MapItemData data = Map.Load(stack);

	showTooltip(data, tooltip);

    }

    public static List<ITextComponent> showTooltip(MapItemData data, List<ITextComponent> tooltip) {

	if (data != null) {

	    ItemRarity rarity = Rarities.Items.get(data.rarity);

	    Tooltip.add(TextFormatting.YELLOW + CLOC.word("level") + ": " + +data.level, tooltip);
	    Tooltip.add("", tooltip);

	    addAffixTypeToTooltip(data, tooltip, AffectedEntities.Mobs);
	    addAffixTypeToTooltip(data, tooltip, AffectedEntities.Players);
	    addAffixTypeToTooltip(data, tooltip, AffectedEntities.All);

	    Tooltip.add("", tooltip);

	    try {
		Tooltip.add(TextFormatting.BLUE + CLOC.word("world_type") + ": " + data.getWorldProvider().locName(),
			tooltip);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    Tooltip.add("", tooltip);
	    Tooltip.add(TextFormatting.GOLD + CLOC.word("tier") + ": " + data.tier, tooltip);

	    Tooltip.add("", tooltip);
	    Tooltip.add(TextFormatting.GREEN + CLOC.word("minutes") + ": " + data.minutes, tooltip);

	    Tooltip.add("", tooltip);
	    Tooltip.add(TextFormatting.YELLOW + CLOC.word("bonus_loot_amount") + ": " + data.getBonusLootAmount() + "%",
		    tooltip);

	    Tooltip.add("", tooltip);
	    Tooltip.add(rarity.Color() + CLOC.word("rarity") + ": " + rarity.locName(), tooltip);

	    Tooltip.add("", tooltip);
	    Tooltip.add(TextFormatting.BLUE + CLOC.tooltip("put_in_mapdevice"), tooltip);

	}

	return tooltip;

    }

    private static void addAffixTypeToTooltip(MapItemData data, List<ITextComponent> tooltip,
	    AffectedEntities affected) {

	List<MapAffixData> affixes = data.getAllAffixesThatAffect(affected);

	if (affixes.size() == 0) {
	    return;
	}

	String str = "";

	if (affected.equals(AffectedEntities.Players)) {
	    str = CLOC.word("player_affixes");
	} else if (affected.equals(AffectedEntities.Mobs)) {
	    str = CLOC.word("mob_affixes");
	}

	Tooltip.add(TextFormatting.GREEN + str, tooltip);

	for (MapAffixData affix : affixes) {

	    for (StatModData statmod : affix.getAffix().Stats(affix.percent)) {

		for (String statstring : statmod.GetTooltipString(Rarities.Maps.get(data.rarity).StatPercents(),
			data.level, false)) {

		    Tooltip.add(" * " + TextFormatting.RED + statstring, tooltip);
		}

	    }

	}
    }

    public static void createMap(int id, BlockPos pos, World world, MapItemData data) {
	IWorldData currentdata = Load.World(world);

	if (currentdata.isMapWorld()) {

	} else {

	    if (data != null) {

		summonPortal(world, pos, id);
	    }
	}
    }

    private static void summonPortal(World world, BlockPos pos, int id) {

	spawnPortalBlock(world, pos, id);

	spawnFrameBlock(world, pos.south());
	spawnFrameBlock(world, pos.north());
	spawnFrameBlock(world, pos.east());
	spawnFrameBlock(world, pos.west());

	spawnFrameBlock(world, pos.south().east());
	spawnFrameBlock(world, pos.south().west());
	spawnFrameBlock(world, pos.north().east());
	spawnFrameBlock(world, pos.north().west());

    }

    private static void spawnPortalBlock(World world, BlockPos pos, int id) {
	world.setBlockState(pos, MapPortalBlock.BLOCK.getDefaultState(), 2);
	TileMapPortal portal = new TileMapPortal(id);
	world.setTileEntity(pos, portal);
    }

    private static void spawnFrameBlock(World world, BlockPos pos) {

	world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState(), 2);
    }

    public ItemMap() {
	super(new Properties());

    }

}