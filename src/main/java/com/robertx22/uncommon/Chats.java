package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.interfaces.IAutoLocName;

public enum Chats implements IAutoLocName {

    Ran_Out_Of_Time,
    You_have_attuned_to_this_Altar,
    Infusion_Failed_Horribly,
    Not_enough_time,
    Infusion_Failed,
    Infusion_was_incredibly_Successful,
    A_Piece_of_gear_is_too_high_level_for_you,
    Map_time_penalty_activated,
    Player_died_in_a_map_world,
    You_have_ran_out_of_time,
    Teleporting_back,
    No_targets_found,
    You_are_too_low_level,
    Cooldown_not_met,
    Weapon_durability_is_too_low,
    You_have_leveled_up,
    No_bed_found,
    Teleport_canceled_due_to_movement,
    You_are_not_inside_a_map_world,
    has_died_by_the_hands_of,
    Not_attuned_to_any_altars,
    Distance_too_high_to_teleport,
    Teleport_started,
    Not_enough_experience,
    Time_ran_out_due_to_Permadeath,
    Remaining_Map_Time_is,
    Can_not_go_over_maximum_level,
    This_is_not_an_Attunement_Altar;

    @Override
    public IAutoLocName.AutoLocGroup locNameGroup() {
        return AutoLocGroup.Chat_Messages;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".chat." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return this.name().replaceAll("_", " ");
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}
