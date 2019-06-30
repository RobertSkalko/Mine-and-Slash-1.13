package com.robertx22.new_content_test;

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
                .withDisplay(BlockRegister.BLOCK_GEAR_REPAIR, trans("repair.title"), trans("repair.description"), new ResourceLocation("textures/gui/advancements/backgrounds/end.png"), FrameType.TASK, false, false, false)
                .withCriterion("placed_repair", PlacedBlockTrigger.Instance.placedBlock(BlockRegister.BLOCK_GEAR_REPAIR))
                .register(consumerAdv, "/repair");

        Advancement advancement1 = Advancement.Builder.builder()
                .withParent(advancement)
                .withDisplay(BlockRegister.BLOCK_GEAR_SALVAGE, trans("salvage.title"), trans("salvage.description"), (ResourceLocation) null, FrameType.TASK, true, true, false)
                .withCriterion("placed_salvage", PlacedBlockTrigger.Instance.placedBlock(BlockRegister.BLOCK_GEAR_SALVAGE))
                .register(consumerAdv, "/salvage");

    }

    private TranslationTextComponent trans(String str) {
        return new TranslationTextComponent(".advancements.");
    }
}

