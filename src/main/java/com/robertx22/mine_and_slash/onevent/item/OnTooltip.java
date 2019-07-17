package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.config.compatible_items.ConfigItems;
import com.robertx22.mine_and_slash.items.gearitems.offhands.IEffectItem;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.saveclasses.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.SpellItemData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnTooltip {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemTooltip(ItemTooltipEvent event) {

        if (Screen.hasControlDown()) {
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

            if (Screen.hasShiftDown() == false) {
                event.getToolTip()
                        .add(Styles.BLUECOMP()
                                .appendSibling(CLOC.tooltip("press_shift_more_info")));
            } else {
                event.getToolTip()
                        .add(Styles.GOLDCOMP()
                                .appendSibling(new StringTextComponent("Power Level: " + gear
                                        .getPowerLevel())));
                event.getToolTip()
                        .add(Styles.BLUECOMP()
                                .appendSibling(new StringTextComponent("[Alt]: Show Detailed Stat Descriptions")));

            }
        } else {
            SpellItemData data = Spell.Load(stack);

            if (data != null) {
                data.BuildTooltip(stack, event, unit, unitdata);
            } else {
                MapItemData map = Map.Load(stack);
                if (map != null) {

                    map.BuildTooltip(stack, event, unit, unitdata);

                }
            }

        }

        if (stack.getItem() instanceof IEffectItem) {
            IEffectItem effect = (IEffectItem) stack.getItem();
            event.getToolTip().addAll(effect.getEffectTooltip(Screen.hasShiftDown()));
        }

        if (gear == null && stack.getItem().getRegistryName() != null) {
            if (ConfigItems.INSTANCE.map.containsKey(stack.getItem()
                    .getRegistryName()
                    .toString())) {

                event.getToolTip()
                        .add(new StringTextComponent(Styles.RED + "Compatible Mine and Slash Item"));

            }
        }

    }

}