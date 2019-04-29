package com.robertx22.uncommon.utilityclasses;

import com.robertx22.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.INpc;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimal;

public class EntityTypeUtils {

    public static float getLootMulti(Entity en) {

        if (isMob(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.MOB_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        } else if (isNPC(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.NPC_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        } else if (isAnimal(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.ANIMAL_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        } else {
            return ModConfig.INSTANCE.EntityTypeConfig.OTHER_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        }

    }

    public static float getExpMulti(Entity en) {

        if (isMob(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.MOB_CONFIG.EXP_MULTI.get()
                    .floatValue();
        } else if (isNPC(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.NPC_CONFIG.EXP_MULTI.get()
                    .floatValue();
        } else if (isAnimal(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.ANIMAL_CONFIG.EXP_MULTI.get()
                    .floatValue();
        } else {
            return ModConfig.INSTANCE.EntityTypeConfig.OTHER_CONFIG.EXP_MULTI.get()
                    .floatValue();
        }

    }

    public static boolean isMob(Entity en) {

        return en instanceof IMob;

    }

    public static boolean isAnimal(Entity en) {

        return en instanceof IAnimal;

    }

    /**
     * has to be checked first because inpc extends ianimals
     *
     * @param en
     * @return
     */
    public static boolean isNPC(Entity en) {

        return en instanceof INpc;

    }
}
