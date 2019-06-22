package com.robertx22.onevent;

import com.robertx22.mmorpg.Ref;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class OnModel {

    @SubscribeEvent
    public static void bake(ModelBakeEvent event) {

        List<ResourceLocation> test = event.getModelRegistry()
                .keySet()
                .stream()
                .filter(x -> x.getNamespace().equals(Ref.MODID) && x.getPath()
                        .contains("spell"))
                .collect(Collectors.toList());

        List<IBakedModel> models = new ArrayList<>();
        test.stream().forEach(x -> models.add(event.getModelRegistry().get(x)));

        for (ResourceLocation loc : test) {

            SimpleBakedModel model = (SimpleBakedModel) event.getModelRegistry().get(loc);
            InGame3DWhile2DInvModel mymodel = new InGame3DWhile2DInvModel(model);
            event.getModelRegistry().put(loc, mymodel);

        }

        System.out.println(" it works !");

    }

}


