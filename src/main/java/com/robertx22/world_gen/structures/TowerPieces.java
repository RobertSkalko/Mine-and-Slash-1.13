package com.robertx22.world_gen.structures;

import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class TowerPieces {
    private static final ResourceLocation TOP_LOC = new ResourceLocation("igloo/top");
    private static final ResourceLocation MIDDLE_LOC = new ResourceLocation("igloo/middle");
    private static final ResourceLocation BOTTOM_LOC = new ResourceLocation("igloo/bottom");

    static int MIDDLE_HEIGHT = 16;

    public static void init(TemplateManager manager, BlockPos pos, Rotation rotation,
                            List<StructurePiece> pieces, Random ran,
                            NoFeatureConfig config) {

        int middleAmount = ran.nextInt(5);
        int height = 0;

        int num = 0;

        pieces.add(new Piece(manager, BOTTOM_LOC, pos, rotation, height, num++));

        for (int i = 0; i < middleAmount - 1; ++i) {
            height += MIDDLE_HEIGHT;
            pieces.add(new Piece(manager, MIDDLE_LOC, pos, rotation, height, num++));
        }

        height += MIDDLE_HEIGHT;
        pieces.add(new Piece(manager, TOP_LOC, pos, rotation, height, num++));

    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;
        public int number = 0;

        int getHeight() {
            return number + MIDDLE_HEIGHT;
        }

        BlockPos getPos() {
            return new BlockPos(0, getHeight(), 0);
        }

        public Piece(TemplateManager templateManager, ResourceLocation resourceLocation,
                     BlockPos blockPos, Rotation rotation, int heightDifference,
                     int number) {
            super(IStructurePieceType.IGLU, 0);
            this.resourceLocation = resourceLocation;
            this.number = number;
            BlockPos pos = getPos();
            this.templatePosition = blockPos.add(pos.getX(), pos.getY() - heightDifference, pos
                    .getZ());
            this.rotation = rotation;
            this.setupTemplateManager(templateManager);
        }

        public Piece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(IStructurePieceType.IGLU, compoundNBT);
            this.resourceLocation = new ResourceLocation(compoundNBT.getString("Template"));
            this.rotation = Rotation.valueOf(compoundNBT.getString("Rot"));
            this.number = compoundNBT.getInt("num");
            this.setupTemplateManager(templateManager);

        }

        private void setupTemplateManager(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementSettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(getPos())
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementSettings);
        }

        @Override
        protected void readAdditional(CompoundNBT compoundNBT) {
            super.readAdditional(compoundNBT);
            compoundNBT.putString("Template", this.resourceLocation.toString());
            compoundNBT.putString("Rot", this.rotation.name());
            compoundNBT.putInt("num", this.number);

        }

        @Override
        public boolean addComponentParts(IWorld iworld, Random ran,
                                         MutableBoundingBox boundingbox,
                                         ChunkPos chunkPos) {
            PlacementSettings placeSettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(getPos())
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            BlockPos pffset = getPos();
            BlockPos pos = this.templatePosition.add(Template.transformedBlockPos(placeSettings, new BlockPos(3 - pffset
                    .getX(), 0, 0 - pffset.getZ())));

            int y = WorldUtils.getSurface(iworld.getWorld(), pos).getY();

            BlockPos templatePosition = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, y - 90 - 1, 0);
            boolean addedParts = super.addComponentParts(iworld, ran, boundingbox, chunkPos);

            this.templatePosition = templatePosition;
            return addedParts;
        }

        @Override
        protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld,
                                        Random random,
                                        MutableBoundingBox mutableBoundingBox) {

        }
    }
}
