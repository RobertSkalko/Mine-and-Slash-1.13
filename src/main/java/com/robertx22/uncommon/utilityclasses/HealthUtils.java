package com.robertx22.uncommon.utilityclasses;

import com.robertx22.database.stats.stat_types.resources.Health;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class HealthUtils {

    public static float DamageToMinecraftHealth(float dmg, LivingEntity entity) {

        try {
            UnitData data = Load.Unit(entity);
            Unit unit = data.getUnit();

            float maxhp = unit.getStat(new Health()).Value;
            float maxMChp = entity.getMaxHealth();

            return (float) (maxMChp / maxhp * dmg);
        } catch (Exception e) {
        }
        return 0;

    }

}
