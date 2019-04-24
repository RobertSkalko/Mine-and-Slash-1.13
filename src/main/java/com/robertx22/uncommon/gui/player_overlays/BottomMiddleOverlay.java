package com.robertx22.uncommon.gui.player_overlays;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BottomMiddleOverlay extends BasePlayerOverlay {

    @Override
    public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit,
	    UnitData data) {

	int height = Minecraft.getInstance().mainWindow.getScaledHeight();
	int width = Minecraft.getInstance().mainWindow.getScaledHeight();

	// ENERGY
	int x = width / 2 - this.TEXTURE_WIDTH * 2;
	int y = height - 15;

	this.DrawBar(mc, gui, unit, energytexturepath, data.getCurrentEnergy(), unit.energyData().Value, false, data, x,
		y);

	// ENERGY

	// MANA
	x = width / 2 + this.TEXTURE_WIDTH;
	y = height - 15;
	this.DrawBar(mc, gui, unit, manatexturepath, data.getCurrentMana(), unit.manaData().Value, false, data, x, y);
	// MANA

	// HEALTHs
	x = width / 2 - this.TEXTURE_WIDTH;
	y = height - 53;

	this.DrawBar(mc, gui, unit, healthtexturepath, unit.health().CurrentValue(entity, unit),
		unit.healthData().Value, false, data, x, y);
	// HEALTH

	// EXP
	x = width / 2 + 5;
	y = height - 53;

	this.DrawBar(mc, gui, unit, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), true, data,
		x, y);
	// EXP

    }

}
