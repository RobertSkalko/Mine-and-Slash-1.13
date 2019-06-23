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
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FloatingIslandPieces {

    static final String FOLDER = "floating_island";

    private static final ResourceLocation CENTER = new ResourceLocation(Ref.MODID, FOLDER + "/center");
    private static final ResourceLocation SIDE = new ResourceLocation(Ref.MODID, FOLDER + "/side");
    private static final ResourceLocation EDGE = new ResourceLocation(Ref.MODID, FOLDER + "/edge");
    private static final ResourceLocation BOTTOM_SMALL = new ResourceLocation(Ref.MODID, FOLDER + "/small_bottom");
    private static final ResourceLocation BOTTOM_BIG = new ResourceLocation(Ref.MODID, FOLDER + "/big_bottom");
    private static final ResourceLocation STAIRS = new ResourceLocation(Ref.MODID, FOLDER + "/staircase");

    static List<PosRot> sidePosRots = Arrays.asList(new PosRot(new BlockPos(0, 0, 0), Rotation.CLOCKWISE_180), new PosRot(new BlockPos(0, 0, 16), Rotation.NONE), new PosRot(new BlockPos(0, 0, 0), Rotation.CLOCKWISE_90), new PosRot(new BlockPos(0, 0, 0), Rotation.COUNTERCLOCKWISE_90));
    static List<PosRot> edgePosRots = Arrays.asList(new PosRot(new BlockPos(-16, 0, 16), Rotation.NONE), new PosRot(new BlockPos(-16, 0, -16), Rotation.COUNTERCLOCKWISE_90), new PosRot(new BlockPos(16, 0, 16), Rotation.CLOCKWISE_90), new PosRot(new BlockPos(16, 0, -16), Rotation.CLOCKWISE_180));

    static class PosRot {
        public PosRot(BlockPos pos, Rotation rota) {
            this.posOffset = pos;
            this.rotation = rota;
        }

        public BlockPos posOffset;
        public Rotation rotation;

    }

    public static void init(StructurePieceData data, List<StructurePiece> pieces,
                            Random ran) {
        List<PosRot> sidePosRots = Arrays.asList(new PosRot(new BlockPos(0, 0, 0), Rotation.CLOCKWISE_180), new PosRot(new BlockPos(0, 0, 16), Rotation.NONE), new PosRot(new BlockPos(0, 0, 0), Rotation.CLOCKWISE_90), new PosRot(new BlockPos(0, 0, 16), Rotation.COUNTERCLOCKWISE_90));

        data.lowerIntoGroundBy = -30;
        data.height = 0;

        int centerHeight = BasePieces.height(data.templateManager, CENTER);
        int bigBottomHeight = BasePieces.height(data.templateManager, BOTTOM_BIG);

        int totalStairsHeight = Math.abs(data.lowerIntoGroundBy) + bigBottomHeight;
        int stairsHeight = BasePieces.height(data.templateManager, STAIRS);
        int stairs = totalStairsHeight / stairsHeight;

        data.height -= stairsHeight;
        for (int i = 0; i < stairs; i++) {

            data.resource(STAIRS);
            pieces.add(new FloatingIslandPiece(data));
            data.height -= stairsHeight;
        }

        data.height = 0;

        data.resource(CENTER);
        pieces.add(new FloatingIslandPiece(data));
        data.height -= centerHeight;
        data.resource(BOTTOM_BIG);
        pieces.add(new FloatingIslandPiece(data));
        data.height -= bigBottomHeight;



/*
        for (PosRot posrot : sidePosRots) {

            data.height = 0;
            data.blockPos = data.initialPos.add(posrot.posOffset);
            data.rotation = posrot.rotation;

            data.resource(BOTTOM_SMALL);
            pieces.add(new FloatingIslandPiece(data));
            data.height += 16;
            data.resource(SIDE);
            pieces.add(new FloatingIslandPiece(data));
            data.height += 16;

        }
/*
        for (PosRot posrot : edgePosRots) {

            data.height = 0;
            data.blockPos = data.initialPos.add(posrot.posOffset);
            data.rotation = posrot.rotation;

            data.resource(BOTTOM_SMALL);
            pieces.add(new FloatingIslandPiece(data));
            data.height += 16;
            data.resource(EDGE);
            pieces.add(new FloatingIslandPiece(data));
            data.height += 16;

        }

 */

    }

    public static class FloatingIslandPiece extends TemplatePiece {

        public FloatingIslandPiece(StructurePieceData data) {
            super(data);

        }

        @Override
        public List<StructureProcessor> processors() {
            return Arrays.asList(new ChestProcessor(100), new BiomeProcessor(iwp), BlockIgnoreStructureProcessor.AIR);
        }

        public FloatingIslandPiece(TemplateManager templateManager, CompoundNBT nbt) {
            super(StructurePieceRegisters.FLOATING_ISLAND, templateManager, nbt);

        }

    }

}
