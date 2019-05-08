package com.robertx22.items.bags.loot_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.datasaving.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

public class ItemLootBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":loot_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemLootBag() {
        super(ID);

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    @Nonnull EnumHand hand) {
        if (!world.isRemote) {
            NetworkHooks.openGui((EntityPlayerMP) player, new InteractLootBag(player.getHeldItem(hand)), buf -> {
                buf.writeBoolean(hand == EnumHand.OFF_HAND);
            });
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public boolean IsValidItem(ItemStack stack) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null) {
            return true;
        }

        SpellItemData spell = Spell.Load(stack);

        if (spell != null) {
            return true;
        }

        RuneItemData rune = Rune.Load(stack);
        if (rune != null) {
            return true;

        }
        return false;
    }

}
