package com.robertx22.items.runes.base;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.*;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.saveclasses.rune.RunesData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseRuneItem extends Item implements IWeighted, ICurrencyItemEffect {

    public int rarity;

    @Override
    public int Weight() {
        return 1000;
    }

    public abstract BaseRuneItem byRarity(int rar);

    public BaseRuneItem(int rarity) {

        super(new Properties().maxStackSize(1).defaultMaxDamage(0));
        this.rarity = rarity;

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {
        GearItemData gear = Gear.Load(stack);

        RuneItemData rune = Rune.Load(currency);

        gear.runes.insert(rune, gear);

        Gear.Save(stack, gear);

        return stack;

    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null && gear.isRuned()) {
            if (gear.runes == null) {
                gear.runes = new RunesData();
                Gear.Save(stack, gear);
            }

            RuneItemData rune = Rune.Load(currency);

            if (rune != null) {
                return gear.runes.canFit(gear, rune);
            }
        }

        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        RuneItemData rune = Rune.Load(stack);

        if (rune != null) {

            tooltip.add(TooltipUtils.level(rune.level));

            RuneRarity rar = rune.GetRarity();

            TooltipInfo info = new TooltipInfo(rar.StatPercents(), rune.level);

            if (rune.armor != null) {
                Tooltip.add(Styles.GRAYCOMP()
                        .appendSibling(CLOC.tooltip("stats_on_armor")
                                .appendText(":")), tooltip);
                for (ITextComponent str : rune.armor.GetTooltipString(info)) {
                    Tooltip.add(str, tooltip);
                }
                Tooltip.add("", tooltip);
            }
            if (rune.weapon != null) {

                Tooltip.add(Styles.GRAYCOMP()
                        .appendSibling(CLOC.tooltip("stats_on_weapon")
                                .appendText(":")), tooltip);
                for (ITextComponent str : rune.weapon.GetTooltipString(info)) {
                    Tooltip.add(str, tooltip);
                }
            }
            if (rune.jewerly != null) {

                Tooltip.add("", tooltip);
                Tooltip.add(Styles.GRAYCOMP()
                        .appendSibling(CLOC.tooltip("stats_on_jewerly")
                                .appendText(":")), tooltip);
                for (ITextComponent str : rune.jewerly.GetTooltipString(info)) {
                    Tooltip.add(str, tooltip);
                }
                Tooltip.add("", tooltip);
            }

            Tooltip.add(TooltipUtils.rarity(rune.GetRarity()), tooltip);

        }

    }

    public abstract String name();

    public abstract List<StatMod> weaponStat();

    public abstract List<StatMod> armorStat();

    public abstract List<StatMod> jewerlyStat();

    private List<StatMod> allElements(ElementalStatMod mod) {

        return mod.generateAllPossibleStatVariations()
                .stream()
                .filter(x -> ((ElementalStatMod) x).element.isSingleElement)
                .collect(Collectors.toList());
    }

    public List<StatMod> spellDamageFlats() {
        return allElements(new ElementalSpellDamageFlat());
    }

    public List<StatMod> spellDamageMultis() {
        return allElements(new ElementalSpellDamageMulti());
    }

    public List<StatMod> resistFlats() {
        return allElements(new ElementalResistFlat());
    }

    public List<StatMod> peneFlats() {
        return allElements(new ElementalPeneFlat());
    }

    public List<StatMod> penePercents() {
        return allElements(new ElementalPenePercent());
    }

    public List<StatMod> spellDamagePercents() {
        return allElements(new ElementalSpellDamagePercent());
    }

}
