package com.robertx22.api;

import com.robertx22.uncommon.capability.EntityData.UnitData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MineAndSlashEvents {

    public static class GiveExpEvent extends LivingEvent {

        public int experience = 0;
        public UnitData playerCapability;
        public EntityLivingBase entityKilled;
        public EntityPlayer player;

        public GiveExpEvent(EntityLivingBase killed, EntityPlayer player,
                            UnitData playerdata, int exp) {
            super(player);
            this.entityKilled = killed;
            this.player = player;
            this.playerCapability = playerdata;
            this.experience = exp;
            this.setResult(Result.ALLOW);

        }

    }
}