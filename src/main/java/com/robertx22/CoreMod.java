package com.robertx22;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Collection;

public class CoreMod {

    public static Iterable<WorldServer> getWorlds(MinecraftServer server) {

        System.out.println("IT WORKS");

        return new ArrayList<WorldServer>((Collection<? extends WorldServer>) server.getWorlds());

    }
}
