package com.robertx22.world_gen.structures;

import com.robertx22.mmorpg.registers.common.StructurePieceRegisters;
import com.robertx22.world_gen.processors.BiomeProcessor;
import com.robertx22.world_gen.processors.ChestProcessor;
import com.robertx22.world_gen.structures.bases.StructurePieceData;
import com.robertx22.world_gen.structures.bases.TemplatePiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FloatingIslandPieces {

    public static void init(StructurePieceData data, List<StructurePiece> pieces,
                            Random ran) {

        int middleAmount = ran.nextInt(2) + 1;

        for (int x = 0; x < 9; x++) {

            data.height = 0;

            if (x == 0) {
                data.blockPos = data.initialPos.add(16, 0, 0);
            }
            if (x == 1) {
                data.blockPos = data.initialPos.add(32, 0, 0);
            }
            if (x == 2) {
                data.blockPos = data.initialPos.add(0, 0, 16);
            }
            if (x == 3) {
                data.blockPos = data.initialPos.add(0, 0, 32);
            }
            if (x == 4) {
                data.blockPos = data.initialPos.add(16, 0, 16);
            }
            if (x == 5) {
                data.blockPos = data.initialPos.add(16, 0, 32);
            }
            if (x == 6) {
                data.blockPos = data.initialPos.add(32, 0, 16);
            }
            if (x == 7) {
                data.blockPos = data.initialPos.add(32, 0, 32);
            }
            if (x == 8) {
                data.blockPos = data.initialPos.add(0, 0, 32);
            }

        }

    }

    public static class FloatingIslandPiece extends TemplatePiece {

        public FloatingIslandPiece(StructurePieceData data) {
            super(data);

        }

        @Override
        public List<StructureProcessor> processors() {
            return Arrays.asList(new ChestProcessor(30), new BiomeProcessor(iwp));
        }

        public FloatingIslandPiece(TemplateManager templateManager, CompoundNBT nbt) {
            super(StructurePieceRegisters.TOWER, templateManager, nbt);

        }

    }

}
