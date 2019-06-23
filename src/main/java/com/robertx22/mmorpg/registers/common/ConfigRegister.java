package com.robertx22.mmorpg.registers.common;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.robertx22.config.ClientContainer;
import com.robertx22.config.ModConfig;
import com.robertx22.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.config.dimension_configs.ConfigDimensionsSerialization;
import com.robertx22.mmorpg.Ref;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ConfigRegister {

    // MUST BE CALLED IN MAIN CLASS
    public static void register() {

        ModLoadingContext ctx = ModLoadingContext.get();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ctx.registerConfig(Type.CLIENT, ClientContainer.spec);
        });

        ctx.registerConfig(Type.COMMON, ModConfig.spec);

        ConfigItemsSerialization.INSTANCE.generateIfEmpty();
        ConfigDimensionsSerialization.INSTANCE.generateIfEmpty();

    }

    // MUST BE CALLED IN MAIN CLASS
    public static void load() {

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            loadConfig(ClientContainer.spec, FMLPaths.CONFIGDIR.get()
                    .resolve(Ref.MODID + "-" + "client" + ".toml")); // needs to be modid
        });

        loadConfig(ModConfig.spec, FMLPaths.CONFIGDIR.get()
                .resolve(Ref.MODID + "-" + "common" + ".toml"));

        ConfigItemsSerialization.INSTANCE.load();
        ConfigDimensionsSerialization.INSTANCE.load();

    }

    // MUST BE CALLED IN MAIN CLASS
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
