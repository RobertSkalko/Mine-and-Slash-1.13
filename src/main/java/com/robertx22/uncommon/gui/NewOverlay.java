package com.robertx22.uncommon.gui;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityCap;
import com.robertx22.uncommon.gui.player_overlays.BasePlayerOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class NewOverlay extends BasePlayerOverlay {

    int xPos = 50;
    int yPos = 10;

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity,
                     RenderGameOverlayEvent event, Unit unit, EntityCap.UnitData data) {
        yPos = 20;
        xPos = 40;

        drawHealthBar(mc, gui, unit, newhealthbar, unit.health()
                .CurrentValue(mc.player, unit), unit.healthData().Value, false, data, xPos, yPos);
        yPos += 12;

    }

}
