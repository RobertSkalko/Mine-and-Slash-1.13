package com.robertx22.items.misc;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.localization.Styles;
import com.robertx22.uncommon.localization.Words;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAwakenRuneWord extends Item implements ICurrencyItemEffect {

    @ObjectHolder(Ref.MODID + ":awaken_runeword")
    public static final Item ITEM = null;

    public ItemAwakenRuneWord() {

        super(new Properties().maxStackSize(64).defaultMaxDamage(0));

        RegisterItemUtils.RegisterItemName(this, "awaken_runeword");

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (stack != null && RuneWords.All.containsKey(this.getWord(stack))) {
            Tooltip.add("", tooltip);
            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(Words.Runeword.locName().appendText(": ")), tooltip);
            String word = this.getWord(stack);

            RuneWord runeword = RuneWords.All.get(word);

            Tooltip.add(new StringTextComponent(TextFormatting.GOLD + "").appendSibling(runeword
                    .locName()), tooltip);

            Tooltip.add(new StringTextComponent(TextFormatting.GREEN + runeword.getRuneWordComboString()), tooltip);

            Tooltip.add(TextFormatting.AQUA + "Runes: " + runeword.size(), tooltip);

            Tooltip.add("", tooltip);

        }
        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.Item_modifiable_in_station.locName()), tooltip);
        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.unlocks_runeword_combo.locName()), tooltip);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {

        if (currency.getItem() instanceof ItemAwakenRuneWord) {
            GearItemData gear = Gear.Load(stack);
            if (gear != null) {
                gear.runes.AwakenRuneWord(this.getWord(currency));
                Gear.Save(stack, gear);
            }
        }

        return stack;
    }

    public void setWord(ItemStack stack, RuneWord word) {
        CompoundNBT nbt = stack.getTag();
        if (nbt == null) {
            nbt = new CompoundNBT();
        }
        nbt.putString("runeword", word.GUID());
        stack.setTag(nbt);

    }

    public String getWord(ItemStack stack) {

        if (stack != null && stack.hasTag() && stack.getTag().contains("runeword")) {
            return stack.getTag().getString("runeword");
        }

        return "";

    }

    @Override
    public boolean canItemBeModified(ItemStack item, ItemStack currency) {

        if (currency.getItem() instanceof ItemAwakenRuneWord) {
            GearItemData gear = Gear.Load(item);

            if (gear != null) {

                String wordtext = this.getWord(currency);

                if (RuneWords.All.containsKey(wordtext)) {

                    if (gear.isRuned() && gear.runes.canAwakenRuneWord(RuneWords.All.get(wordtext))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}