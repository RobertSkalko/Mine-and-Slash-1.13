package com.robertx22.mmorpg.registers.client;

import com.robertx22.uncommon.gui.StatGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnKeyPress {

    // they said i should use clienttickevent but idk how
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        int key = event.getKey();

        PlayerEntity player = Minecraft.getInstance().player;

        if (Minecraft.getInstance().ingameGUI == null) { // TODO UNSURE

            if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {

                Minecraft.getInstance().displayGuiScreen(new StatGUI());
            }
        }

    }
}
