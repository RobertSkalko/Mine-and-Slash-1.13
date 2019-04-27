package com.robertx22.mmorpg.config;

import org.apache.commons.lang3.tuple.Pair;

import com.robertx22.uncommon.enumclasses.Player_GUIs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ClientContainer {
    public static final String ID = "client";

    public static final ForgeConfigSpec spec;
    public static final ClientContainer INSTANCE;
    static {
	final Pair<ClientContainer, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
		.configure(ClientContainer::new);
	spec = specPair.getRight();
	INSTANCE = specPair.getLeft();

    }

    public BooleanValue RENDER_ITEM_ENTITY_RARITY_PARTICLES;
    public BooleanValue RENDER_CHAT_COMBAT_LOG;
    public BooleanValue SHOW_AFFIXED_NAME;
    public BooleanValue RENDER_FLOATING_DAMAGE;
    public BooleanValue RENDER_MOB_HEALTH_GUI;
    public BooleanValue SHOW_LOW_ENERGY_MANA_WARNING;
    public BooleanValue SHOW_FLOATING_EXP;
    public ConfigValue<Player_GUIs> PLAYER_GUI_TYPE;

    ClientContainer(ForgeConfigSpec.Builder builder) {
	builder.comment("Client Settings").push(ID);

	RENDER_ITEM_ENTITY_RARITY_PARTICLES = builder.comment(".").translation("mmorpg.config.item_on_ground_particles")
		.define("RENDER_ITEM_ENTITY_RARITY_PARTICLES", false);

	SHOW_AFFIXED_NAME = builder

		.comment(".").translation("mmorpg.config.show_item_affixes").define("SHOW_AFFIXED_NAME", false);

	RENDER_CHAT_COMBAT_LOG = builder

		.comment(".").translation("mmorpg.config.chat_combat_log").define("RENDER_CHAT_COMBAT_LOG", false);

	RENDER_FLOATING_DAMAGE = builder

		.comment(".").translation("mmorpg.config.floating_damage_numbers")
		.define("RENDER_FLOATING_DAMAGE", true);

	RENDER_MOB_HEALTH_GUI = builder

		.comment(".").translation("mmorpg.config.mob_health_bar").define("RENDER_MOB_HEALTH_GUI", true);

	SHOW_LOW_ENERGY_MANA_WARNING = builder

		.comment(".").translation("mmorpg.config.low_resource_warnings")
		.define("SHOW_LOW_ENERGY_MANA_WARNING", true);

	SHOW_FLOATING_EXP = builder

		.comment(".").translation("mmorpg.config.floating_exp").define("SHOW_FLOATING_EXP", true);

	PLAYER_GUI_TYPE = builder

		.comment(".").translation("mmorpg.config.player_gui_overlay_type")
		.define("PLAYER_GUI_TYPE", Player_GUIs.Middle);

	builder.pop();
	builder.build();
    }

}
