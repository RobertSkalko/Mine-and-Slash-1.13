package com.robertx22.mmorpg.registers.common;

import com.robertx22.mmorpg.Ref;
import com.robertx22.world_gen.structures.TowerPieces;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class StructurePieceRegisters {

    public static IStructurePieceType TOWER;

    public static void reg() {
        TOWER = IStructurePieceType.register(TowerPieces.TowerPiece::new, Ref.MODID + ":tower_piece");
    }

}
