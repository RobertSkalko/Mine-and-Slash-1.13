package com.robertx22.api;

import com.robertx22.api.msg_types.MineAndSlashMSG;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public class DatabaseIMCProcess {
    public static void proc(InterModProcessEvent event) {

        event.getIMCStream()
                .filter(x -> x.getMessageSupplier().get() instanceof MineAndSlashMSG)
                .map(x -> (MineAndSlashMSG) x.getMessageSupplier().get())
                .forEach(DatabaseIMCProcess::process);

    }

    private static void process(MineAndSlashMSG MSG) {

        System.out.println("Registering: " + MSG.toString());
        MSG.register();

    }

}
