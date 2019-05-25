package com.robertx22.saveclasses;

import com.robertx22.Styles;
import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.dimensions.MapManager;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class MapItemData implements ISalvagable, ITooltip {

    @Store
    public int minutes = 30; // default

    @Store
    public int level = 1;

    @Store
    public int tier = 0;

    @Store
    public int rarity = 0;

    @Store
    public boolean isPermaDeath = false;

    @Store
    public List<MapAffixData> affixes = new ArrayList<MapAffixData>();

    //@Store
    // public String worldGeneratorName;

    @Override
    public int getSalvagedRarity() {
        return this.rarity;
    }

    public int getBonusLootAmount() {

        return (int) (getTotalPercents() * .5F) + getPermaDeathBonusLoot();

    }

    public int getPermaDeathBonusLoot() {
        return this.isPermaDeath ? 50 : 0;
    }

    public int getBonusLootRarity() {

        return (int) (getTotalPercents() * 0.5F);

    }

    public boolean increaseLevel(int i) {

        int lvl = level + i;

        if (lvl > ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get()) {
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

    public DimensionType initDimension(World ogworld, BlockPos pos, EntityPlayer player) {

        UnitData unit = Load.Unit(player);

        return MapManager.initDimension(ogworld, player, unit, this, pos);

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

    @Override
    public boolean isSalvagable() {
        return true;
    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             UnitData unitdata) {

        if (unitdata != null) {

            List<ITextComponent> tooltip = event.getToolTip();

            ItemRarity rarity = Rarities.Items.get(this.rarity);

            tooltip.add(TooltipUtils.level(this.level));
            Tooltip.add("", tooltip);

            addAffixTypeToTooltip(this, tooltip, AffectedEntities.Mobs);
            addAffixTypeToTooltip(this, tooltip, AffectedEntities.Players);
            addAffixTypeToTooltip(this, tooltip, AffectedEntities.All);

            Tooltip.add("", tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(CLOC.word("tier")
                            .appendText(": " + this.tier)), tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.GREENCOMP()
                    .appendSibling(CLOC.word("minutes")
                            .appendText(": " + this.minutes)), tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.YELLOWCOMP()
                    .appendSibling(CLOC.word("bonus_loot_amount")
                            .appendText(": " + this.getBonusLootAmount() + "%")), tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(TooltipUtils.rarity(rarity), tooltip);

            if (this.isPermaDeath) {
                Tooltip.add("", tooltip);
                Tooltip.add(Styles.REDCOMP()
                        .appendSibling(CLOC.word("permadeath")), tooltip);
            }

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.BLUECOMP()
                    .appendSibling(CLOC.tooltip("put_in_mapdevice")), tooltip);

        }

    }

    private static void addAffixTypeToTooltip(MapItemData data,
                                              List<ITextComponent> tooltip,
                                              AffectedEntities affected) {

        List<MapAffixData> affixes = data.getAllAffixesThatAffect(affected);

        if (affixes.size() == 0) {
            return;
        }

        ITextComponent str = new TextComponentString("");

        if (affected.equals(AffectedEntities.Players)) {
            str.appendSibling(CLOC.word("player_affixes"));
        } else if (affected.equals(AffectedEntities.Mobs)) {
            str.appendSibling(CLOC.word("mob_affixes"));
        } else {
            str.appendSibling(CLOC.word("all_affixes"));
        }

        Tooltip.add(Styles.GREENCOMP().appendSibling(str), tooltip);

        for (MapAffixData affix : affixes) {

            for (StatModData statmod : affix.getAffix().Stats(affix.percent)) {

                for (ITextComponent statstring : statmod.GetTooltipString(Rarities.Maps.get(data.rarity)
                        .StatPercents(), data.level, false)) {

                    Tooltip.add(new TextComponentString(" * ").appendSibling(statstring), tooltip);

                }

            }

        }
    }
}
