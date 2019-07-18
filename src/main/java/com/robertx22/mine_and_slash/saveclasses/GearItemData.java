package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Energy;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.initializers.GearTypes;
import com.robertx22.mine_and_slash.db_lists.initializers.UniqueItems;
import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.gearitem.*;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.*;
import com.robertx22.mine_and_slash.saveclasses.rune.RunesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.interfaces.ISalvagable;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ListUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class GearItemData implements ITooltip, ISalvagable {

    @Store
    public boolean isUnique = false;

    @Store
    public String uniqueGUID = "";

    @Store
    public int Rarity;

    @Store
    public boolean isNotFromMyMod = false;

    @Store
    public String gearTypeName = "";

    @Store
    public RunesData runes;

    public boolean isRuned() {
        return runes != null;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public boolean changesItemStack() {
        return this.isNotFromMyMod == false;
    }

    public ITextComponent name(ItemStack stack) {

        return stack.getDisplayName();

    }

    @Store
    public int level;

    // Stats
    @Store
    public UniqueStatsData uniqueStats;
    @Store
    public PrimaryStatsData primaryStats;
    @Store
    public SecondaryStatsData secondaryStats;
    @Store
    public SuffixData suffix;
    @Store
    public PrefixData prefix;
    @Store
    public SetData set;
    @Store
    public ChaosStatsData chaosStats;
    @Store
    public InfusionData infusion;
    // Stats

    @Store
    public boolean isSalvagable = true;
    // crafting limits
    @Store
    public int timesLeveledUp = 0;
    //

    // used when upgrading item rarity
    public Item getItem() {

        if (isUnique) {
            return UniqueItems.ITEMS.getOrDefault(this.uniqueGUID, null);
        } else {
            if (gearTypeName.isEmpty()) {
                return Items.AIR;
            } else {
                return GearTypes.All.get(gearTypeName)
                        .GetItemForRarity(GetRarity().Rank());
            }
        }

    }

    public void WriteOverDataThatShouldStay(GearItemData newdata) {

        newdata.timesLeveledUp = this.timesLeveledUp;
        newdata.isSalvagable = this.isSalvagable;

    }

    public GearItemSlot GetBaseGearType() {

        return GearTypes.All.get(gearTypeName);
    }

    public int getPowerLevel() {

        int power = 0;

        for (IStatsContainer container : this.GetAllStatContainers()) {
            for (IStatsContainer.LevelAndStats stats : container.GetAllStats(1)) {
                for (StatModData mod : stats.mods) {
                    power += mod.getPercent();
                }
            }
        }

        return power;

    }

    public ItemRarity GetRarity() {
        if (isUnique) {
            return new UniqueItem();
        } else {
            return Rarities.Items.get(Rarity);
        }
    }

    public ITextComponent GetDisplayName(ItemStack stack) {

        ITextComponent text = new StringTextComponent(this.GetRarity()
                .textFormatColor() + "");

        if (this.isRuned()) {
            text.appendSibling(Words.Runed.locName()
                    .appendText(" ")
                    .appendSibling(name(stack)));
        } else {

            if (prefix != null && ClientContainer.INSTANCE.SHOW_AFFIXED_NAME.get()) {
                text.appendSibling(prefix.BaseAffix().locName().appendText(" "));
            }
            text.appendSibling(name(stack));

            if (suffix != null && ClientContainer.INSTANCE.SHOW_AFFIXED_NAME.get()) {
                text.appendText(" ")
                        .appendSibling(suffix.BaseAffix().locName())
                        .appendText(" ");
            }

        }

        return text;

    }

    public List<IStatsContainer> GetAllStatContainers() {

        List<IStatsContainer> list = new ArrayList<IStatsContainer>();

        IfNotNullAdd(secondaryStats, list);
        IfNotNullAdd(primaryStats, list);
        IfNotNullAdd(prefix, list);
        IfNotNullAdd(suffix, list);
        IfNotNullAdd(chaosStats, list);
        IfNotNullAdd(uniqueStats, list);
        IfNotNullAdd(infusion, list);
        IfNotNullAdd(runes, list);

        return list;

    }

    public List<IStatsContainer.LevelAndStats> GetAllStats(int level) {

        List<IStatsContainer.LevelAndStats> datas = new ArrayList<IStatsContainer.LevelAndStats>();

        for (IStatsContainer con : GetAllStatContainers()) {
            datas.addAll(con.GetAllStats(this.level));
        }

        return datas;
    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             UnitData data) {

        List<ITextComponent> tip = event.getToolTip();

        TooltipInfo info = new TooltipInfo(data, GetRarity().StatPercents(), this.level);

        tip.clear();

        tip.add(GetDisplayName(stack));

        tip.add(TooltipUtils.level(level));

        tip.add(new StringTextComponent(""));

        List<ITooltipList> list = new ArrayList<ITooltipList>();

        if (uniqueStats != null) {
            tip.addAll(uniqueStats.GetTooltipString(info));
        }
        if (primaryStats != null) {
            tip.addAll(primaryStats.GetTooltipString(info));
        }
        if (runes != null) {
            tip.addAll(runes.GetTooltipString(info));
        }

        tip.add(new StringTextComponent(""));

        list.add(secondaryStats);
        list.add(prefix);
        list.add(suffix);

        list.add(chaosStats);
        list.add(infusion);
        list.add(set);

        for (ITooltipList part : list) {

            if (part != null) {
                tip.addAll(part.GetTooltipString(info));
                tip.add(new StringTextComponent(""));
            }

        }

        if (isUnique) {
            IUnique unique = this.uniqueStats.getUniqueItem();

            tip.add(Styles.GREENCOMP()
                    .appendSibling(new StringTextComponent("'"))
                    .appendSibling(unique.locDesc())
                    .appendText("'"));

            tip.add(new StringTextComponent(""));

            tip.add(TooltipUtils.tier(unique.Tier()));

            tip.add(new StringTextComponent(""));
        }

        ItemRarity rarity = GetRarity();
        tip.add(TooltipUtils.rarity(rarity));

        if (!this.isSalvagable) {
            tip.add(Styles.REDCOMP().appendSibling(Words.Unsalvagable.locName()));
        }

        if (this.GetBaseGearType() instanceof IWeapon) {
            IWeapon iwep = (IWeapon) this.GetBaseGearType();
            tip.add(new StringTextComponent(""));
            tip.add(Styles.GREENCOMP()
                    .appendSibling(new Energy().locName()
                            .appendText(": " + iwep.mechanic().GetEnergyCost())));
            tip.add(new StringTextComponent(Styles.GREEN + "[Hit]: ").appendSibling(iwep.mechanic()
                    .tooltipDesc()));
        }

        List<ITextComponent> tool = TooltipUtils.removeDoubleBlankLines(tip, 25);
        tip.clear();
        tip.addAll(tool);

    }

    public List<IRerollable> GetAllRerollable() {
        List<IRerollable> list = new ArrayList<IRerollable>();
        IfNotNullAdd(secondaryStats, list);
        IfNotNullAdd(primaryStats, list);
        IfNotNullAdd(prefix, list);
        IfNotNullAdd(suffix, list);
        IfNotNullAdd(chaosStats, list);
        IfNotNullAdd(uniqueStats, list);
        return list;
    }

    private <T> void IfNotNullAdd(T obj, List<T> list) {
        if (obj != null) {
            list.add(obj);
        }
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        if (this.isSalvagable) { // problems with issalvagable?
            ItemStack stack = ItemStack.EMPTY;
            int tier = 0;

            int min = 1;
            int max = 2;

            min = tryIncreaseAmount(salvageBonus, min);
            max = tryIncreaseAmount(salvageBonus, max);

            if (isUnique) {
                try {
                    tier = this.uniqueStats.getUniqueItem().Tier();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (isUnique || RandomUtils.roll(this.GetRarity().specialItemChance())) {

                Item item = RandomUtils.weightedRandom(ListUtils.minMaxTier(CurrencyItem.ITEMS, tier - 5, tier + 2));

                int tierAmountBonus = (tier / 4);

                int amount = RandomUtils.RandomRange(min + tierAmountBonus, max + tierAmountBonus);
                stack = new ItemStack(item);
                stack.setCount(amount);

                return stack;

            } else {

                int amount = RandomUtils.RandomRange(min, max);

                ItemOre ore = (ItemOre) ItemOre.ItemOres.get(Rarity);
                stack = new ItemStack(ore);
                stack.setCount(amount);

            }

            return stack;
        } else
            return ItemStack.EMPTY;

    }

    @Override
    public int getSalvagedRarity() {
        return this.Rarity;
    }

    @Override
    public boolean isSalvagable() {
        return this.isSalvagable;
    }

    public boolean canGetSet() {

        if (this.isUnique() == false && this.isRuned() == false) {
            return true;
        }

        return false;
    }
}
