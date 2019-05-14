package com.robertx22.api;

import com.robertx22.api.msg_types.MineAndSlashMSG;
import com.robertx22.mmorpg.Ref;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

public class MineAndSlashAPI {

    public static void sendMSG(MineAndSlashMSG MSG, InterModEnqueueEvent event) {
        InterModComms.sendTo(Ref.MODID, "register", () -> MSG);
    }
}
