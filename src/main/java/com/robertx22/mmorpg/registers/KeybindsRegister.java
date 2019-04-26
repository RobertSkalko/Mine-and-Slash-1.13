package com.robertx22.mmorpg.registers;

import org.lwjgl.glfw.GLFW;

import com.robertx22.mmorpg.Ref;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeybindsRegister {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stats", GLFW.GLFW_KEY_P, Ref.NAME);

    public static KeyBinding Map_Stats = new KeyBinding("Current Map Stats", GLFW.GLFW_KEY_F7, Ref.NAME);

    public static KeyBinding Map_Drops = new KeyBinding("What Unique Items Current Map can drop", GLFW.GLFW_KEY_F6,
	    Ref.NAME);

    public static void register() {
	ClientRegistry.registerKeyBinding(Player_Stats);
	ClientRegistry.registerKeyBinding(Map_Stats);
	ClientRegistry.registerKeyBinding(Map_Drops);
    }
}
