package com.robertx22.world_gen.structures;

import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.StructurePieceRegisters;
import com.robertx22.world_gen.processors.BiomeProcessor;
import com.robertx22.world_gen.processors.ChestProcessor;
import com.robertx22.world_gen.structures.bases.BasePieces;
import com.robertx22.world_gen.structures.bases.StructurePieceData;
import com.robertx22.world_gen.structures.bases.TemplatePiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dungeon0Pieces {

    static final String FOLDER = "one_chunk";

    private static final ResourceLocation DUNGEON = new ResourceLocation(Ref.MODID, FOLDER + "/dungeon0");

    public static void init(StructurePieceData data, List<StructurePiece> pieces,
                            Random ran) {

        data.lowerIntoGroundBy = BasePieces.height(data.templateManager, DUNGEON) - 9;
        data.height = 0;

        data.resource(DUNGEON);
        pieces.add(new Dungeon0Piece(data));

    }

    public static class Dungeon0Piece extends TemplatePiece {

        public Dungeon0Piece(StructurePieceData data) {
            super(data);

        }

        @Override
        public List<StructureProcessor> processors() {
            return Arrays.asList(new ChestProcessor(25), new BiomeProcessor(iwp));
        }

        public Dungeon0Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(StructurePieceRegisters.DUNGEON_0, templateManager, nbt);

        }

    }

}
