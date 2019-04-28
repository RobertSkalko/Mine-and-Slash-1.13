package com.robertx22.saveclasses;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.MapManager;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.List;

@Storable
public class MapItemData implements ISalvagable {

    @Store
    public int minutes = 30; // default

    @Store
    public int level = 1;

    @Store
    public int tier = 0;

    @Store
    public int rarity = 0;

    @Store
    public List<MapAffixData> affixes = new ArrayList<MapAffixData>();

    @Store
    public String worldGeneratorName;

    @Override
    public int getSalvagedRarity() {
        return this.rarity;
    }

    public int getBonusLootAmount() {

        return (int) (getTotalPercents() * .5F);

    }

    public int getBonusLootRarity() {

        return (int) (getTotalPercents() * 0.5F);

    }

    public boolean increaseLevel(int i) {

        int lvl = level + i;

        if (lvl > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
            return false;
        }

        level = lvl;

        return true;
    }

    public boolean increaseTier(int i) {

        int tier = this.tier + i;

        if (tier > 20) {
            return false;
        }

        this.tier = tier;

        return true;
    }

    private int getTotalPercents() {

        int total = 0;
        for (MapAffixData affix : affixes) {
            total += affix.percent;
        }
        return total;
    }

    public List<MapAffixData> getAllAffixesThatAffect(AffectedEntities affected) {

        List<MapAffixData> list = new ArrayList<>();

        for (MapAffixData data : affixes) {
            if (data.affectedEntities.equals(affected)) {
                list.add(data);
            }
        }
        return list;
    }

    public IWP getWorldProvider() {
        return WorldProviders.All.get(this.worldGeneratorName);
    }

    public DimensionType createDimension(World ogworld, BlockPos pos,
                                         EntityPlayer player) {

        UnitData unit = Load.Unit(player);

        return MapManager.createNewDim(ogworld, player, unit, this, pos);

    }

    public MapRarity GetRarity() {

        return Rarities.Maps.get(rarity);

    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        int min = tryIncreaseAmount(salvageBonus, 1);
        int max = tryIncreaseAmount(salvageBonus, 3);

        ItemStack stack = ItemStack.EMPTY;

        if (RandomUtils.roll(this.GetRarity().specialItemChance())) {

            Item item = (Item) RandomUtils.WeightedRandom(ListUtils.SameTierOrLess(ListUtils
                    .CollectionToList(CurrencyItem.ITEMS), tier));

            stack = new ItemStack(item);
        } else {

            int amount = RandomUtils.RandomRange(min, max);

            ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);

            stack = new ItemStack(ore);
            stack.setCount(amount);

        }

        return stack;
    }

}
