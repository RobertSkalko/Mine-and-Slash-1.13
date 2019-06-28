package com.robertx22.items.bags.master_bag;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.MasterBagPacket;
import net.minecraft.client.gui.widget.Widget;

public class MasterButton extends Widget {

    ContainerMasterBag.ItemType type;

    public MasterButton(int xIn, int yIn, ContainerMasterBag.ItemType type) {
        super(xIn, yIn, 16, 16, "TEST BUTTON");
        this.type = type;

    }

    @Override
    public void onClick(double x, double y) {
        //  System.out.println("button works!!  " + type.toString());
        MMORPG.sendToServer(new MasterBagPacket(type));

    }

}
