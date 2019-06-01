package com.robertx22.database.unique_items;

import com.robertx22.database.unique_items.axes.AxeFire;
import com.robertx22.database.unique_items.axes.AxeThunder;
import com.robertx22.database.unique_items.axes.AxeWaterFire;
import com.robertx22.database.unique_items.boots.BootsFire;
import com.robertx22.database.unique_items.boots.BootsNature;
import com.robertx22.database.unique_items.boots.BootsThunder;
import com.robertx22.database.unique_items.boots.BootsWater;
import com.robertx22.database.unique_items.bows.BowFire;
import com.robertx22.database.unique_items.bows.BowNature;
import com.robertx22.database.unique_items.bows.BowThunder;
import com.robertx22.database.unique_items.bows.BowWater;
import com.robertx22.database.unique_items.bracelets.*;
import com.robertx22.database.unique_items.charms.CharmFire;
import com.robertx22.database.unique_items.charms.CharmNature;
import com.robertx22.database.unique_items.charms.CharmThunder;
import com.robertx22.database.unique_items.charms.CharmWater;
import com.robertx22.database.unique_items.chest.*;
import com.robertx22.database.unique_items.hammers.HammerPhysical;
import com.robertx22.database.unique_items.hammers.HammerThunder;
import com.robertx22.database.unique_items.helmet.*;
import com.robertx22.database.unique_items.necklaces.*;
import com.robertx22.database.unique_items.pants.PantsFire;
import com.robertx22.database.unique_items.pants.PantsNature;
import com.robertx22.database.unique_items.pants.PantsThunder;
import com.robertx22.database.unique_items.pants.PantsWater;
import com.robertx22.database.unique_items.rings.RingCrit;
import com.robertx22.database.unique_items.rings.RingDodge;
import com.robertx22.database.unique_items.rings.RingEnergy;
import com.robertx22.database.unique_items.rings.RingWaterFire;
import com.robertx22.database.unique_items.staffs.*;
import com.robertx22.database.unique_items.swords.SwordNature;
import com.robertx22.database.unique_items.swords.SwordPhysical;
import com.robertx22.database.unique_items.swords.SwordWater;
import com.robertx22.db_lists.UniqueItems;
import net.minecraft.item.Item;

public class UniqueItemRegister {

    /**
     * this needs to be called before serialization of config
     */
    public static void register() {

        // bows
        add(new BowNature());
        add(new BowWater());
        add(new BowThunder());
        add(new BowFire());

        // charms
        add(new CharmThunder());
        add(new CharmWater());
        add(new CharmFire());
        add(new CharmNature());

        // pants
        add(new PantsThunder());
        add(new PantsWater());
        add(new PantsNature());
        add(new PantsFire());

        // helmet
        add(new HelmetWater());
        add(new HelmetMana());
        add(new HelmetFire());
        add(new HelmetThunder());
        add(new HelmetNature());

        // chest
        add(new ChestFire());
        add(new ChestWater());
        add(new ChestDodge());
        add(new ChestNature());
        add(new ChestThunder());
        add(new ChestMana());

        // boots
        add(new BootsNature());
        add(new BootsWater());
        add(new BootsFire());
        add(new BootsThunder());

        // hammers
        add(new HammerThunder());
        add(new HammerPhysical());

        // swords
        add(new SwordNature());
        add(new SwordWater());
        add(new SwordPhysical());

        // axes
        add(new AxeWaterFire());
        add(new AxeFire());
        add(new AxeThunder());

        // rings
        add(new RingDodge());
        add(new RingWaterFire());
        add(new RingEnergy());
        add(new RingCrit());

        // bracelets
        add(new BraceletThunder());
        add(new BraceletWater());
        add(new BraceletThunderNature());
        add(new BraceletFire());
        add(new BraceletNature());
        add(new BraceletSetDrop());

        // necklaces
        add(new NecklaceNature());
        add(new NecklaceWater());
        add(new NecklaceFire());
        add(new NecklaceThunder());
        add(new NecklaceEnergy());
        add(new NecklaceSetDrop());

        // staffs
        add(new StaffFire());
        add(new StaffWater());
        add(new StaffThunder());
        add(new StaffNature());
        add(new StaffLifesteal());

        // localization helper

    }

    private static void add(Item item) {

        IUnique uniq = (IUnique) item;
        UniqueItems.ITEMS.put(uniq.GUID(), item);
    }

}
