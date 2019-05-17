package com.robertx22.onevent.Item;

import com.robertx22.Styles;
import com.robertx22.config.non_mine_items.ConfigItems;
import com.robertx22.items.gearitems.offhands.IEffectItem;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.datasaving.Spell;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(Dist.CLIENT)
public class OnTooltip {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void showCompatibleItem(ItemTooltipEvent event) {

        ItemStack stack = event.getItemStack();

        if (stack.getItem().getRegistryName() != null) {
            if (ConfigItems.INSTANCE.map.containsKey(stack.getItem()
                    .getRegistryName()
                    .toString())) {

                event.getToolTip()
                        .add(new TextComponentString(Styles.RED + "Compatible Mine and Slash Item"));

            }
        }

    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onItemTooltip(ItemTooltipEvent event) {

        if (GuiScreen.isCtrlKeyDown()) {
            return;
        }

        if (event.getEntityPlayer() == null || event.getEntityPlayer().world == null || !event
                .getEntityPlayer().world.isRemote) {
            return;
        }

        ItemStack stack;

        stack = event.getItemStack();

        if (!stack.hasTag()) {

            return;
        }

        UnitData unitdata = Load.Unit(event.getEntityPlayer());

        if (unitdata == null) {
            return;
        }

        Unit unit = unitdata.getUnit();

        if (unit == null) {
            return;
        }

        GearItemData gear = Gear.Load(stack);

        if (gear != null) {

            gear.BuildTooltip(stack, event, unit, unitdata);

            if (GuiScreen.isShiftKeyDown() == false) {
                event.getToolTip()
                        .add(Styles.BLUECOMP()
                                .appendSibling(CLOC.tooltip("press_shift_more_info")));
            } else {
                event.getToolTip()
                        .add(Styles.GOLDCOMP()
                                .appendSibling(new TextComponentString("Power LeveL: " + gear
                                        .getPowerLevel())));
            }
        } else {
            SpellItemData data = Spell.Load(stack);

            if (data != null) {
                data.BuildTooltip(stack, event, unit, unitdata);
            } else {
                MapItemData map = Map.Load(stack);
                if (map != null) {

                    map.BuildTooltip(stack, event, unit, unitdata);

                    event.getToolTip().add(new TextComponentString(""));
                    event.getToolTip()
                            .add(new TextComponentString(TextFormatting.GOLD + "").appendSibling(CLOC
                                    .tooltip("affix_rarity_lootbonus"))
                                    .appendText(": " + unitdata.getLootBonusPerAffixKills(map) + "%"));

                }
            }

        }

        if (stack.getItem() instanceof IEffectItem) {
            IEffectItem effect = (IEffectItem) stack.getItem();
            event.getToolTip()
                    .addAll(effect.getEffectTooltip(GuiScreen.isShiftKeyDown()));
        }

    }

}
