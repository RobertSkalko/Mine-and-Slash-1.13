package com.robertx22.db_lists;

import java.util.List;

public interface IRandom<T, Config> {

    T random(Config config);

    T random();

    List<T> allThatMeetRequirement();

}
