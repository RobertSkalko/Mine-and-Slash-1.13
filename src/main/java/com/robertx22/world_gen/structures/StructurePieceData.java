package com.robertx22.world_gen.structures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.UUID;

public class StructurePieceData {

    IStructurePieceType type;
    TemplateManager templateManager;
    ResourceLocation resourceLocation;
    BlockPos blockPos;
    Rotation rotation;
    int height;
    Biome biome;
    int lowerIntoGroundBy = 0;
    String guid = "";

    private StructurePieceData generateGUID() {
        this.guid = UUID.randomUUID().toString();
        return this;
    }

    public StructurePieceData(IStructurePieceType type, TemplateManager templateManager,
                              BlockPos blockPos, Rotation rotation, Biome biome) {

        this.type = type;
        this.templateManager = templateManager;
        this.blockPos = blockPos;
        this.rotation = rotation;
        this.biome = biome;

        generateGUID();

    }

    public StructurePieceData resource(ResourceLocation loc) {
        this.resourceLocation = loc;
        return this;
    }

    public StructurePieceData height(int height) {
        this.height = height;
        return this;
    }

}
