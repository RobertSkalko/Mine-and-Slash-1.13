package com.robertx22.onevent.data_gen;

import com.robertx22.onevent.data_gen.providers.MyAdvProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class OnGatherData {

    public void onGatherData(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();

        gen.addProvider(new MyAdvProvider(gen));

    }

}

