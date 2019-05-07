package com.robertx22.onevent.Item;

import com.robertx22.Styles;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.Map;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber
public class OnTooltip {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {

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
        Unit unit = null;
        if (unitdata != null) {
            unit = unitdata.getUnit();
        }
        if (GuiScreen.isCtrlKeyDown() == false) {

            GearItemData gear = Gear.Load(stack);

            if (unit != null && gear != null) {

                gear.BuildTooltip(stack, event, unit, Load.Unit(event.getEntityPlayer()));

                if (GuiScreen.isShiftKeyDown() == false) {

                    event.getToolTip()
                            .add(CLOC.tooltip("press_shift_more_info")
                                    .setStyle(Styles.BLUE));
                } else {

                    event.getToolTip()
                            .add(new TextComponentString("Power LeveL: " + gear.getPowerLevel())
                                    .setStyle(Styles.GOLD));

                }

            }
        }

        if (unitdata != null) {
            MapItemData map = Map.Load(stack);
            if (map != null) {
                event.getToolTip().add(new TextComponentString(""));
                event.getToolTip()
                        .add(new TextComponentString(TextFormatting.GOLD + "").appendSibling(CLOC
                                .tooltip("affix_rarity_lootbonus"))
                                .appendText(": " + unitdata.getLootBonusPerAffixKills(map) + "%"));

            }
        }

    }

}
