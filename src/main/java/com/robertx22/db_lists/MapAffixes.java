package com.robertx22.db_lists;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.map_affixes.detrimental.*;
import com.robertx22.database.map_affixes.detrimental.ele.LessAllFireDmgAffix;
import com.robertx22.database.map_affixes.detrimental.ele.LessAllNatureDmgAffix;
import com.robertx22.database.map_affixes.detrimental.ele.LessAllThunderDmgAffix;
import com.robertx22.database.map_affixes.detrimental.ele.LessAllWaterDmgAffix;
import com.robertx22.database.map_affixes.detrimental.weapon.*;

import java.util.HashMap;

public class MapAffixes {
    public static HashMap<String, BaseMapAffix> All = new HashMap<String, BaseMapAffix>() {
        {
            {

	    	/*
		put(new BonusHealthAffix().GUID(), new BonusHealthAffix());
		put(new BonusLifestealAffix().GUID(), new BonusLifestealAffix());

		put(new BonusFireDamageAffix().GUID(), new BonusFireDamageAffix());
		put(new BonusNatureDamageAffix().GUID(), new BonusNatureDamageAffix());
		put(new BonusThunderDamageAffix().GUID(), new BonusThunderDamageAffix());
		put(new BonusWaterDamageAffix().GUID(), new BonusWaterDamageAffix());

		put(new BonusFireResistAffix().GUID(), new BonusFireResistAffix());
		put(new BonusNatureResistAffix().GUID(), new BonusNatureResistAffix());
		put(new BonusThunderResistAffix().GUID(), new BonusThunderResistAffix());
		put(new BonusWaterResistAffix().GUID(), new BonusWaterResistAffix());

	    	 */

                put(new LessAllFireDmgAffix().GUID(), new LessAllFireDmgAffix());
                put(new LessAllNatureDmgAffix().GUID(), new LessAllNatureDmgAffix());
                put(new LessAllThunderDmgAffix().GUID(), new LessAllThunderDmgAffix());
                put(new LessAllWaterDmgAffix().GUID(), new LessAllWaterDmgAffix());

                put(new LessDodgeAffix().GUID(), new LessDodgeAffix());
                put(new LessCriticalHitAffix().GUID(), new LessCriticalHitAffix());

                // resources
                put(new LessEnergyRegenAffix().GUID(), new LessEnergyRegenAffix());
                put(new LessManaRegenAffix().GUID(), new LessManaRegenAffix());
                put(new LessHealthRegenAffix().GUID(), new LessHealthRegenAffix());
                put(new LessLifeOnHitAffix().GUID(), new LessLifeOnHitAffix());
                put(new LessLifestealAffix().GUID(), new LessLifestealAffix());
                put(new LessHealthAffix().GUID(), new LessHealthAffix());
                put(new LessManaOnHitAffix().GUID(), new LessManaOnHitAffix());

                // weapon damages
                put(new LessHammerDamageAffix().GUID(), new LessHammerDamageAffix());
                put(new LessSwordDamageAffix().GUID(), new LessSwordDamageAffix());
                put(new LessBowDamageAffix().GUID(), new LessBowDamageAffix());
                put(new LessAxeDamageAffix().GUID(), new LessAxeDamageAffix());
                put(new LessStaffDamageAffix().GUID(), new LessStaffDamageAffix());

            }
        }
    };
}
