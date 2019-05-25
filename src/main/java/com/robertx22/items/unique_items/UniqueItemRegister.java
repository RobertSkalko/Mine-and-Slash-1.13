package com.robertx22.items.unique_items;

import com.robertx22.items.unique_items.axes.AxeFire;
import com.robertx22.items.unique_items.axes.AxeThunder;
import com.robertx22.items.unique_items.axes.AxeWaterFire;
import com.robertx22.items.unique_items.boots.BootsFire;
import com.robertx22.items.unique_items.boots.BootsNature;
import com.robertx22.items.unique_items.boots.BootsThunder;
import com.robertx22.items.unique_items.boots.BootsWater;
import com.robertx22.items.unique_items.bows.BowFire;
import com.robertx22.items.unique_items.bows.BowNature;
import com.robertx22.items.unique_items.bows.BowThunder;
import com.robertx22.items.unique_items.bows.BowWater;
import com.robertx22.items.unique_items.bracelets.*;
import com.robertx22.items.unique_items.charms.CharmFire;
import com.robertx22.items.unique_items.charms.CharmNature;
import com.robertx22.items.unique_items.charms.CharmThunder;
import com.robertx22.items.unique_items.charms.CharmWater;
import com.robertx22.items.unique_items.chest.*;
import com.robertx22.items.unique_items.hammers.HammerPhysical;
import com.robertx22.items.unique_items.hammers.HammerThunder;
import com.robertx22.items.unique_items.helmet.*;
import com.robertx22.items.unique_items.necklaces.*;
import com.robertx22.items.unique_items.pants.PantsFire;
import com.robertx22.items.unique_items.pants.PantsNature;
import com.robertx22.items.unique_items.pants.PantsThunder;
import com.robertx22.items.unique_items.pants.PantsWater;
import com.robertx22.items.unique_items.rings.RingCrit;
import com.robertx22.items.unique_items.rings.RingDodge;
import com.robertx22.items.unique_items.rings.RingEnergy;
import com.robertx22.items.unique_items.rings.RingWaterFire;
import com.robertx22.items.unique_items.staffs.*;
import com.robertx22.items.unique_items.swords.SwordNature;
import com.robertx22.items.unique_items.swords.SwordPhysical;
import com.robertx22.items.unique_items.swords.SwordWater;

public class UniqueItemRegister {

    /**
     * this needs to be called before serialization of config
     */
    public static void register() {

        // bows
        new BowNature();
        new BowWater();
        new BowThunder();
        new BowFire();

        // charms
        new CharmThunder();
        new CharmWater();
        new CharmFire();
        new CharmNature();

        // pants
        new PantsThunder();
        new PantsWater();
        new PantsNature();
        new PantsFire();

        // helmet
        new HelmetWater();
        new HelmetMana();
        new HelmetFire();
        new HelmetThunder();
        new HelmetNature();

        // chest
        new ChestFire();
        new ChestWater();
        new ChestDodge();
        new ChestNature();
        new ChestThunder();
        new ChestMana();

        // boots
        new BootsNature();
        new BootsWater();
        new BootsFire();
        new BootsThunder();

        // hammers
        new HammerThunder();
        new HammerPhysical();

        // swords
        new SwordNature();
        new SwordWater();
        new SwordPhysical();

        // axes
        new AxeWaterFire();
        new AxeFire();
        new AxeThunder();

        // rings
        new RingDodge();
        new RingWaterFire();
        new RingEnergy();
        new RingCrit();

        // bracelets
        new BraceletThunder();
        new BraceletWater();
        new BraceletThunderNature();
        new BraceletFire();
        new BraceletNature();

        // necklaces
        new NecklaceNature();
        new NecklaceWater();
        new NecklaceFire();
        new NecklaceThunder();
        new NecklaceEnergy();

        // staffs
        new StaffFire();
        new StaffWater();
        new StaffThunder();
        new StaffNature();
        new StaffLifesteal();

        // localization helper

    }

}
