package com.robertx22.dimensions;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileFilter;

public class WorldFileUtils {

    private static final FileFilter FILE_FILTER_NO_LEVEL = new FileFilter() {
        public boolean accept(File name) {
            return name.getName().equals("level.dat") == false;
        }
    };

    @Nullable
    public static File getWorldDirectory(World world) {
        IChunkProvider chunkProvider = world.getChunkProvider();

        if (chunkProvider instanceof ChunkProviderServer) {
            ChunkProviderServer chunkProviderServer = (ChunkProviderServer) chunkProvider;
            IChunkLoader chunkLoader = chunkProviderServer.chunkLoader;

            if (chunkLoader instanceof AnvilChunkLoader) {
                return ((AnvilChunkLoader) chunkLoader).chunkSaveLocation;
            }

            return null;
        }
        return null;
    }

}
