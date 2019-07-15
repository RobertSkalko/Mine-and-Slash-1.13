package com.robertx22.saveclasses;

import com.robertx22.uncommon.capability.EntityCap;
import com.robertx22.uncommon.datasaving.Load;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;

@Storable
public class PlayersCapBackup {

    @Store
    private HashMap<String, Integer> map = new HashMap<>();

    public void backup(ServerPlayerEntity player, EntityCap.UnitData data) {

        int currentLvl = data.getLevel();
        int backedUpLvl = map.getOrDefault(getKey(player), currentLvl);

        if (backedUpLvl < currentLvl) {
            map.put(getKey(player), data.getLevel());
        }
    }

    public void restoreFromBackup(ServerPlayerEntity player) {

        int currentLvl = Load.Unit(player).getLevel();
        int backedUpLvl = map.getOrDefault(getKey(player), currentLvl);

        if (currentLvl < backedUpLvl) {
            Load.Unit(player)
                    .freelySetLevel(map.getOrDefault(getKey(player), currentLvl));
            player.sendMessage(new StringTextComponent("Level Restored. If you have an error in your log file relating to the level loss, send to robertx22 (Mine and Slash)."));

        } else {
            player.sendMessage(new StringTextComponent("No need to restore level, your level is higher than the backed-up level."));
        }
    }

    private String getKey(ServerPlayerEntity player) {
        return player.getUniqueID().toString();
    }
}
