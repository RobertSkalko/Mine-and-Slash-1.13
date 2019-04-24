package com.robertx22.customitems.gearitems.bases;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.utilityclasses.Tooltip;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class BaseSpellItem extends Item {

    public abstract String GUID();

    public abstract BaseSpell Spell();

    public BaseSpellItem() {
	super(new Properties().maxStackSize(0).defaultMaxDamage(0));

	this.setRegistryName(GUID().toLowerCase());

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
	    ITooltipFlag flagIn) {

	SpellItemData data = Spell.Load(stack);

	if (data != null && data.GetSpell() != null) {

	    BaseSpell spell = data.GetSpell();

	    ItemRarity rarity = Rarities.Items.get(data.rarity);

	    Tooltip.add(TextFormatting.YELLOW + CLOC.word("level") + ": " + data.level, tooltip);
	    Tooltip.add("", tooltip);

	    boolean moreInfo = GuiScreen.isShiftKeyDown();

	    Tooltip.add(TextFormatting.GREEN + CLOC.word("stats") + ": ", tooltip);
	    Tooltip.add(" * " + (TextFormatting.RED + data.GetManaDesc(moreInfo)), tooltip);
	    Tooltip.add(" * " + (TextFormatting.RED + data.GetBaseDesc(moreInfo)), tooltip);

	    if (spell.hasScalingValue()) {
		Tooltip.add(" * " + (TextFormatting.RED + data.GetScalingDesc(moreInfo)), tooltip);
	    }

	    Tooltip.add("", tooltip);

	    Tooltip.add(TextFormatting.AQUA + "Type: " + this.Spell().typeString(), tooltip);

	    Tooltip.add("", tooltip);

	    Tooltip.add(TextFormatting.LIGHT_PURPLE + data.GetSpell().GetDescription(data), tooltip);

	    Tooltip.add("", tooltip);
	    Tooltip.add(rarity.Color() + CLOC.word("rarity") + ": " + rarity.locName(), tooltip);
	}
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

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
