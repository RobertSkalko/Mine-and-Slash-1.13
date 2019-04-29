package com.robertx22.mmorpg.registers.common;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.robertx22.config.ClientContainer;
import com.robertx22.config.ModConfig;
import com.robertx22.config.dimensions.ConfigDimensionsSerialization;
import com.robertx22.config.non_mine_items.ConfigItemsSerialization;
import com.robertx22.mmorpg.Ref;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ConfigRegister {

    public static void register() {
        ModLoadingContext ctx = ModLoadingContext.get();
        ctx.registerConfig(Type.CLIENT, ClientContainer.INSTANCE.spec);
        ctx.registerConfig(Type.COMMON, ModConfig.INSTANCE.spec);

        ConfigItemsSerialization.INSTANCE.generateIfEmpty();
        ConfigDimensionsSerialization.INSTANCE.generateIfEmpty();

    }

    public static void load() {
        loadConfig(ClientContainer.spec, FMLPaths.CONFIGDIR.get()
                .resolve(Ref.NAME_NO_SPACE + "-" + ClientContainer.NAME + ".toml"));
        loadConfig(ModConfig.spec, FMLPaths.CONFIGDIR.get()
                .resolve(Ref.NAME_NO_SPACE + "-" + ModConfig.NAME + ".toml"));

        ConfigItemsSerialization.INSTANCE.load();
        ConfigDimensionsSerialization.INSTANCE.load();

    }

    private static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        spec.setConfig(configData);
    }
}
