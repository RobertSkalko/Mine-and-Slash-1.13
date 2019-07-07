package com.robertx22.mmorpg.registers.common;

import com.robertx22.mmorpg.Ref;
import com.robertx22.world_gen.structures.Dungeon0Pieces;
import com.robertx22.world_gen.structures.FloatingIslandPieces;
import com.robertx22.world_gen.structures.TowerPieces;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class StructurePieceRegisters {

    public static IStructurePieceType TOWER;
    public static IStructurePieceType FLOATING_ISLAND;
    public static IStructurePieceType DUNGEON_0;

    public static void reg() {
        TOWER = IStructurePieceType.register(TowerPieces.TowerPiece::new, Ref.MODID + ":tower_piece");
        TOWER = IStructurePieceType.register(Dungeon0Pieces.Dungeon0Piece::new, Ref.MODID + ":dungeon0_piece");
        FLOATING_ISLAND = IStructurePieceType.register(FloatingIslandPieces.FloatingIslandPiece::new, Ref.MODID + ":floating_island_piece");
    }

}
