package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class CriteriaRegisters {

    public static PlayerLevelTrigger PLAYER_LEVEL_TRIGGER;

    public static void register() {

        PLAYER_LEVEL_TRIGGER = CriteriaTriggers.register(new PlayerLevelTrigger());

    }

}
