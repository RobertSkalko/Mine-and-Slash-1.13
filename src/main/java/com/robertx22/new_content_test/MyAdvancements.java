package com.robertx22.new_content_test;

import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.PlacedBlockTrigger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class MyAdvancements implements Consumer<Consumer<Advancement>> {

    public void accept(Consumer<Advancement> consumerAdv) {
        Advancement advancement = Advancement.Builder.builder()
                .withDisplay(BlockRegister.BLOCK_GEAR_REPAIR, trans("root.title"), trans("repair.description"), new ResourceLocation("textures/gui/advancements/backgrounds/end.png"), FrameType.TASK, false, false, false)
                .withCriterion("placed_repair", PlacedBlockTrigger.Instance.placedBlock(BlockRegister.BLOCK_GEAR_REPAIR))
                .register(consumerAdv, id("root"));

        Advancement advancement1 = Advancement.Builder.builder()
                .withParent(advancement)
                .withDisplay(BlockRegister.BLOCK_GEAR_SALVAGE, trans("salvage.title"), trans("salvage.description"), (ResourceLocation) null, FrameType.TASK, true, true, false)
                .withCriterion("placed_salvage", PlacedBlockTrigger.Instance.placedBlock(BlockRegister.BLOCK_GEAR_SALVAGE))
                .register(consumerAdv, id("salvage"));

    }
    
    private TranslationTextComponent trans(String str) {
        return new TranslationTextComponent("mmorpg.advancements." + str);
    }

    private String id(String str) {
        return Ref.MODID + ":mine_and_slash/" + str;
    }

}

