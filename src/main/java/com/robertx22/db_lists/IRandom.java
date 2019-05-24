package com.robertx22.db_lists;

public interface IRandom<T, Config> {

    T random(Config config);

    T random();

}
