package com.robertx22.mmorpg.registers.common;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.ClientContainer;
import com.robertx22.mmorpg.config.StatConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigRegister {

    public static void register() {
	ModLoadingContext ctx = ModLoadingContext.get();
	ctx.registerConfig(Type.CLIENT, ClientContainer.INSTANCE.spec);
	ctx.registerConfig(Type.COMMON, StatConfig.INSTANCE.spec);

    }

    public static void load() {
	loadConfig(ClientContainer.spec,
		FMLPaths.CONFIGDIR.get().resolve(Ref.NAME_NO_SPACE + "-" + ClientContainer.NAME + ".toml"));

	loadConfig(StatConfig.spec,
		FMLPaths.CONFIGDIR.get().resolve(Ref.NAME_NO_SPACE + "-" + StatConfig.NAME + ".toml"));

    }

    private static void loadConfig(ForgeConfigSpec spec, Path path) {

	final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
		.writingMode(WritingMode.REPLACE).build();
	configData.load();
	spec.setConfig(configData);
    }
}
