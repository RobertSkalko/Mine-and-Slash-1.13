package com.robertx22.items.gearitems.bases;

import com.robertx22.Styles;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseSpellItem extends Item {

    public abstract String GUID();

    public abstract BaseSpell Spell();

    public BaseSpellItem() {
        super(new Properties().maxStackSize(0).defaultMaxDamage(0));

        this.setRegistryName(GUID().toLowerCase());

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        SpellItemData data = Spell.Load(stack);

        if (data != null && data.GetSpell() != null) {

            BaseSpell spell = data.GetSpell();

            ItemRarity rarity = Rarities.Items.get(data.rarity);

            tooltip.add(TooltipUtils.level(data.level));
            Tooltip.add("", tooltip);

            boolean moreInfo = GuiScreen.isShiftKeyDown();

            Tooltip.add(CLOC.word("stats")
                    .appendText(": ")
                    .setStyle(Styles.GREEN), tooltip);

            Tooltip.add(new TextComponentString(" * ").appendSibling(data.GetManaDesc(moreInfo))
                    .setStyle(Styles.RED), tooltip);

            Tooltip.add(new TextComponentString(" * ").appendSibling(data.GetBaseDesc(moreInfo))
                    .setStyle(Styles.RED), tooltip);

            if (spell.hasScalingValue()) {
                Tooltip.add(new TextComponentString(" * ").appendSibling(data.GetScalingDesc(moreInfo)
                        .setStyle(Styles.RED)), tooltip);
            }

            Tooltip.add("", tooltip);

            Tooltip.add(CLOC.word("type")
                    .appendText(": ")
                    .appendText(this.Spell().typeString())
                    .setStyle(Styles.AQUA), tooltip);

            Tooltip.add("", tooltip);

            Tooltip.add(data.GetSpell()
                    .GetDescription(data)
                    .setStyle(Styles.LIGHT_PURPLE), tooltip);

            Tooltip.add("", tooltip);

            tooltip.add(TooltipUtils.rarity(rarity));

        }
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                    EnumHand handIn) {

        if (worldIn.isRemote) {
            this.Spell().cast(worldIn, playerIn, handIn, 5, null);
        } else {

            try {
                SpellItemData data = Spell.Load(playerIn.getHeldItem(handIn));

                if (data != null) {

                    if (Spell().CanCast(playerIn, data)) {
                        Spell().cast(worldIn, playerIn, handIn, 5, data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
