package com.robertx22.saveclasses;

import com.robertx22.Styles;
import com.robertx22.config.ClientContainer;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.gearitemslots.bases.GearItemSlot.GearSlotType;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.items.unique_items.IUnique;
import com.robertx22.saveclasses.gearitem.*;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.rune.RunesData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

@Storable
public class GearItemData implements ITooltip, ISalvagable {

    @Store
    public boolean isUnique = false;

    @Store
    public String uniqueGUID;

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

    public boolean changesItemStack() {
        return this.isNotFromMyMod == false;
    }

    public ITextComponent name(ItemStack stack) {

        if (isUnique) {
            if (IUnique.ITEMS.containsKey(this.uniqueGUID)) {
                return ((IUnique) IUnique.ITEMS.get(this.uniqueGUID)).locName();
            }
        } else {
            if (gearTypeName.isEmpty()) {

            } else {
                return stack.getDisplayName();
            }
        }

        return new TextComponentString("error");

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
    public SocketsListData sockets;
    @Store
    public InfusionData infusion;
    // Stats

    @Store
    public boolean isSalvagable = true;
    // crafting limits
    @Store
    public int timesLeveledUp = 0;
    //

    public boolean isUpgradable() {
        return this.GetBaseGearType().slotType().equals(GearSlotType.Weapon);
    }

    public boolean isEnchantable() {
        return this.GetBaseGearType().slotType().equals(GearSlotType.Jewerly);
    }

    public Item getItem() {

        if (isUnique) {
            return IUnique.ITEMS.getOrDefault(this.uniqueGUID, null);
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

            for (LevelAndStats stats : container.GetAllStats(1)) {

                for (StatModData mod : stats.mods) {

                    power += mod.percent;

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

        ITextComponent text = new TextComponentString("");

        if (isUnique) {
            IUnique uniq = (IUnique) this.getItem();
            text.appendSibling(uniq.locName());

        } else if (this.isRuned()) {
            text.appendSibling(CLOC.word("runed")
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

        text.setStyle(new net.minecraft.util.text.Style().setColor(this.GetRarity()
                .textFormatColor()));

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
        IfNotNullAdd(sockets, list);
        IfNotNullAdd(infusion, list);
        IfNotNullAdd(runes, list);

        return list;

    }

    public List<LevelAndStats> GetAllStats(int level) {

        List<LevelAndStats> datas = new ArrayList<LevelAndStats>();

        for (IStatsContainer con : GetAllStatContainers()) {
            datas.addAll(con.GetAllStats(this.level));
        }

        return datas;
    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             UnitData data) {

        List<ITextComponent> tip = event.getToolTip();

        tip.clear();

        tip.add(GetDisplayName(stack));

        tip.add(TooltipUtils.level(level));

        tip.add(new TextComponentString(""));

        List<ITooltipList> list = new ArrayList<ITooltipList>();

        if (uniqueStats != null) {
            tip.addAll(uniqueStats.GetTooltipString(this));
        }
        if (primaryStats != null) {
            tip.addAll(primaryStats.GetTooltipString(this));
        }
        if (runes != null) {
            tip.addAll(runes.GetTooltipString(this));
        }

        tip.add(new TextComponentString(""));

        list.add(secondaryStats);
        list.add(prefix);
        list.add(suffix);

        list.add(chaosStats);
        list.add(sockets);
        list.add(infusion);

        for (ITooltipList part : list) {

            if (part != null) {
                tip.addAll(part.GetTooltipString(this));
                tip.add(new TextComponentString(""));
            }

        }

        this.BuildSetTooltip(event, unit, data);

        if (isUnique) {
            IUnique unique = this.uniqueStats.getUniqueItem();

            tip.add(Styles.GREENCOMP()
                    .appendSibling(new TextComponentString("'"))
                    .appendSibling(unique.locDesc())
                    .appendText("'"));

            tip.add(new TextComponentString(""));

            tip.add(Styles.YELLOWCOMP()
                    .appendSibling(CLOC.word("tier"))
                    .appendText(": " + unique.Tier()));

            tip.add(new TextComponentString(""));
        }

        ItemRarity rarity = GetRarity();
        tip.add(TooltipUtils.rarity(rarity));

        if (!this.isSalvagable) {
            tip.add(Styles.REDCOMP().appendSibling(CLOC.word("unsalvagable")));
        }

        if (this.GetBaseGearType() instanceof IWeapon) {
            IWeapon iwep = (IWeapon) this.GetBaseGearType();
            tip.add(new TextComponentString(""));
            tip.add(Styles.GREENCOMP()
                    .appendSibling(CLOC.stat("energy")
                            .appendText(": " + iwep.mechanic().GetEnergyCost())));
            tip.add(new TextComponentString(Styles.GREEN + "[Hit]: ").appendSibling(iwep.mechanic()
                    .tooltipDesc()));
        }

        List<ITextComponent> tool = removeDoubleBlankLines(tip);
        tip.clear();
        tip.addAll(tool);

    }

    private List<ITextComponent> removeDoubleBlankLines(List<ITextComponent> list) {

        List<ITextComponent> newt = new ArrayList();

        String s = "";
        for (int i = 0; i < list.size(); i++) {

            if (i > 0) {

                if (s.equals(list.get(i).toString()) && s.isEmpty()) {

                } else {
                    newt.add(list.get(i));
                }
            } else {
                newt.add(list.get(i));
            }
            s = list.get(i).toString();
        }

        return newt;
    }

    private void BuildSetTooltip(ItemTooltipEvent event, Unit unit, UnitData data) {

        if (this.set != null) {
            event.getToolTip()
                    .add(Styles.GREENCOMP()
                            .appendSibling(new TextComponentString("[Set]: " + set.GetSet()
                                    .Name())));

            for (Entry<Integer, StatMod> entry : set.GetSet().AllMods().entrySet()) {

                boolean has = false;

                TextFormatting color = null;
                if (unit.WornSets.containsKey(set.baseSet) && unit.WornSets.get(set.baseSet) >= entry
                        .getKey()) {
                    color = TextFormatting.GREEN;
                    has = true;
                } else {
                    color = TextFormatting.DARK_GREEN;
                }

                for (ITextComponent str : StatModData.Load(entry.getValue(), set.GetSet().StatPercent)
                        .GetTooltipString(this.GetRarity()
                                .StatPercents(), data.getLevel(), false)) {

                    ITextComponent comp = new TextComponentString(color + "").appendSibling(new TextComponentString(entry
                            .getKey() + " ").appendSibling(CLOC.word("set")
                            .appendText(": ")
                            .appendSibling(str)));

                    event.getToolTip().add(comp);
                }

            }
            event.getToolTip().add(new TextComponentString(""));
        }
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
            int max = 3;

            min = tryIncreaseAmount(salvageBonus, min);
            max = tryIncreaseAmount(salvageBonus, max);

            if (isUnique) {
                try {
                    tier = this.uniqueStats.getUniqueItem().Tier();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (RandomUtils.roll(this.GetRarity().specialItemChance())) {

                Item item = (Item) RandomUtils.WeightedRandom(ListUtils.SameTierOrLess(ListUtils
                        .CollectionToList(CurrencyItem.ITEMS), tier));

                if (isUnique) {
                    int amount = RandomUtils.RandomRange(min, max + (tier / 5));
                    stack.setCount(amount);
                }

                stack = new ItemStack(item);
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

}
