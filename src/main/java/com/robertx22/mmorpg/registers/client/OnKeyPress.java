package com.robertx22.mmorpg.registers.client;

import com.robertx22.StatGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnKeyPress {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        int key = event.getKey();

        EntityPlayer player = Minecraft.getInstance().player;

        if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {

            Minecraft.getInstance().displayGuiScreen(new StatGUI());

        }

    }
}
